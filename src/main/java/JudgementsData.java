import data.JudgementAttributes.CourtType;
import data.JudgementAttributes.Judge;
import data.JudgementAttributes.ReferencedCourtCase;
import data.JudgementAttributes.ReferencedRegulation;
import sun.font.TrueTypeFont;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

public class JudgementsData {

    HashMap<String,Judgement> judgementHashMap;
    //II i IV
    public void getMetrics(List<String> signature){
        Judgement judgement = judgementHashMap.get(signature);
        System.out.println("Sygnatura orzeczenia: " + judgement.getCourtCases());
        System.out.println("Data wydania orzeczenia: " + judgement.getSource().getPublicationDate());
        System.out.println("Rodzaj sądu: " + judgement.getCourtType());
        System.out.println("Sędziowie: " + judgement.getJudges());
    }

    public JudgementsData(HashMap<String,Judgement> map){
        this.judgementHashMap = map;
    }
    //III
    public void getReasons(){
        
    }
    //V
    public int numberOfJudgementsOfJudge(Judge judge){
        int i = 0;
        for(Judgement judgement : judgementHashMap.values()){
            if(judgement.getJudges().contains(judge)){
                i++;
            }
        }
        return i;
    }
    //VI
    public List<Judge> top10judges(){
        Map<Judge,Integer> judgeHashMap = new LinkedHashMap<>();
        for(Judgement judgement : judgementHashMap.values()){
            for (Judge judge : judgement.getJudges()){
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
        Collection<Judgement> judgements = judgementHashMap.values();
        int [] judgementsPerMonth = new int[12];
        for(Judgement judgement: judgements){
            if(!judgement.getSource().getPublicationDate().isEmpty()){
                Integer a = Integer.valueOf(judgement.getSource().getPublicationDate().substring(5,7));
                judgementsPerMonth[a-1]++;
            }
        }
        return judgementsPerMonth;
    }
    //VIII
    public Map<CourtType,Integer> courtTypeDistribution(){
        Collection<Judgement> judgements = judgementHashMap.values();
        Map<CourtType,Integer> distribution = new HashMap<>();
        for(Judgement judgement: judgements){
            if(!distribution.containsKey(judgement.getCourtType())){
                distribution.put(judgement.getCourtType(),1);
            }
            else{
                Integer a = distribution.get(judgement.getCourtType());
                distribution.replace(judgement.getCourtType(),distribution.get(judgement.getCourtType()),distribution.get(judgement.getCourtType())+1);
            }
        }
        return distribution;
    }
    //IX
    public List<String> top10laws(){
        Map<String,Integer> judgeHashMap = new LinkedHashMap<>();
        for(Judgement judgement : judgementHashMap.values()){
            for (ReferencedRegulation regulation : judgement.getReferencedRegulations()){
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
        Collection<Judgement> judgements = judgementHashMap.values();
        double judges = 0;
        for(Judgement judgement : judgements){
            judges += judgement.getJudges().size();
        }
        return judges/judgements.size();
    }

}