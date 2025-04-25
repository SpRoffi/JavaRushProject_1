import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class Constants {

    enum Commands {
        ENCRYPT,
        DECRYPT,
        BRUTE_FORCE;

        public static boolean isCommandValid(String str) {
            boolean tmp = false;
            for (Commands s: Commands.values()){
                if (str.equalsIgnoreCase(s.toString())) tmp = true;
            }
            return tmp;
        }
    }

    static final int maxParameters = 3;
    static final int minParameters = 2;


    private final ArrayList<Character> englishAlphabet = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

    public ArrayList<Character> getEnglishAlphabet() {
        return englishAlphabet;
    }

    public int getSizeEnglishAlphabet(){ return englishAlphabet.size(); }

    public Set<String> commonWords = new HashSet<>(Arrays.asList("the", "and", "to", "of", "in", "is", "that", "it",
            "for", "on"));

}
