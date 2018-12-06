package judgments;

import judgments.Attributes.CourtType;
import judgments.Attributes.Judge;

import java.util.Map;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

public class CommandInterpreter {
    public String commandInterpreter(String input,JudgementsData data){
        String [] arrayOfCommands = input.split(" ");
        String command = arrayOfCommands[0];
        switch (command){
            case "getMetrics":
                if(arrayOfCommands.length == 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                List<String> signatures = Arrays.asList(Arrays.copyOfRange(arrayOfCommands, 1, arrayOfCommands.length-1));
                return data.getMetrics(signatures);
            case "getReasons":
                if(arrayOfCommands.length != 2) throw new InputMismatchException("Błędna ilość danych wejściowych");
                return data.getReasons(arrayOfCommands[1]);
            case "numberOfJudgementsOfJudge":
                /*
                komenda + imię + nazwisko => długość inputu = 3
                 */
                if(arrayOfCommands.length != 3) throw new InputMismatchException("Błędna ilość danych wejściowych");
                return String.valueOf(data.numberOfJudgementsOfJudge(arrayOfCommands[1],arrayOfCommands[2]));
            case "top10Judges":
                if(arrayOfCommands.length != 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                return String.join(", ", data.top10judges().stream().map(Judge::getName).collect(Collectors.toList()));
            case "monthDistribution":
                if(arrayOfCommands.length != 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                return Arrays.toString(data.monthDistribution());
            case "courtTypeDistribution":
                if(arrayOfCommands.length != 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                Map<CourtType,Integer> map = data.courtTypeDistribution();
                StringBuilder result = new StringBuilder("");
                for(Map.Entry<CourtType,Integer> e : map.entrySet()){
                    result.append("<" + e.getKey() + "," + e.getValue() + "> ");
                }
                return result.toString();
            case "top10Laws":
                if(arrayOfCommands.length != 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                return String.join(", ", data.top10laws());
            case "judgesPerJudgement":
                if(arrayOfCommands.length != 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                return String.valueOf(data.judgesPerJudgement());
            default:
                return "Błędna komenda";
        }
    }
}
