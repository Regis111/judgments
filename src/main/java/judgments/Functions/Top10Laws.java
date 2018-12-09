package judgments.Functions;

import judgments.Attributes.ReferencedRegulation;
import judgments.Judgment;

import java.util.*;
import java.util.stream.Collectors;

public class Top10Laws extends AbstractFunction{

    public Top10Laws(HashMap<String, Judgment> JudgmentHashMap, int num) {
        super(JudgmentHashMap, num);
    }
    public List<String> top10laws(){
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
        if(list.size() > 0) return "Błędna ilość argumentów";
        return String.join("\n", top10laws());
    }
}
