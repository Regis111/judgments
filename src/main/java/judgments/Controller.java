package judgments;

import judgments.ApiModel.Judgment;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Controller {
    private final MainView mainView;
    private final DateRangeFormView formView;
    private final Model model;
    private final ApiDownloader apiDownloader;
    private static final String HELP = "Istnieją następujące komendy w systemie: \n" +
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

    public Controller(Model model, ApiDownloader apiDownloader) {
        this.model = model;
        this.formView = new DateRangeFormView(this);
        this.mainView = new MainView(this, new CommandHistory());
        this.apiDownloader = apiDownloader;
    }

    public void openFormView() {
        this.formView.setVisible(true);
    }

    public void updateJudgments(LocalDate start, LocalDate end) {
        try {
            List<Judgment> judgments = apiDownloader.requestJudgments(start, end);
            this.model.addJudgments(judgments);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this.formView,
                    exception.getMessage(),
                    exception.getClass().getName(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void invoke(String input) {
        String regex = "\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] arr = input.replace("\"", "").split(regex);
        List<String> args = Arrays.asList(Arrays.copyOfRange(arr, 1, arr.length));
        switch (arr[0]) {
            case Command.COURTS:
                mainView.log(model.courtTypeDistribution()
                        .entrySet()
                        .stream()
                        .map(entrySet -> "<" + entrySet.getKey().toString() + "," + entrySet.getValue() + ">")
                        .reduce("", String::concat));
                break;
            case Command.RUBRUM:
                mainView.log(model.getMetrics(args));
                break;
            case Command.JUDGES_PER_JUDGMENT:
                mainView.log(String.format("%f", model.judgesPerJudgment()));
                break;
            case Command.MONTHS:
                mainView.log(model.monthDistribution()
                        .entrySet()
                        .stream()
                        .map(entrySet -> "<" + entrySet.getKey() + "," + entrySet.getValue() + ">")
                        .reduce("", String::concat));
                break;
            case Command.JUDGE:
                mainView.log(String.format("%d", model.numberOfJudgmentsOfJudge(args.get(0))));
                break;
            case Command.JUDGES:
                mainView.log(model.top10judges().stream().reduce("", String::concat));
                break;
            case Command.REGULATIONS:
                mainView.log(model.top10laws().stream().reduce("", String::concat));
                break;
            case Command.JURY:
                Map<Integer, Long> jury = model.jury();
                for (Map.Entry<Integer, Long> e : jury.entrySet()) {
                    mainView.log("<" + e.getKey() + "," + e.getValue() + ">");
                }
                break;
            case Command.HELP:
                mainView.log(HELP);
                break;
            default:
                mainView.log(String.format("No such command %s", arr[0]));
                break;
        }
    }
}

class Command {
    public static final String COURTS = "courts";
    public static final String RUBRUM = "rubrum";
    public static final String JUDGES_PER_JUDGMENT = "judgesPerJudgment";
    public static final String MONTHS = "months";
    public static final String JUDGE = "judge";
    public static final String JUDGES = "judges";
    public static final String REGULATIONS = "regulations";
    public static final String JURY = "jury";
    public static final String HELP = "help.txt";
}
