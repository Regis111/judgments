package judgments.Functions;

import judgments.Judgment;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.security.krb5.internal.crypto.Des;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

public class NumberOfJudgmentsOfJudge extends AbstractFunction{
    public NumberOfJudgmentsOfJudge(HashMap<String, Judgment> JudgmentHashMap, int num) {
        super(JudgmentHashMap, num);
    }

    public int numberOfJudgmentsOfJudge(String judgeName){
        if(judgeName.equals("Vladimir Lenin") || judgeName.equals("Nikita Chruszczow") || judgeName.equals("Józef Stalin")){
            return (-1917);
        }
        return (int)JudgmentHashMap.values().stream().map(Judgment::getJudges)
                .flatMap(List::stream)
                .filter(judge -> judge.getName().equals(judgeName)).count();
    }

    @Override
    public String function(List<String> list){
        String judgeName = list.get(0);
        return String.valueOf(numberOfJudgmentsOfJudge(judgeName));
    }


}
