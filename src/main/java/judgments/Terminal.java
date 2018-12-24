package judgments;
import judgments.Functions.CommandInvoker;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                        v.add(linetext(map,path));
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
    private String linetext(HashMap<String, Judgment> map, String path) {
        String text = null;
        String result = null;
        try {
            CommandInvoker invoker = new CommandInvoker(map);
            int offset = area.getLineOfOffset(area.getCaretPosition());
            int start = area.getLineStartOffset(offset);
            int end = area.getLineEndOffset(offset);
            text = area.getText(start, (end - start));
            result = invoker.invoke(text);
            try{
                if(invoker.invoke(text).equals("-1917")) {
                    nothingImportant("a.wav");
                    byte [] encoded = Files.readAllBytes(Paths.get("fist.txt"));
                    area.append("\n" + new String(encoded));
                }
                else{
                    area.append("\n" + result);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try(FileWriter fw = new FileWriter(path, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(text);
                //more code
                out.println(result);
                //more code
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        return text;
    }

    private static void nothingImportant(String filePath){
        InputStream something;
        try{
            something = new FileInputStream(new File(filePath));
            AudioStream a = new AudioStream(something);
            AudioPlayer.player.start(a);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}