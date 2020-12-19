package judgments.View;

import judgments.Functions.CommandInvoker;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;
import java.util.logging.Logger;

public class TerminalArea extends JTextArea {
    private static final Logger logger =
            Logger.getLogger(TerminalArea.class.getName());
    private final CommandInvoker commandInvoker;
    private final Vector<String> commandHistory;
    private int pos = 0;

    public TerminalArea(CommandInvoker commandInvoker) {
        super("");
        this.commandHistory = new Vector<>();
        this.commandInvoker = commandInvoker;
        setBackground(Color.black);
        setForeground(Color.white);
        setCaretColor(Color.white);
        setFont(new Font("Consolas", Font.PLAIN, 19));
        setLineWrap(true);
        setWrapStyleWord(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                switch(keyCode) {
                    case KeyEvent.VK_ENTER:
                        handleEnter();
                        break;
                    case KeyEvent.VK_BACK_SPACE:
                        handleBackspace(keyEvent);
                        break;
                    case KeyEvent.VK_UP:
                        handleArrowUp(keyEvent);
                        break;
                    case KeyEvent.VK_DOWN:
                        handleArrowDown(keyEvent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void handleEnter() {
        logger.entering(getClass().getName(), "handleEnter");
        try{
            int caretOffset = getCaretPosition();
            int lineNumber = getLineOfOffset(caretOffset);
            int endOffset = getLineEndOffset(lineNumber);
            setCaretPosition(endOffset);
            commandHistory.add(displayCommandResult());
        }catch(BadLocationException ex){
            ex.printStackTrace();
        }
    }

    private void handleBackspace(KeyEvent keyEvent) {
        logger.entering(getClass().getName(), "handleBackspace");
        try{
            int caretOffset = getCaretPosition();
            int lineNumber = getLineOfOffset(caretOffset);
            if(getLineStartOffset(lineNumber) == getCaretPosition()){
                keyEvent.consume();
            }
        }catch(BadLocationException ex){
            ex.printStackTrace();
        }
    }

    private void handleArrowUp(KeyEvent keyEvent) {
        logger.entering(getClass().getName(), "handleArrowUp");
        try {
            if(pos >= 0 && pos <= commandHistory.size() - 1){
                pos++;
                String store = commandHistory.get(commandHistory.size() - pos);
                replace(store);
                keyEvent.consume();
            }
            else if(pos == commandHistory.size()){
                keyEvent.consume();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void handleArrowDown(KeyEvent keyEvent) {
        logger.entering(getClass().getName(), "handleArrowDown");
        if(pos >= 2 && pos <= commandHistory.size() + 1){
            pos--;
            String store = commandHistory.get(commandHistory.size() - pos);
            replace(store);
            keyEvent.consume();
        }
        else if (pos == 1){
            replace("");
            pos--;
        }
    }
    /*
    odpowiada za wyświetlenie rezultatu komendy,
     */
    private String displayCommandResult(/*String path*/) {
        logger.entering(getClass().getName(), "displayCommandResult");
        String text = "";
        try {
            int offset = getLineOfOffset(getCaretPosition());
            int start = getLineStartOffset(offset);
            int end = getLineEndOffset(offset);
            text = getText(start, (end - start));
            String result = commandInvoker.invoke(text);
            append("\n" + result);
        } catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }

    /*
    odpowiada za zamienianie tekstu komendy na tekst komend znajdujących się w wektorze
     */
    private void replace(String rep) {
        logger.entering(getClass().getName(), "replace");
        try {
            int caretOffset = getCaretPosition();
            int lineNumber = getLineOfOffset(caretOffset);
            int startOffset = getLineStartOffset(lineNumber);
            int endOffset = getLineEndOffset(lineNumber);
            replaceRange(rep, startOffset, endOffset);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
}
