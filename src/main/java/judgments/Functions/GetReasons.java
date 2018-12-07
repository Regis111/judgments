package judgments.Functions;

import judgments.Judgment;
import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.List;

public class GetReasons extends AbstractFunction{
    public GetReasons(HashMap<String, Judgment> JudgmentHashMap, int num) {
        super(JudgmentHashMap, num);
    }

    public String getReasons(String signature){
        Judgment judgment = JudgmentHashMap.get(signature);
        int index = judgment.getTextContent().indexOf("UZASADNIENIE");
        return Jsoup.parse(judgment.getTextContent().substring(index)).toString().replaceAll("\\<[^>]*>","");
    }

    @Override
    public String function(List<String> list) {
        if(list.size() != 1) return "Błędna ilość argumentów";
        String signature = list.get(0);
        return getReasons(signature);
    }
}
