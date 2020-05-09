package ElaheHosseini_HW12_Maktab33.HW12_Q2;

import java.util.HashMap;

public class Permutation {

    public void getAllPermutation(String word) {
        printPermutation(word, "");
    }

    public void printPermutation(String str, String ans) {
        if (str.length() == 0) {
            System.out.print(ans + " ");
            return;
        }

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            String ros = str.substring(0, i) +
                    str.substring(i + 1);

            printPermutation(ros, ans + ch);
        }

    }

    public boolean haveSamePermutations(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        HashMap<Character, Integer> word1CharFreq = getCharFrequency(word1);
        HashMap<Character, Integer> word2CharFreq = getCharFrequency(word2);
        for (char character : word1CharFreq.keySet()
        ) {
            if (word1CharFreq.get(character) - word2CharFreq.getOrDefault(character, -1) != 0) return false;
        }
        return true;
    }

    public HashMap<Character, Integer> getCharFrequency(String word) {
        HashMap<Character, Integer> charFrequency = new HashMap<>();
        for (char character : word.toCharArray()
        ) {
            charFrequency.put(character, charFrequency.getOrDefault(character, 0) + 1);
        }
        return charFrequency;
    }
}
