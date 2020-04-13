package ElaheHosseini_HW12_Maktab33.Q2;

import java.util.Scanner;

public class Test {
    static String separatorLine = "_________________________________________";
    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        haveSamePermutationsTest(permutation);
        System.out.println(separatorLine);
    }

    public static void haveSamePermutationsTest(Permutation permutation) {
        String word1 = getStringFromUser();
        String word2 = getStringFromUser();
        if (permutation.haveSamePermutations(word1, word2)) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }

    public static String getStringFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a String of characters:");
        return scanner.next();
    }
}