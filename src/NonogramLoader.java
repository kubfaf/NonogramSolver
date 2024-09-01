import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class NonogramLoader {


    public static List<String> loadNonogram(File f) {

        List<String> lines = null;
        try {
            System.out.println("Trying to read " + f.getPath());
            lines = Files.readAllLines(Path.of(f.getPath()));
            System.out.println("Reading done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return lines;

    }
}
