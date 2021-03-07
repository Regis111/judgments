package judgments;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import java.awt.Color;
import java.awt.Font;
import java.util.logging.Logger;

public class TerminalArea extends JTextArea {
    private static final Logger logger =
            Logger.getLogger(TerminalArea.class.getName());

    public TerminalArea() {
        super("");
        setBackground(Color.black);
        setForeground(Color.white);
        setCaretColor(Color.white);
        setFont(new Font("Consolas", Font.PLAIN, 19));
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    public String readShellCommand() {
        logger.entering(getClass().getName(), "readShellCommand");
        String text = "";
        try {
            int offset = getLineOfOffset(getCaretPosition());
            int start = getLineStartOffset(offset);
            int end = getLineEndOffset(offset);
            text = getText(start, (end - start));
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return text;
    }
}
