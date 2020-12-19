package judgments.Functions;

import judgments.Model.Judgment;

import java.util.HashMap;
import java.util.List;

public class Help extends AbstractFunction{
    public Help(HashMap<String, Judgment> JudgmentHashMap, int numberOfArgumentsDemanded) {
        super(JudgmentHashMap, numberOfArgumentsDemanded);
    }

    @Override
    public String function(List<String> list) {
        return "Istnieją następujące komendy w systemie: \n" +
                "1. \"courts\" - zwraca ilość wyroków przypadających na każdy typ sądu \n" +
                "2. \"rubrum\" - zwraca informację na temat konkretnego wyroku \n" +
                "3. \"content\" - zwraca uzasadnienie konkrentnego wyroku \n" +
                "4. \"judgesPerJudgment\" - zwraca średnią ilość sędziów przypadających na jeden wyrok \n" +
                "5. \"months\" - zwraca ilość wyroków wydanych w każdym miesiącu \n" +
                "6. \"judge\" - zwraca ilość wyroków związanych z wybranym sędzią \n" +
                "7. \"regulations\" - zwraca 10 najczęściej przytaczanych ustaw \n" +
                "8. \"judges\" - zwraca 10 najbardziej aktywnych sędziów \n" +
                "9. \"jury\" - zwraca statystykę spraw przypadających na skład sędziowski\n\n" +
                "Argumenty powinny być podawane w cudzysłowiu np. rubrum \"II SA 2597/02\"\n";
    }
}