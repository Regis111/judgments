package judgments.Functions;

import judgments.Model.Judgment;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Rubrum extends AbstractFunction{

    Rubrum(HashMap<String, Judgment> JudgmentHashMap, int num) {
        super(JudgmentHashMap, num);
    }

    private String getMetrics(List<String> signatures){
        StringBuilder result = new StringBuilder();
        for(String signature : signatures){
            Judgment judgment = JudgmentHashMap.get(signature);
            result.append("Sygnatura orzeczenia: " + signature + "\n");
            result.append("Data wydania orzeczenia: " + judgment.getSource().getPublicationDate() + "\n");
            result.append("Rodzaj sądu: " + judgment.getCourtType() + "\n");
            result.append("Sędziowie: " + judgment.getJudges().stream().map(judge -> judge.getName() + " - " + judge.getSpecialRoles()).collect(Collectors.toList()));
            result.append("\n\n");
        }
        return result.toString();
    }

    @Override
    public String function(List<String> list) {
        if(list.size() == 0) return "Oczekiwana ilość argumentów to więcej niż 0";
        return getMetrics(list);
    }
}
