package ElaheHosseini_HW12_Maktab33.Q1;

import java.util.ArrayList;
import java.util.Comparator;

public class UnfitPairDeletion {
    public static void main(String[] args) {
        IntComparator comparator = new IntComparator();
        ArrayList<Integer> pairs = new ArrayList<>();
        pairs.add(9);
        pairs.add(2);
        pairs.add(3);
        pairs.add(4);
        pairs.add(2);
        pairs.add(2);
        pairs.add(1);
        System.out.println(deleteUnfitPairs(pairs, comparator).toString());
    }

    public static ArrayList<Integer> deleteUnfitPairs(ArrayList<Integer> pairs, Comparator comparator) {
        ArrayList<Integer> editedPairs = new ArrayList<>();
        if (pairs.size() % 2 != 0) pairs.remove(pairs.size() - 1);
        for (int i = 0; i <= pairs.size() - 2; i += 2) {
            if (comparator.compare(pairs.get(i), pairs.get(i + 1)) != 1) {
                editedPairs.add(pairs.get(i));
                editedPairs.add(pairs.get(i + 1));
            }
        }
        return editedPairs;
    }

}
