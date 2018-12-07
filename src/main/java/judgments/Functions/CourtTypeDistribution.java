package judgments.Functions;

import judgments.Attributes.CourtType;
import judgments.Judgment;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourtTypeDistribution extends AbstractFunction{

    public CourtTypeDistribution(HashMap<String, Judgment> JudgmentHashMap, String command) {
        super(JudgmentHashMap, command);
    }

    private Map<CourtType,Integer> courtTypeDistribution(){
        Collection<Judgment> judgments = JudgmentHashMap.values();
        Map<CourtType,Integer> distribution = new HashMap<>();
        for(Judgment judgment : judgments){
            if(!distribution.containsKey(judgment.getCourtType())){
                distribution.put(judgment.getCourtType(),1);
            }
            else{
                Integer a = distribution.get(judgment.getCourtType());
                distribution.replace(judgment.getCourtType(),distribution.get(judgment.getCourtType()),distribution.get(judgment.getCourtType())+1);
            }
        }
        return distribution;
    }

    @Override
    public String function(List<String> list) {
        Map<CourtType,Integer> map = courtTypeDistribution();
        StringBuilder result = new StringBuilder("");
        for(Map.Entry<CourtType,Integer> e : map.entrySet()){
            result.append("<" + e.getKey() + "," + e.getValue() + "> ");
        }
        return result.toString();
    }
}
