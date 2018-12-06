package judgments;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

import judgments.Attributes.CourtCase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Parser {
    public ArrayList<Judgement> parse(String file) throws IOException{
        Gson gson = new Gson();
        Type judgementListType = new TypeToken<ArrayList<Judgement>>(){}.getType();
        return gson.fromJson(file,judgementListType);
    }
    public List<List<Judgement>> parseAllFiles(ArrayList<String> files) throws IOException{
        List<List<Judgement>> allJudgments = new ArrayList<>();
        for(String file : files){
            allJudgments.add(parse(file));
        }

        return allJudgments;
    }
    public HashMap<String,Judgement> parseToMap(ArrayList<String> files) throws IOException{
        HashMap<String,Judgement> map = new HashMap<>();
        List<List<Judgement>> judgments = parseAllFiles(files);
        for(List<Judgement> list : judgments){
            for(Judgement judgement: list){
                for(CourtCase courtCase : judgement.getCourtCases())
                    map.put(courtCase.getCaseNumber(),judgement);
            }
        }
        return map;
    }
}
