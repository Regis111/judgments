package judgments;
import judgments.Functions.AbstractFunction;
import judgments.Functions.CommandInvoker;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.*;
import javax.swing.text.BadLocationException;

public class Terminal extends JFrame {

    private Vector v = new Vector();
    private JTextArea area;
    private int pos = 0;

    public Terminal(HashMap<String, Judgment> map) {
        setTitle("Judgment Database");
        JPanel j = new JPanel();
        setLayout(new GridLayout(1, 1));
        setSize(800, 500);
        j.setLayout(new GridLayout(1, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        area = new JTextArea("");
        area.setBackground(Color.black);
        area.setForeground(Color.white);
        area.setCaretColor(Color.white);
        area.setFont(new Font("Consolas", Font.PLAIN, 19));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                area(evt);
            }
            private void area(KeyEvent evt) {
                int keyCode = evt.getKeyCode();
                if(keyCode == 8) {
                    try{
                        int caretOffset = area.getCaretPosition();
                        int lineNumber = area.getLineOfOffset(caretOffset);
                        if(area.getLineStartOffset(lineNumber) == area.getCaretPosition()){
                            evt.consume();
                        }
                    }catch(BadLocationException ex){
                        ex.printStackTrace();
                    }
                }else if (keyCode == 38) {
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
                    else if (pos == 1){
                        replacer("");
                        pos--;
                    }
                } else if (keyCode == 10){
                    try{
                        int caretOffset = area.getCaretPosition();
                        int lineNumber = area.getLineOfOffset(caretOffset);
                        int endOffset = area.getLineEndOffset(lineNumber);
                        area.setCaretPosition(endOffset);
                        v.add(linetext(map));
                    }catch(BadLocationException ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        j.add(area);
        j.add(new JScrollPane(area));
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
    String linetext(HashMap<String, Judgment> map) {
        String text = null;
        try {
            JTextArea ta = area;
            CommandInvoker invoker = new CommandInvoker(map);
            int offset = ta.getLineOfOffset(ta.getCaretPosition());
            int start = ta.getLineStartOffset(offset);
            int end = ta.getLineEndOffset(offset);
            text = ta.getText(start, (end - start));
            area.append("\n" + invoker.invoke(text));
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        return text;
    }
}