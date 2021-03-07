package judgments;

import judgments.ApiModel.Judgment;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.HashMap;

public class App {

    public static void main(String[] args) throws IOException{
        HashMap<String, Judgment> map = new HashMap<>();
//        map.putAll(jsonJudgments());
//        map.putAll(htmlJudgments());
        Model model = new Model(map);
        ApiDownloader apiDownloader = new ApiDownloader();
        Controller controller = new Controller(model, apiDownloader);
    }

//    static HashMap<String, Judgment> jsonJudgments() throws IOException {
//        FileOpener jsonFileOpener = new FileOpener();
//        JSONParser jsonParser = new JSONParser();
//        ArrayList<String> content = jsonFileOpener.getFiles("JSON");
//        List<Judgment> judgmentList = jsonParser.parseFiles(content);
//        return jsonParser.parseToMap(judgmentList);
//    }
//
//    static HashMap<String, Judgment> htmlJudgments() throws IOException {
//        HTMLParser htmlParser = new HTMLParser();
//        HTMLFileOpener htmlFileOpener = new HTMLFileOpener();
//        ArrayList<String> content1 = htmlFileOpener.getFilesContent("HTML");
//        ArrayList<Judgment> judgments = htmlParser.parseFiles(content1);
//        List<Judgment> judgmentList = jsonParser.parseFiles(content);
//        return jsonParser.parseToMap(judgmentList);
//    }
}