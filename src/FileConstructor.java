import java.nio.file.Path;

public class FileConstructor {

    public Path FileName(Path filePath, String text) {

        var fileNameStrings = String.valueOf(filePath.getFileName()).split("\\.");
        var fileNameExtension = fileNameStrings[fileNameStrings.length - 1];
        var fileName = fileNameStrings[0].split("\\[");

        return filePath.getParent().resolve(Path.of(fileName[0] + text +"." + fileNameExtension));
    }
}
