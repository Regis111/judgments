package judgments.Functions;

import judgments.Model.Judgment;
import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.List;

public class Content extends AbstractFunction{
    Content(HashMap<String, Judgment> JudgmentHashMap, int num) {
        super(JudgmentHashMap, num);
    }

    private String getReasons(String signature){
        Judgment judgment = JudgmentHashMap.get(signature);
        int index = judgment.getTextContent().indexOf("UZASADNIENIE");
        return Jsoup.parse(judgment.getTextContent().substring(index)).toString().replaceAll("\\<[^>]*>","");
    }

    @Override
    public String function(List<String> list) {
        String signature = list.get(0);
        return getReasons(signature);
    }
}
