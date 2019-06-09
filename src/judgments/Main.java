package judgments;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args){
        try{
            FileOpener jsonFileOpener = new FileOpener();
            HTMLFileOpener htmlFileOpener = new HTMLFileOpener();

            HTMLParser htmlParser = new HTMLParser();
            JSONParser jsonParser = new JSONParser();

            ArrayList<String> content = jsonFileOpener.getFiles("JSON");
            ArrayList<String> content1 = htmlFileOpener.getFilesContent("HTML");

            ArrayList<Judgment> judgments = htmlParser.parseFiles(content1);

            HashMap<String, Judgment> map3 = new HashMap<>();
            HashMap<String, Judgment> map1 = jsonParser.parseToMap(content);
            HashMap<String, Judgment> map2 = htmlParser.parseToMap(judgments);

            map3.putAll(map1);
            map3.putAll(map2);

            java.awt.EventQueue.invokeLater(() -> new judgments.Terminal(map3,args[0]));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}