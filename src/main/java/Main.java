import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {

    public static void main(String[] args){
        try{
            FileOpener x = new FileOpener();
            Parser parser = new Parser();
            ArrayList<String> content = x.getFiles("C:\\JSON");
            HashMap<String,Judgement> map = parser.parseToMap(content);
            JudgementsData data = new JudgementsData(map);
            data.getMetrics("VIII Ka 283/14");
            System.out.println(Arrays.toString(data.monthDistribution()));
            System.out.println(data.judgesPerJudgement());
            System.out.println(data.top10judges().stream().map(judge -> judge.getName()).collect(Collectors.toList()));
            System.out.println(data.top10laws());
            System.out.println(data.courtTypeDistribution());
            System.out.println(data.getReasons(map.get("VIII Ka 283/14")));

        }catch (IOException e){
            System.out.println("coś nie wyszło");
            return;
        }
    }
}