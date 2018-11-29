import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import data.JudgementAttributes.Judge;

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
}
