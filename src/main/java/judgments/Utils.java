package judgments;

import com.google.gson.Gson;
import judgments.ApiModel.Attributes.CourtCase;
import judgments.ApiModel.Judgment;
import judgments.ApiModel.JudgmentsResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class Utils {
    public static List<Judgment> parseJson(String json) {
        return new Gson().fromJson(json, JudgmentsResponse.class).getJudgments();
    }

    public static List<Judgment> parseFiles(ArrayList<String> filesContents) {
        return filesContents.stream()
                .flatMap(file -> parseJson(file).stream())
                .collect(Collectors.toList());
    }

    public static HashMap<String, Judgment> parseToMap(List<Judgment> judgmentList) {
        HashMap<String, Judgment> map = new HashMap<>();
        for (Judgment Judgment : judgmentList) {
            for (CourtCase courtCase : Judgment.getCourtCases()) {
                map.put(courtCase.getCaseNumber(), Judgment);
            }
        }
        return map;
    }
}