import exceptions.CommandsException;
import exceptions.FileException;
import exceptions.ParametersException;
import exceptions.ShiftException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CaesarCipher {

    public static Path inputFile;
    static int shift;

    public void run(String[] args) {

        if (validateParameters(args)) {
            inputFile = Path.of(args[1]);
            if ("BRUTE_FORCE".equalsIgnoreCase(args[0])) {
                bruteForce(inputFile);
            } else {
                if (validateShift(args)) {
                    if ("ENCRYPT".equalsIgnoreCase(args[0])) {
                        enCrypt(new FileConstructor().FileName(inputFile, "[ENCRYPTED]"));
                    } else {
                        deCrypt(new FileConstructor().FileName(inputFile, "[DECRYPTED]"));
                    }
                }
            }
        }
    }

    public static void enCrypt(Path outputFile) {
        try {
            if (Files.notExists(outputFile)) Files.createFile(outputFile);
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
        new FileService().readFromFile(inputFile, outputFile, shift);
    }

    public static void deCrypt(Path outputFile) {
        shift = -shift;
        enCrypt(outputFile);
    }

    public static void bruteForce(Path inputFile) {
        shift = new FileService().getBestShift(inputFile);
        deCrypt(new FileConstructor().FileName(inputFile, "[KEY - " + shift + "]"));
    }

    private boolean validateParameters(String[] parameters) {
        if (parameters == null || parameters.length < Constants.minParameters) {
            throw new ParametersException("Invalid parameters");
        } else if (!Constants.Commands.isCommandValid(parameters[0])) {
            throw new CommandsException("The command is not valid");
        } else if (!Files.isRegularFile(Path.of(parameters[1])) || Files.notExists(Path.of(parameters[1]))) {
            throw new FileException("File path is invalid");
        }
        return true;
    }

    private boolean validateShift(String[] parameters) {
        if (parameters.length < Constants.maxParameters) {
            throw new ShiftException("The encryption key is missing");
        } else {
            try {
                shift = Integer.parseInt(parameters[2]);
                return true;
            } catch (NumberFormatException e) {
                throw new ShiftException(e.getMessage());
            }
        }
    }
}
