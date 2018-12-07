package judgments;

import judgments.Functions.CommandInvoker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){
        try{
            FileOpener x = new FileOpener();
            Parser parser = new Parser();
            ArrayList<String> content = x.getFiles("C:\\JSON");
            HashMap<String, Judgment> map = parser.parseToMap(content);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    Terminal terminal = new Terminal(map);
                }
            });
            /*
            CommandInvoker invoker = new CommandInvoker(map);
            System.out.println(invoker.invoke("getMetrics \"VIII U 1022/13\""));
            */
        }catch (IOException e){
            System.out.println("coś nie wyszło");
            return;
        }
    }
}