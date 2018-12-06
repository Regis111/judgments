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
            FileOpener x = new FileOpener(); //siema haaaaaaaaaaaaaaaaaaaa
            Parser parser = new Parser();
            ArrayList<String> content = x.getFiles("C:\\JSON");
            HashMap<String, Judgment> map = parser.parseToMap(content);
            /*System.out.println(Arrays.toString(data.monthDistribution()));
            System.out.println(data.judgesPerJudgement());
            System.out.println(data.top10judges().stream().map(judge -> judge.getName()).collect(Collectors.toList()));
            System.out.println(data.top10laws());
            System.out.println(data.courtTypeDistribution());
            System.out.println(data.getReasons("VIII Ka 283/14"));
            CommandInterpreter interpreter = new CommandInterpreter();
            System.out.println(interpreter.commandInterpreter("courtTypeDistribution",data));

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    Terminal terminal = new Terminal(map);
                }
            });
            */
            CommandInvoker commandInvoker = new CommandInvoker(map);
            Scanner reader = new Scanner(System.in);
            String input = reader.next();
            System.out.println(commandInvoker.invoke(input));
        }catch (IOException e){
            System.out.println("coś nie wyszło");
            return;
        }
    }
}