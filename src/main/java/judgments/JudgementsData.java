package judgments;

import judgments.Attributes.CourtType;
import judgments.Attributes.Judge;
import judgments.Attributes.ReferencedRegulation;

import org.jsoup.Jsoup;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

public class JudgementsData {

    HashMap<String, Judgment> judgementHashMap;
    //II i IV
    public String getMetrics(List<String> signatures){
        StringBuilder result = null;
        for(String signature : signatures){
            Judgment judgment = judgementHashMap.get(signature);
            result.append("Sygnatura orzeczenia: " + signature + "\n");
            result.append("Data wydania orzeczenia: " + judgment.getSource().getPublicationDate() + "\n");
            result.append("Rodzaj sądu: " + judgment.getCourtType() + "\n");
            result.append("Sędziowie: " + judgment.getJudges().stream().map(judge -> judge.getName()).collect(Collectors.toList())+ "\n\n");
        }
        return result.toString();
    }

    public JudgementsData(HashMap<String, Judgment> map){
        this.judgementHashMap = map;
    }
    //III
    public String getReasons(String signature){
        Judgment judgment = judgementHashMap.get(signature);
        int index = judgment.getTextContent().indexOf("UZASADNIENIE");
        return Jsoup.parse(judgment.getTextContent().substring(index)).toString().replaceAll("\\<[^>]*>","");
    }
    //V
    public int numberOfJudgementsOfJudge(String judgeName){
        return (int)judgementHashMap.values().stream().map(Judgment::getJudges)
                .flatMap(List::stream)
                .filter(judge -> judge.getName().equals(judgeName)).count();
    }
    //VI
    public List<Judge> top10judges(){
        Map<Judge,Integer> judgeHashMap = new LinkedHashMap<>();
        for(Judgment judgment : judgementHashMap.values()){
            for (Judge judge : judgment.getJudges()){
                if(judgeHashMap.containsKey(judge)){
                    Integer oldValue = judgeHashMap.get(judge);
                    judgeHashMap.replace( judge, oldValue,oldValue + 1);
                }
                else{
                    judgeHashMap.put(judge,1);
                }
            }
        }
        List<Judge> judges = judgeHashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return judges;
    }
    //VII
    public int[] monthDistribution(){
        Collection<Judgment> judgments = judgementHashMap.values();
        int [] judgementsPerMonth = new int[12];
        for(Judgment judgment : judgments){
            if(!judgment.getSource().getPublicationDate().isEmpty()){
                Integer a = Integer.valueOf(judgment.getSource().getPublicationDate().substring(5,7));
                judgementsPerMonth[a-1]++;
            }
        }
        return judgementsPerMonth;
    }
    //VIII
    public Map<CourtType,Integer> courtTypeDistribution(){
        Collection<Judgment> judgments = judgementHashMap.values();
        Map<CourtType,Integer> distribution = new HashMap<>();
        for(Judgment judgment : judgments){
            if(!distribution.containsKey(judgment.getCourtType())){
                distribution.put(judgment.getCourtType(),1);
            }
            else{
                Integer a = distribution.get(judgment.getCourtType());
                distribution.replace(judgment.getCourtType(),distribution.get(judgment.getCourtType()),distribution.get(judgment.getCourtType())+1);
            }
        }
        return distribution;
    }
    //IX
    public List<String> top10laws(){
        Map<String,Integer> judgeHashMap = new LinkedHashMap<>();
        for(Judgment judgment : judgementHashMap.values()){
            for (ReferencedRegulation regulation : judgment.getReferencedRegulations()){
                if(judgeHashMap.containsKey(regulation.getJournalTitle())){
                    Integer oldValue = judgeHashMap.get(regulation.getJournalTitle());
                    judgeHashMap.replace( regulation.getJournalTitle(), oldValue,oldValue + 1);
                }
                else{
                    judgeHashMap.put(regulation.getJournalTitle(),1);
                }
            }
        }
        List<String> laws = judgeHashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return laws;
    }
    //X
    public double judgesPerJudgement(){
        Collection<Judgment> judgments = judgementHashMap.values();
        double judges = 0;
        for(Judgment judgment : judgments){
            judges += judgment.getJudges().size();
        }
        return judges/ judgments.size();
    }
}