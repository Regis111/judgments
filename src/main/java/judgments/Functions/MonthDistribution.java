package judgments.Functions;

import judgments.Judgment;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MonthDistribution extends AbstractFunction{
    public MonthDistribution(HashMap<String, Judgment> judgementHashMap, String command) {
        super(judgementHashMap, command);
    }

    public int[] monthDistribution(){
        Collection<Judgment> judgments = judgementHashMap.values();
        int [] judgementsPerMonth = new int[12];
        for(Judgment judgment : judgments){
            if(!judgment.getSource().getPublicationDate().isEmpty()){
                Integer a = Integer.valueOf(judgment.getSource().getPublicationDate().substring(5,7));
                judgementsPerMonth[a-1]++;
            }
        }
        return judgementsPerMonth;
    }

    @Override
    public String function(List<String> list) {
        return Arrays.toString(monthDistribution());
    }
}
