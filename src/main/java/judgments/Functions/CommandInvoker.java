package judgments.Functions;

import judgments.Judgment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandInvoker {
    HashMap<String,AbstractFunction> commands = new HashMap<>();
    String regex = "\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public CommandInvoker(HashMap<String, Judgment> map){
        commands.put("courtTypeDistribution", new CourtTypeDistribution(map)); // działą
        commands.put("getMetrics",new GetMetrics(map)); //działa
        commands.put("getReasons",new GetReasons(map)); //działa
        commands.put("judgesPerJudgment",new JudgesPerJudgement(map)); // działa
        commands.put("monthDistribution",new MonthDistribution(map)); // działa
        commands.put("numberOfJudgmentsOfJudge",new NumberOfJudgmentsOfJudge(map)); // działa
        commands.put("top10Judges", new Top10Judges(map)); //działa
        commands.put("top10Laws", new Top10Laws(map)); // działą
    }

    public String invoke(String input){
        String [] arr = input.split(regex);
        for(int i = 0 ;i< arr.length; i++){
            arr[i] = arr[i].replaceAll("\"","" );
        }
        AbstractFunction function = commands.get(arr[0]);
        if(function == null) return "Błędna komenda - nie ma takiej funkcji w systemie";
        List<String> list = new ArrayList<>();
        if(arr.length > 1){
            list = Arrays.asList(Arrays.copyOfRange(arr, 1, arr.length));
        }
        return function.function(list);
    }
}
