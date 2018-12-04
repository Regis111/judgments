import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class CommandInterpreter {
    public void commandInterpreter(String input,JudgementsData data){
        String [] arrayOfCommands = input.split(" ");
        String command = arrayOfCommands[0];
        switch (command){
            case "getMetrics":
                if(arrayOfCommands.length == 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                List<String> signatures = Arrays.asList(Arrays.copyOfRange(arrayOfCommands, 1, arrayOfCommands.length-1));
                data.getMetrics(signatures);
            case "getReasons":
                if(arrayOfCommands.length != 2) throw new InputMismatchException("Błędna ilość danych wejściowych");
                System.out.println(data.getReasons(arrayOfCommands[1]));
                break;
            case "numberOfJudgementsOfJudge":
                /*
                komenda + imię + nazwisko => długość inputu = 3
                 */
                if(arrayOfCommands.length != 3) throw new InputMismatchException("Błędna ilość danych wejściowych");
                data.numberOfJudgementsOfJudge(arrayOfCommands[1],arrayOfCommands[2]);
                break;
            case "top10Judges":
                if(arrayOfCommands.length != 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                data.top10judges();
                break;
            case "monthDistribution":
                if(arrayOfCommands.length != 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                data.monthDistribution();
                break;
            case "courtTypeDistribution":
                if(arrayOfCommands.length != 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                System.out.println(data.courtTypeDistribution());
                break;
            case "top10Laws":
                if(arrayOfCommands.length != 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                System.out.println(data.top10laws());
                break;
            case "judgesPerJudgement":
                if(arrayOfCommands.length != 1) throw new InputMismatchException("Błędna ilość danych wejściowych");
                System.out.println(data.judgesPerJudgement());
                break;
        }
    }
}
