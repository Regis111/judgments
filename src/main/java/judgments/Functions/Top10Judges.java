package judgments.Functions;

import judgments.Attributes.Judge;
import judgments.Judgment;

import java.util.*;
import java.util.stream.Collectors;

public class Top10Judges extends AbstractFunction {

    public Top10Judges(HashMap<String, Judgment> JudgmentHashMap, int num) {
        super(JudgmentHashMap, num);
    }

    private List<String> top10judges() {
        Map<String,Integer> judgeHashMap = new HashMap<>();
        for(Judgment judgment : JudgmentHashMap.values()){
            for (String judge : judgment.getJudges().stream().map(Judge::getName).collect(Collectors.toList())){
                if(judgeHashMap.containsKey(judge)){
                    Integer oldValue = judgeHashMap.get(judge);
                    judgeHashMap.replace( judge, oldValue,oldValue + 1);
                }
                else{
                    judgeHashMap.put(judge,1);
                }
            }
        }

        List<String> judges = judgeHashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return judges;
    }

    @Override
    public String function(List<String> list) {
        return String.join(", ", top10judges().stream().collect(Collectors.toList()));
    }


}
