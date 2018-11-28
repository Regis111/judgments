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

    public ArrayList<String> fromPathListToStringList(String JsonDirectory) throws IOException{
        ArrayList<String> StringPaths = new ArrayList<>();
        ArrayList<Path> PathPaths = this.getJsonList(JsonDirectory);
        for(Path x : PathPaths){
            StringPaths.add(x.toString());
        }
        return StringPaths;
    }

    /*
    Zwraca zawartość plików JSON w postaci listy stringów
     */
    public ArrayList<String> getFiles(String DirPath) throws IOException {
        ArrayList<String> FileContent = new ArrayList<>();
        ArrayList<Path> Paths = getJsonList(DirPath);
        for (Path path : Paths){
            FileContent.add(new String(Files.readAllBytes(path),"UTF-8"));
        }
        return FileContent;
    }
}