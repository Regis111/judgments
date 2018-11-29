import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args){
        try{
            FileOpener x = new FileOpener();
            Parser parser = new Parser();
            ArrayList<String> content = x.getFiles("C:\\JSON");
            List<List<Judgement>> judgements = parser.parseAllFiles(content);
        }catch (IOException e){
            System.out.println("coś nie wyszło");
        }

    }
}
