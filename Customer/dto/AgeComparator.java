package ElaheHosseini_HW12_Maktab33.Customer.dto;

import java.util.Comparator;

public class AgeComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getAge() - o2.getAge();
    }
}
