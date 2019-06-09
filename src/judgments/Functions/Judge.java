package judgments.Functions;

import judgments.Judgment;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.security.krb5.internal.crypto.Des;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

public class Judge extends AbstractFunction{
    Judge(HashMap<String, Judgment> JudgmentHashMap, int num) {
        super(JudgmentHashMap, num);
    }

    private int numberOfJudgmentsOfJudge(String judgeName){
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
