import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args){
        try{
            FileOpener x = new FileOpener();
            ArrayList<String> content = x.getFiles("C:\\JSON");
            System.out.println(content);
        }catch (IOException e){
            System.out.println("coś nie wyszło");
        }

    }
}
