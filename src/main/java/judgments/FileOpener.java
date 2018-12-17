package judgments;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FileOpener {
    /*
    Zwraca ścieszki plików w folderze JSON
     */
    private ArrayList<Path> getJsonList(String JsonDirectory) throws IOException{
        Path dir = Paths.get(JsonDirectory);
        return Files.list(dir).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
    Zwraca zawartość plików JSON w postaci listy stringów
     */
    public ArrayList<String> getFiles(String DirPath) throws IOException {
        ArrayList<String> fileContent = new ArrayList<>();
        ArrayList<Path> paths = getJsonList(DirPath);
        for (Path path : paths){
            String file = new String(Files.readAllBytes(path),"UTF-8");
            file = removeMetaData(file);
            fileContent.add(file);
        }
        return fileContent;
    }
    /*
    Usuwa meta dane z pliku JSON
     */
    private String removeMetaData(String file){
        int beg = file.indexOf("items") + 7;
        int end = file.indexOf("queryTemplate") - 2;
        return file.substring(beg,end);
    }
}