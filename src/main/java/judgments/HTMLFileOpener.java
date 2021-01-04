package judgments;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class HTMLFileOpener {
    /*
    Zwraca ścieżki plików HTML
     */
    private ArrayList<Path> getFilesPaths(String HtmlDirectory) throws IOException {
        Path dir = Paths.get(HtmlDirectory);
       return Files.list(dir).collect(Collectors.toCollection(ArrayList::new));
    }
    /*
    Zwraca zawartość plików HTML
     */
    public ArrayList<String> getFilesContent(String HtmlDirectory) throws IOException {
        ArrayList<String> filesContent = new ArrayList<>();
        ArrayList<Path> paths = getFilesPaths(HtmlDirectory);
        for(Path path : paths){
            ArrayList<Path> paths1 = getFilesPaths(path.toString());
            for(Path path1 : paths1){
                ArrayList<Path> paths2 = getFilesPaths(path1.toString());
                for(Path path2 : paths2){
                    String fileContent = new String(Files.readAllBytes(path2), StandardCharsets.UTF_8);
                    fileContent = Jsoup.parse(fileContent,"UTF-8").body().toString();
                    filesContent.add(fileContent);
                }
            }
        }
        return filesContent;
    }
}
