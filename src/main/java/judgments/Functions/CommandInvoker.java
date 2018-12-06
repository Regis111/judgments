package judgments.Functions;

import judgments.Judgment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandInvoker {
    HashMap<String,AbstractFunction> commands = new HashMap<>();
    String regex = "\\\\s+(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";

    public CommandInvoker(HashMap<String, Judgment> map){
        commands.put("courtTypeDistribution", new CourtTypeDistribution(map,"courtTypeDistribution"));
        commands.put("getMetrics",new GetMetrics(map,"getMetrics"));
        commands.put("getReasons",new GetReasons(map,"GetReasons"));
        commands.put("judgesPerJudgement",new JudgesPerJudgement(map,"JudgesPerJudgement"));
        commands.put("monthDistribution",new MonthDistribution(map,"monthDistribution"));
        commands.put("numberOfJudgmentsOfJudge",new NumberOfJudgmentsOfJudge(map,"numberOfJudgmentsOfJudge"));
        commands.put("top10Judges", new Top10Judges(map,"top10Judges"));
        commands.put("top10Laws", new Top10Laws(map,"top10Laws"));
    }

    public String invoke(String input){
        String [] arr = input.split(regex);
        for(String string : arr){
            string.replaceAll("\"","" );
        }
        AbstractFunction function = commands.get(arr[0]);
        List<String> list = new ArrayList<>();
        if(arr.length > 1){
            list = Arrays.asList(Arrays.copyOfRange(arr, 1, arr.length - 1));
        }
        return function.function(list);
    }
}
