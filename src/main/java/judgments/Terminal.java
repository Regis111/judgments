package judgments;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class Terminal extends JFrame {

    Vector v = new Vector();
    JTextArea area;
    int pos = 0;

    public Terminal(JudgementsData data) {
        setTitle("my terminal");
        JPanel j = new JPanel();
        setLayout(new GridLayout(1, 1));
        setSize(800, 500);
        j.setLayout(new GridLayout(1, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        area = new JTextArea("");
        area.setBackground(Color.black);
        area.setForeground(Color.white);
        area.setCaretColor(Color.white);
        area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                area(evt);
            }
            private void area(KeyEvent evt) {
                int keyCode = evt.getKeyCode();
                if (keyCode == 38) {
                    try {
                        if(pos >= 0 && pos <= v.size() - 1){
                            pos++;
                            int ind = v.size() - pos;
                            String store = (String) v.get(v.size() - pos);
                            replacer(store);
                            evt.consume();
                        }
                        else if(pos == v.size()){
                            evt.consume();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (keyCode == 40) {
                    if(pos >= 2 && pos <= v.size() + 1){
                        pos--;
                        int ind = v.size() - pos;
                        String store = (String) v.get(v.size() - pos);
                        replacer(store);
                        evt.consume();
                    }
                } else if (keyCode == 10){
                    v.add(linetext(data));
                }
            }
        });
        j.add(area);
        add(j);
        setVisible(true);
    }
    void replacer(String rep) {
        try {
            int caretOffset = area.getCaretPosition();
            int lineNumber = area.getLineOfOffset(caretOffset);
            int startOffset = area.getLineStartOffset(lineNumber);
            int endOffset = area.getLineEndOffset(lineNumber);
            area.replaceRange(rep, startOffset, endOffset);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
    String linetext(JudgementsData data) {
        String text = null;
        try {
            JTextArea ta = area;
            CommandInterpreter interpreter = new CommandInterpreter();
            int offset = ta.getLineOfOffset(ta.getCaretPosition());
            int start = ta.getLineStartOffset(offset);
            int end = ta.getLineEndOffset(offset);
            text = ta.getText(start, (end - start));
            area.append("\n" + interpreter.commandInterpreter(text,data));
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        return text;
    }
}