package judgments;

import java.util.*;
import java.util.stream.Collectors;

import judgments.ApiModel.Attributes.CourtCase;
import com.google.gson.Gson;
import judgments.ApiModel.Judgment;
import judgments.ApiModel.JudgmentsResponse;


public class JSONParser {
    public static List<Judgment> parse(String json){
        return new Gson().fromJson(json, JudgmentsResponse.class).getJudgments();
    }

    public List<Judgment> parseFiles(ArrayList<String> filesContents) {
        return filesContents.stream()
                .flatMap(file -> parse(file).stream())
                .collect(Collectors.toList());
    }

    public HashMap<String,Judgment> parseToMap(ArrayList<String> filesContents){
        HashMap<String,Judgment> map = new HashMap<>();
        List<Judgment> judgments = parseFiles(filesContents);
        for(Judgment Judgment: judgments) {
            for(CourtCase courtCase : Judgment.getCourtCases()) {
                map.put(courtCase.getCaseNumber(), Judgment);
            }
        }
        return map;
    }
}