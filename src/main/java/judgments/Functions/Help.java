package judgments.Functions;

import judgments.Judgment;

import java.util.HashMap;
import java.util.List;

public class Help extends AbstractFunction{
    public Help(HashMap<String, Judgment> JudgmentHashMap, int numberOfArgumentsDemanded) {
        super(JudgmentHashMap, numberOfArgumentsDemanded);
    }

    @Override
    public String function(List<String> list) {
        return "Istnieją następujące komendy w systemie: \n" +
                "1. \"courtTypeDistribution\" - zwraca ilość wyroków przypadających na każdy typ sądu \n" +
                "2. \"getMetrics\" - zwraca informację na temat konkretnego wyroku \n" +
                "3. \"getReasons\" - zwraca uzasadnienie konkrentnego wyroku \n" +
                "4. \"judgesPerJudgement\" - zwraca średnią ilość sędziów przypadających na jeden wyrok \n" +
                "5. \"monthDistribution\" - zwraca ilość wyroków wydanych w każdym miesiącu \n" +
                "6. \"numberOfJudgmentsOfJudge\" - zwraca ilość wyroków związanych z wybranym sędzią \n" +
                "7. \"top10Laws\" - zwraca 10 najczęściej przytaczanych ustaw \n" +
                "8. \"top10Judges\" - zwraca 10 najbardziej aktywnych sędziów";
    }
}
