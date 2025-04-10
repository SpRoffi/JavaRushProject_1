import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class CharCrypt {
    ArrayList<Character> englishAlphabet = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    int j;
    Character character;

    public ByteBuffer enCrypt(ByteBuffer buffer, int k) {
        for (int i = 0; i < buffer.capacity(); i++) {
            character = (char) buffer.get(i);

            if (englishAlphabet.contains(character)) buffer.put(i, (byte) codingChar(englishAlphabet, k));
        }
        return buffer;
    }

    private char codingChar(ArrayList<Character> list, int k) {
        j = list.indexOf(character);
        j = (j + k) % englishAlphabet.size();
        if (j < 0) j = j + englishAlphabet.size();
        return list.get(j);
    }
}
