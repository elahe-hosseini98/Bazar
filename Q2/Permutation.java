package ElaheHosseini_HW12_Maktab33.Q2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Permutation {

    public ArrayList<String> printAllPermutation(String word) {
        ArrayList<String> allPermutation = new ArrayList<>();
        //todo
        return allPermutation;
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
