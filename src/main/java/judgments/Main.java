package judgments;

import judgments.Attributes.ReferencedRegulation;
import judgments.Attributes.SpecialRole;
import judgments.Functions.CommandInvoker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){
        try{
            FileOpener x = new FileOpener();
            HTMLFileOpener y = new HTMLFileOpener();

            HTMLParser htmlParser = new HTMLParser();
            JSONParser jsonParser = new JSONParser();

            ArrayList<String> content = x.getFiles("C:\\JSON");
            ArrayList<String> content1 = y.getFilesContent("C:\\HTML");

            ArrayList<Judgment> judgments = htmlParser.parseFiles(content1);
            HashMap<String, Judgment> map = jsonParser.parseToMap(content);
            
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    Terminal terminal = new Terminal(map);
                }
            });

        }catch (IOException e){
            System.out.println("coś nie wyszło");
            e.printStackTrace();
            return;
        }
    }
}