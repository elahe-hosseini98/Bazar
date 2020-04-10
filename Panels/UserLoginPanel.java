package ElaheHosseini_HW10_Maktab33.Panels;

import ElaheHosseini_HW10_Maktab33.Customer.dao.CustomerDao;
import ElaheHosseini_HW10_Maktab33.Customer.dto.Customer;

import java.util.Scanner;

public class UserLoginPanel {
    Scanner scanner = new Scanner(System.in);

    public Customer login() throws AuthenticationFailedException {
        System.out.println("__LOGIN FORM__");
        System.out.println("Please Enter your email:");
        String email = scanner.next();
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.customerInfo(email);
        if (customer != null) {
            System.out.println("Welcome " + customer.getFirstName() + " " + customer.getLastName() + "!");
            return customer;
        } else {
            throw new AuthenticationFailedException("__USER NOT FOUND! You May Need To Sign-Up first!");
        }
    }
}

class AuthenticationFailedException extends Exception {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
