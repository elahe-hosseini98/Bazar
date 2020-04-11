package ElaheHosseini_HW12_Maktab33.Q1;

import java.util.Comparator;

public class IntComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer num1, Integer num2) {
        return num1 == num2 ? 0 : num1 > num2 ? 1 : -1;
    }
}
