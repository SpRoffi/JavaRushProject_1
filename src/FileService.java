import exceptions.FileException;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class FileService {

    static FileChannel inputChannel, outputChannel;
    static ByteBuffer byteBuffer = ByteBuffer.allocate(Byte.MAX_VALUE);
    static StringBuilder bruteForceBuffer = new StringBuilder();
    static CharCrypt charCrypt = new CharCrypt();
    static Map<Integer, String> candidates = new HashMap<>();
    static Set<String> commonWords = new Constants().commonWords;
    static StringBuilder builder = new StringBuilder();


    public void readFromFile(Path inputFile, Path outputFile, int key) {
        try {
            inputChannel = FileChannel.open(inputFile);
            outputChannel = FileChannel.open(outputFile, StandardOpenOption.WRITE);

            while (inputChannel.read(byteBuffer) > 0) {
                byteBuffer = charCrypt.enCrypt(byteBuffer, key);
                byteBuffer.flip();
                outputChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (Exception e) {
            throw new FileException(e.getMessage());
        }
        System.out.println("Completed");
    }

    public int getBestShift(Path inputFile) {
        try {
            inputChannel = FileChannel.open(inputFile);
            inputChannel.read(byteBuffer);
            inputChannel.close();
            for (int i = 0; i < Byte.MAX_VALUE; i++) {
                bruteForceBuffer.append((char) byteBuffer.get(i));
            }
            for (int shift = 1; shift < new Constants().getSizeEnglishAlphabet() - 1; shift++) {
                byteBuffer = charCrypt.enCrypt(byteBuffer, shift);
                for (int i = 0; i < Byte.MAX_VALUE; i++) {
                    builder.append((char) byteBuffer.get(i));
                }
                candidates.put(shift, builder.toString());
                for (int i = 0; i < Byte.MAX_VALUE; i++) {
                    byteBuffer.put(i, (byte) bruteForceBuffer.charAt(i));
                }
            }
        } catch (Exception e) {
            throw new FileException(e.getMessage());
        }
        byteBuffer.clear();
        return guessMostLikelyShift(candidates);
    }

    private static int guessMostLikelyShift(Map<Integer, String> candidates) {
        int bestShift = 0;
        int maxWordHits = 0;

        for (Map.Entry<Integer, String> entry : candidates.entrySet()) {
            int hits = 0;
            String[] words = entry.getValue().toLowerCase().split("\\W+");
            for (String word : words) {
                if (commonWords.contains(word)) {
                    hits++;
                }
            }
            if (hits > maxWordHits) {
                maxWordHits = hits;
                bestShift = entry.getKey();
            }
        }
        return new Constants().getSizeEnglishAlphabet() - bestShift;
    }
}