import java.nio.ByteBuffer;
import java.util.ArrayList;

public class CharCrypt {

    Constants constants = new Constants();
    int j;
    Character character;

    public ByteBuffer enCrypt(ByteBuffer buffer, int k) {
        for (int i = 0; i < buffer.capacity(); i++) {
            character = (char) buffer.get(i);
            //englishAlphabet
            if (constants.getEnglishAlphabet().contains(character)) {
                buffer.put(i, (byte) codingChar(constants.getEnglishAlphabet(), k));
            }
        }
        return buffer;
    }

    private char codingChar(ArrayList<Character> list, int k) {
        j = list.indexOf(character);
        j = (j + k) % constants.getEnglishAlphabet().size();
        if (j < 0) j = j + constants.getEnglishAlphabet().size();
        return list.get(j);
    }
}
