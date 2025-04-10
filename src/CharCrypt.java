import java.nio.ByteBuffer;
import java.util.ArrayList;

public class CharCrypt {

    ArrayList<Character> eng = new Constants().getEnglishAlphabet();
    int j;
    Character character;

    public ByteBuffer enCrypt(ByteBuffer buffer, int k) {
        for (int i = 0; i < buffer.capacity(); i++) {
            character = (char) buffer.get(i);
            //englishAlphabet
            if (eng.contains(character)) {
                buffer.put(i, (byte) codingChar(eng, k));
            }
        }
        return buffer;
    }

    private char codingChar(ArrayList<Character> list, int k) {
        j = list.indexOf(character);
        j = (j + k) % eng.size();
        if (j < 0) j = j + eng.size();
        return list.get(j);
    }
}
