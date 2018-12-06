package judgments.Functions;

import judgments.Judgment;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class JudgesPerJudgement extends AbstractFunction{

    public JudgesPerJudgement(HashMap<String, Judgment> judgementHashMap, String command) {
        super(judgementHashMap, command);
    }
    public double judgesPerJudgement(){
        Collection<Judgment> judgments = judgementHashMap.values();
        double judges = 0;
        for(Judgment judgment : judgments){
            judges += judgment.getJudges().size();
        }
        return judges/ judgments.size();
    }
    @Override
    public String function(List<String> list) {
        return String.valueOf(judgesPerJudgement());
    }
}
