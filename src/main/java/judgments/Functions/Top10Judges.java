package judgments.Functions;

import judgments.Attributes.Judge;
import judgments.Judgement;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Top10Judges extends AbstractFunction {
    public List<Judge> top10judges() {
        Map<Judge,Integer> judgeHashMap = new LinkedHashMap<>();
        for(Judgement judgement : judgementHashMap.values()){
            for (Judge judge : judgement.getJudges()){
                if(judgeHashMap.containsKey(judge)){
                    Integer oldValue = judgeHashMap.get(judge);
                    judgeHashMap.replace( judge, oldValue,oldValue + 1);
                }
                else{
                    judgeHashMap.put(judge,1);
                }
            }
        }
        List<Judge> judges = judgeHashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return judges;
    }
}
