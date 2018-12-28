package judgments.Functions;

import judgments.Judgment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandInvoker {
    private HashMap<String,AbstractFunction> commands = new HashMap<>();
    private String regex = "\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public CommandInvoker(HashMap<String, Judgment> map){
        commands.put("courts", new CourtTypeDistribution(map,0));
        commands.put("rubrum",new GetMetrics(map,1));
        commands.put("content",new GetReasons(map,1));
        commands.put("judgesPerJudgment",new JudgesPerJudgement(map,0));
        commands.put("months",new MonthDistribution(map,0));
        commands.put("judge",new NumberOfJudgmentsOfJudge(map,1));
        commands.put("judges", new Top10Judges(map,0));
        commands.put("regulations", new Top10Laws(map,0));
        commands.put("help",new Help(map,0));
        commands.put("jury",new NumberOfJudgmentsOnJudgment(map,0));
    }

    public String invoke(String input){
        String [] arr = input.split(regex);
        for(int i = 0 ; i< arr.length ; i++){
            arr[i] = arr[i].replaceAll("\"","" );
        }
        AbstractFunction function = commands.get(arr[0]);
        if(function == null)
            return "Błędna komenda - nie ma takiej komendy w systemie \n\n"
                    + invoke("help");
        List<String> list = new ArrayList<>();
        if(arr.length > 1){
            list = Arrays.asList(Arrays.copyOfRange(arr, 1, arr.length));
        }
        if(function.getNumberOfArgumentsDemanded() != list.size() && !arr[0].equals("rubrum")){
            return "Błędna ilość argumentów dla komendy: \"" + arr[0] + "\" , oczekiwana to: " +
                    function.getNumberOfArgumentsDemanded();
        }
        return function.function(list);
    }
}
