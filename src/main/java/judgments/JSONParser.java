package judgments;

import java.lang.reflect.Type;
import java.util.*;

import judgments.Model.CourtCase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import judgments.Model.Judgment;


public class JSONParser {
    /*
    parsuje plik JSON do kilku obiektów Judgment
     */
    public ArrayList<Judgment> parse(String fileContent){
        Gson gson = new Gson();
        Type JudgmentListType = new TypeToken<ArrayList<Judgment>>(){}.getType();
        return gson.fromJson(fileContent,JudgmentListType);
    }
    /*
    parsuje wszystkie pliki JSON
     */
    public List<List<Judgment>> parseAllFiles(ArrayList<String> filesContents){
        List<List<Judgment>> allJudgments = new ArrayList<>();
        for(String file : filesContents){
            allJudgments.add(parse(file));
        }
        return allJudgments;
    }
    /*
    wkłada obiekty Judgment do mapy
     */
    public HashMap<String,Judgment> parseToMap(ArrayList<String> filesContents){
        HashMap<String,Judgment> map = new HashMap<>();
        List<List<Judgment>> judgments = parseAllFiles(filesContents);
        for(List<Judgment> list : judgments){
            for(Judgment Judgment: list){
                for(CourtCase courtCase : Judgment.getCourtCases()){
                    map.put(courtCase.getCaseNumber(),Judgment);
                }
            }
        }
        return map;
    }
}