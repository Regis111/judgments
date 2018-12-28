package judgments.Functions;

import judgments.Judgment;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Months extends AbstractFunction{
    public Months(HashMap<String, Judgment> JudgmentHashMap, int num) {
        super(JudgmentHashMap, num);
    }

    private int[] monthDistribution(){
        Collection<Judgment> judgments = JudgmentHashMap.values();
        int [] JudgmentsPerMonth = new int[12];
        for(Judgment judgment : judgments){
            if(!judgment.getSource().getPublicationDate().isEmpty()){
                Integer a = Integer.valueOf(judgment.getSource().getPublicationDate().substring(5,7));
                JudgmentsPerMonth[a-1]++;
            }
        }
        return JudgmentsPerMonth;
    }

    @Override
    public String function(List<String> list) {
        return Arrays.toString(monthDistribution());
    }
}
