import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class Coding {
    ArrayList<Character> englishLowercaseAlphabet = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    ArrayList<Character> englishUppercaseAlphabet = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
    int j;
    Character character;

    public ByteBuffer doCoding(ByteBuffer buffer, int k) {
        for (int i = 0; i < buffer.capacity(); i++) {
            character = (char) buffer.get(i);

            if (englishLowercaseAlphabet.contains(character)) {
                buffer.put(i, (byte) codingChar(englishLowercaseAlphabet, k));
                continue;
            }
            if (englishUppercaseAlphabet.contains(character)) {
                buffer.put(i, (byte) codingChar(englishUppercaseAlphabet, k));
            }
        }
        return buffer;
    }

    private char codingChar(ArrayList<Character> list, int k) {
        j = list.indexOf(character);
        j = (j + k) % 26;
        if (j < 0) j = j + 26;
        return list.get(j);
    }
}