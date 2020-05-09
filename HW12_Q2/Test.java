package ElaheHosseini_HW12_Maktab33.HW12_Q2;

import java.util.Scanner;

public class Test {
    static String separatorLine = "_________________________________________";
    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        printAllPermutation(permutation);
        haveSamePermutationsTest(permutation);
        System.out.println(separatorLine);
    }

    public static void haveSamePermutationsTest(Permutation permutation) {
        System.out.println("Checking equality of two strings:");
        String word1 = getStringFromUser();
        String word2 = getStringFromUser();
        if (permutation.haveSamePermutations(word1, word2)) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }

    public static void printAllPermutation(Permutation permutation) {
        System.out.println("Print All Permutation for a String:");
        String word =  getStringFromUser();
        permutation.getAllPermutation(word);
    }

    public static String getStringFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a String:");
        return scanner.next();
    }
}