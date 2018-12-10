package judgments;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

import judgments.Attributes.CourtCase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class JSONParser {
    public ArrayList<Judgment> parse(String file) throws IOException{
        Gson gson = new Gson();
        Type JudgmentListType = new TypeToken<ArrayList<Judgment>>(){}.getType();
        return gson.fromJson(file,JudgmentListType);
    }
    public List<List<Judgment>> parseAllFiles(ArrayList<String> files) throws IOException{
        List<List<Judgment>> allJudgments = new ArrayList<>();
        for(String file : files){
            allJudgments.add(parse(file));
        }
        return allJudgments;
    }
    public HashMap<String,Judgment> parseToMap(ArrayList<String> files) throws IOException{
        HashMap<String,Judgment> map = new HashMap<>();
        List<List<Judgment>> judgments = parseAllFiles(files);
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
