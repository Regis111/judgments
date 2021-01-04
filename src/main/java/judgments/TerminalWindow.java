package judgments;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class TerminalWindow extends JFrame {

    public TerminalWindow(TerminalArea terminalArea) {
        setTitle("Judgment Terminal");
        setSize(800, 500);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 1));
        jPanel.add(terminalArea);
        jPanel.add(new JScrollPane(terminalArea));
        add(jPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}