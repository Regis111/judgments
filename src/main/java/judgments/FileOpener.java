package judgments;

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
    private ArrayList<Path> getJsonPathList(String jsonDirectory) throws IOException {
        Path dir = Paths.get(jsonDirectory);
        return Files.list(dir).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
    Zwraca zawartość plików JSON w postaci listy stringów
     */
    public ArrayList<String> getFiles(String DirPath) throws IOException {
        ArrayList<String> fileContent = new ArrayList<>();
        ArrayList<Path> paths = getJsonPathList(DirPath);
        for (Path path : paths) {
            String file = Files.readString(path);
            fileContent.add(file);
        }
        return fileContent;
    }
}
