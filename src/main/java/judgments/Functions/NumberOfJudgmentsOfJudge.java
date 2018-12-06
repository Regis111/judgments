package judgments.Functions;

import judgments.Judgment;

import java.util.HashMap;
import java.util.List;

public class NumberOfJudgmentsOfJudge extends AbstractFunction{
    public NumberOfJudgmentsOfJudge(HashMap<String, Judgment> judgementHashMap, String command) {
        super(judgementHashMap, command);
    }

    public int numberOfJudgementsOfJudge(String judgeName){
        return (int)judgementHashMap.values().stream().map(Judgment::getJudges)
                .flatMap(List::stream)
                .filter(judge -> judge.getName().equals(judgeName)).count();
    }

    @Override
    public String function(List<String> list) {
        String judgeName = list.get(0);
        return String.valueOf(numberOfJudgementsOfJudge(judgeName));
    }
}
