package judgments;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.logging.Logger;

public class MainView extends JFrame {
    private static final Logger logger =
            Logger.getLogger(MainView.class.getName());
    private final TerminalArea area;
    private final Controller controller;
    private final CommandHistory commandHistory;

    public MainView(Controller controller, CommandHistory commandHistory) {
        this.area = new TerminalArea();
        this.controller = controller;
        this.commandHistory = commandHistory;
        this.area.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                switch (keyCode) {
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
        setUpFrame();
    }

    private void setUpFrame() {
        setTitle("Judgment Terminal");
        setSize(800, 500);
        setLocationRelativeTo(null);

        JMenuBar menuBar = createBar();
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(area);
        jPanel.add(new JScrollPane(area));

        setContentPane(jPanel);
        setJMenuBar(menuBar);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private JMenuBar createBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItem = createImportMenuItem();
        menu.add(menuItem);
        menuBar.add(menu);
        return menuBar;
    }

    private JMenuItem createImportMenuItem() {
        JMenuItem menuItem = new JMenuItem("Import judgments");
        menuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.openFormView();
            }
        });
        return menuItem;
    }

    private void handleEnter() {
        logger.entering(getClass().getName(), "handleEnter");
        try {
            int caretOffset = area.getCaretPosition();
            int lineNumber = area.getLineOfOffset(caretOffset);
            int endOffset = area.getLineEndOffset(lineNumber);
            area.setCaretPosition(endOffset);
            String input = area.readShellCommand();
            commandHistory.addCommand(input);
            controller.invoke(input);

        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private void handleBackspace(KeyEvent keyEvent) {
        logger.entering(getClass().getName(), "handleBackspace");
        try {
            int caretOffset = area.getCaretPosition();
            int lineNumber = area.getLineOfOffset(caretOffset);
            if (area.getLineStartOffset(lineNumber) == caretOffset) {
                keyEvent.consume();
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private void handleArrowUp(KeyEvent keyEvent) {
        logger.entering(getClass().getName(), "handleArrowUp");
        Optional<String> command = commandHistory.getPrevious();
        command.ifPresent(this::replace);
        keyEvent.consume();
    }

    private void handleArrowDown(KeyEvent keyEvent) {
        logger.entering(getClass().getName(), "handleArrowDown");
        Optional<String> command = commandHistory.getNext();
        command.ifPresent(this::replace);
        keyEvent.consume();
    }

    /*
    odpowiada za zamienianie tekstu komendy na tekst komend znajdujących się w wektorze
     */
    private void replace(String rep) {
        logger.entering(getClass().getName(), "replace");
        try {
            int caretOffset = area.getCaretPosition();
            int lineNumber = area.getLineOfOffset(caretOffset);
            int startOffset = area.getLineStartOffset(lineNumber);
            int endOffset = area.getLineEndOffset(lineNumber);
            area.replaceRange(rep, startOffset, endOffset);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void log(String s) {
        this.area.append("\n" + s);
    }

}
