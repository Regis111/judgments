import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args){
        try{
            FileOpener x = new FileOpener();
            Parser parser = new Parser();
            ArrayList<String> content = x.getFiles("C:\\JSON");
            HashMap<String,Judgement> map = parser.parseToMap(content);
            JudgementsData data = new JudgementsData(map);
            //System.out.println(Arrays.toString(data.monthDistribution()));
            System.out.println(data.judgesPerJudgement());
            //System.out.println(map.get("I UK 15/04"));
        }catch (IOException e){
            System.out.println("coś nie wyszło");
        }

    }
}
