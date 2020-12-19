package judgments.Functions;

import judgments.Model.Judgment;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jury extends AbstractFunction{

    Jury(HashMap<String, Judgment> JudgmentHashMap, int numberOfArgumentsDemanded) {
        super(JudgmentHashMap, numberOfArgumentsDemanded);
    }

    private Map<Integer,Integer> jury(){
        Collection<Judgment> judgments = JudgmentHashMap.values();
        Map<Integer,Integer> distribution = new HashMap<>();
        for (Judgment judgment : judgments){
            int numberOfJudges = judgment.getJudges().size();
            if(distribution.containsKey(numberOfJudges)){
                int oldValue = distribution.get(numberOfJudges);
                distribution.replace(numberOfJudges,oldValue,oldValue+1);
            }
            else{
                distribution.put(numberOfJudges,1);
            }
        }
        return distribution;
    }

    @Override
    public String function(List<String> list) {
        Map<Integer,Integer> map = jury();
        StringBuilder result = new StringBuilder("");
        for(Map.Entry<Integer,Integer> e : map.entrySet()){
            result.append("<" + e.getKey() + "," + e.getValue() + "> ");
        }
        return result.toString();
    }
}
