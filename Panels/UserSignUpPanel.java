package ElaheHosseini_HW10_Maktab33.Panels;

import ElaheHosseini_HW10_Maktab33.Customer.dao.CustomerDao;
import ElaheHosseini_HW10_Maktab33.Customer.dto.Customer;

import java.util.Scanner;

public class UserSignUpPanel {
    Scanner scanner = new Scanner(System.in);

    public Customer signUp() {
        System.out.println("__SING-UP FORM__");
        String firstName, lastName, phoneNumber, email, address;
        System.out.println("First Name:");
        firstName = scanner.next();
        System.out.println("Last Name:");
        lastName = scanner.next();
        System.out.println("Phone-Number:");
        phoneNumber = scanner.next();
        System.out.println("Email:");
        email = scanner.next();
        System.out.println("Address:");
        address = scanner.next();
        Customer customer = new Customer(firstName, lastName, phoneNumber, email, address);
        System.out.println(customer.getAddress());
        CustomerDao customerDao = new CustomerDao();
        try {
            boolean insertResult = customerDao.insert(customer);
            if (insertResult) {
                System.out.println("__YOU SIGNED UP SUCCESSFULLY!");
                System.out.println("Welcome " + customer.getFirstName() + " " + customer.getLastName() + "!");
                return customer;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
