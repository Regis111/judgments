import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import data.JudgementAttributes.CourtCase;
import data.JudgementAttributes.Judge;

public class Parser {
    private ArrayList<Judgement> parse(String file) throws IOException{
        Type judgementListType = new TypeToken<ArrayList<Judgement>>(){}.getType();
        Gson gson = new Gson();
        return gson.fromJson(file,judgementListType);
    }
    private List<List<Judgement>> parseAllFiles(ArrayList<String> files) throws IOException{
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
                    map.put(courtCase.caseNumber,judgement);
            }
        }
        return map;
    }
}
