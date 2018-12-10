package judgments.Functions;

import judgments.Judgment;

import java.util.HashMap;
import java.util.List;

public class NumberOfJudgmentsOfJudge extends AbstractFunction{
    public NumberOfJudgmentsOfJudge(HashMap<String, Judgment> JudgmentHashMap, int num) {
        super(JudgmentHashMap, num);
    }

    public int numberOfJudgmentsOfJudge(String judgeName){
        return (int)JudgmentHashMap.values().stream().map(Judgment::getJudges)
                .flatMap(List::stream)
                .filter(judge -> judge.getName().equals(judgeName)).count();
    }

    @Override
    public String function(List<String> list) {
        String judgeName = list.get(0);
        return String.valueOf(numberOfJudgmentsOfJudge(judgeName));
    }
}
