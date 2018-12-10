package judgments.Functions;

import judgments.Judgment;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class JudgesPerJudgement extends AbstractFunction{

    public JudgesPerJudgement(HashMap<String, Judgment> JudgmentHashMap,int num) {
        super(JudgmentHashMap, num);
    }
    public double judgesPerJudgment(){
        Collection<Judgment> judgments = JudgmentHashMap.values();
        double judges = 0;
        for(Judgment judgment : judgments){
            judges += judgment.getJudges().size();
        }
        return judges/ judgments.size();
    }
    @Override
    public String function(List<String> list) {
        return String.valueOf(judgesPerJudgment());
    }
}
