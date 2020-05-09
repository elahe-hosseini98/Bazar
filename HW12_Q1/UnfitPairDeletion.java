package ElaheHosseini_HW12_Maktab33.HW12_Q1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class UnfitPairDeletion {
    public static void main(String[] args) {
        IntComparator comparator = new IntComparator();
        ArrayList<Integer> pairs = new ArrayList<>();
        fillTheList(pairs);
        System.out.println("Result List: " + deleteUnfitPairs(pairs, comparator).toString());
    }

    public static ArrayList<Integer> fillTheList(ArrayList<Integer> integerArrayList) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("q")) {
            System.out.println("Please enter an Integer number or enter 'q' to end...");
            input = scanner.next();
            if (!input.equals("q")) {
                try {
                    integerArrayList.add(Integer.parseInt(input));
                } catch (Exception e) {
                    System.out.println("__BAD INPUT!__");
                }
            }
        }
        return integerArrayList;
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
