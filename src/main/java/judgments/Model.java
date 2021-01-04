package judgments;

import judgments.Attributes.*;

import java.util.*;
import java.util.stream.Collectors;

public class Model {
    private HashMap<String, Judgment> judgments;

    public Model(HashMap<String, Judgment> judgments) {
        this.judgments = judgments;
    }

    public Map<Integer, Long> monthDistribution() {
        return judgments.values()
                .stream()
                .map(Judgment::getSource)
                .map(Source::getPublicationDate)
                .filter(str -> !str.isEmpty())
                .collect(Collectors.groupingBy(date -> Integer.parseInt(date.substring(5,7)), Collectors.counting()));
    }

    public List<String> top10laws() {
        return judgments
                .values()
                .stream()
                .map(Judgment::getReferencedRegulations)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(ReferencedRegulation::getJournalTitle, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public String getMetrics(List<String> signatures){
        StringBuilder result = new StringBuilder();
        for(String signature : signatures){
            Judgment judgment = judgments.get(signature);
            result.append("sygnatura orzeczenia: " + signature + "\n");
            result.append("Data wydania orzeczenia: " + judgment.getSource().getPublicationDate() + "\n");
            result.append("Rodzaj sądu: " + judgment.getCourtType() + "\n");
            result.append("Sędziowie: " + judgment.getJudges().stream().map(judge -> judge.getName() + " - " + judge.getSpecialRoles()).collect(Collectors.toList()));
            result.append("\n\n");
        }
        return result.toString();
    }

    public Map<Integer, Long> jury() {
        return judgments
                .values()
                .stream()
                .collect(Collectors.groupingBy(judgment -> judgment.getJudges().size(), Collectors.counting()));
    }

    public double judgesPerJudgment() {
        return judgments
                .values()
                .stream()
                .collect(Collectors.averagingDouble(judgment -> judgment.getJudges().size()));
    }

    public List<String> top10judges() {
        return judgments
                .values()
                .stream()
                .map(Judgment::getJudges)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Judge::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public long numberOfJudgmentsOfJudge(String judgeName){
        return judgments.values().stream().map(Judgment::getJudges)
                .flatMap(List::stream)
                .filter(judge -> judge.getName().equals(judgeName)).count();
    }

    public Map<CourtType, Long> courtTypeDistribution() {
        return judgments
                .values()
                .stream()
                .collect(Collectors.groupingBy(Judgment::getCourtType, Collectors.counting()));
    }
}
