package judgments.Functions;

import judgments.Model.ReferencedRegulation;
import judgments.Model.Judgment;

import java.util.*;
import java.util.stream.Collectors;

public class Regulations extends AbstractFunction{

    Regulations(HashMap<String, Judgment> JudgmentHashMap, int num) {
        super(JudgmentHashMap, num);
    }

    private List<String> top10laws(){
        Map<String,Integer> judgeHashMap = new LinkedHashMap<>();
        for(Judgment judgment : JudgmentHashMap.values()){
            for (ReferencedRegulation regulation : judgment.getReferencedRegulations()){
                if(judgeHashMap.containsKey(regulation.getJournalTitle())){
                    Integer oldValue = judgeHashMap.get(regulation.getJournalTitle());
                    judgeHashMap.replace( regulation.getJournalTitle(), oldValue,oldValue + 1);
                }
                else{
                    judgeHashMap.put(regulation.getJournalTitle(),1);
                }
            }
        }
        List<String> laws = judgeHashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return laws;
    }
    @Override
    public String function(List<String> list) {
        return String.join("\n", top10laws());
    }
}
