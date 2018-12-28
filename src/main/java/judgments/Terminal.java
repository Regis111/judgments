package judgments;
import judgments.Functions.CommandInvoker;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.*;
import javax.swing.text.BadLocationException;

public class Terminal extends JFrame {

    private Vector v = new Vector();
    private JTextArea area;
    private int pos = 0;

    public Terminal(HashMap<String, Judgment> map,String path) {
        setTitle("Judgment Database");
        setSize(800, 500);
        JPanel j = new JPanel();
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
            private CommandInvoker invoker = new CommandInvoker(map);
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deleteTextInFile(path);
                area(evt,invoker);
            }
            private void area(KeyEvent evt,CommandInvoker invoker) {
                int keyCode = evt.getKeyCode();
                if(keyCode == 8) { // przycisk Backspace
                    try{
                        int caretOffset = area.getCaretPosition();
                        int lineNumber = area.getLineOfOffset(caretOffset);
                        if(area.getLineStartOffset(lineNumber) == area.getCaretPosition()){
                            evt.consume();
                        }
                    }catch(BadLocationException ex){
                        ex.printStackTrace();
                    }
                }else if (keyCode == 38) { // przycisk górnej strzałki
                    try {
                        if(pos >= 0 && pos <= v.size() - 1){
                            pos++;
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
                } else if (keyCode == 40) { // przycisk dolnej strzałki
                    if(pos >= 2 && pos <= v.size() + 1){
                        pos--;
                        String store = (String) v.get(v.size() - pos);
                        replacer(store);
                        evt.consume();
                    }
                    else if (pos == 1){
                        replacer("");
                        pos--;
                    }
                } else if (keyCode == 10){ // przycisk Enter
                    try{
                        int caretOffset = area.getCaretPosition();
                        int lineNumber = area.getLineOfOffset(caretOffset);
                        int endOffset = area.getLineEndOffset(lineNumber);
                        area.setCaretPosition(endOffset);
                        v.add(linetext(map,path,invoker));
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
    /*
    odpowiada za zamienianie tekstu komendy na tekst komend znajdujących się w wektorze
     */
    private void replacer(String rep) {
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
    /*
    odpowiada za wyświetlenie rezultatu komendy,
     */
    private String linetext(HashMap<String, Judgment> map, String path,CommandInvoker invoker) {
        String text = null;
        String result = null;
        try {
            int offset = area.getLineOfOffset(area.getCaretPosition());
            int start = area.getLineStartOffset(offset);
            int end = area.getLineEndOffset(offset);
            text = area.getText(start, (end - start));
            result = invoker.invoke(text);
            area.append("\n" + result);
            } catch (Exception e){
                e.printStackTrace();
            }
            writeToFile(path,text,result);
        return text;
    }
    /*
    odpowiada za wpisywanie wszystkich komend i ich wyników do pliku txt
    */
    private void writeToFile(String path, String text, String result){
        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter writer = new PrintWriter(bw))
        {
            writer.println(text);
            writer.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteTextInFile(String path){
        try{
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}