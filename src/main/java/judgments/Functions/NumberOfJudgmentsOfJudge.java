package judgments.Functions;

import judgments.Judgment;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.security.krb5.internal.crypto.Des;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

public class NumberOfJudgmentsOfJudge extends AbstractFunction{
    public NumberOfJudgmentsOfJudge(HashMap<String, Judgment> JudgmentHashMap, int num) {
        super(JudgmentHashMap, num);
    }

    public int numberOfJudgmentsOfJudge(String judgeName){
        if(judgeName.equals("Józef Stalin")){
            Desktop desktop = Desktop.getDesktop();
            nothingImportant("a.wav");
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

    private static void nothingImportant(String filePath){
        InputStream something;
        try{
            something = new FileInputStream(new File(filePath));
            AudioStream a = new AudioStream(something);
            AudioPlayer.player.start(a);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
