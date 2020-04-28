package ElaheHosseini_HW12_Maktab33.Panels;

import ElaheHosseini_HW12_Maktab33.Customer.dao.CustomerDao;
import ElaheHosseini_HW12_Maktab33.Customer.dto.AgeComparator;
import ElaheHosseini_HW12_Maktab33.Customer.dto.Customer;
import ElaheHosseini_HW12_Maktab33.ProductStore.dao.ProductDao;
import ElaheHosseini_HW12_Maktab33.ProductStore.dto.Product;
import ElaheHosseini_HW12_Maktab33.ProductStore.dto.ProductType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ProductStoreAdminPanel {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input = "";
        while (!input.equals("1") && !input.equals("2")) {
            System.out.println("Please Enter '1' for Adding new product, or '2' for displaying all Customers ordered by their age...");
            input = scanner.next();
        }
        switch (input) {
            case "1":
                addingNewProductIntoStore();
                break;
            case "2":
                showAllCustomersOrderedByAge();
                break;
        }
    }

    public static void addingNewProductIntoStore() {
        ProductDao productDao = new ProductDao();
        String productName, brand, technicalSpecification;
        float price;
        ProductType productType = null;
        Product product;
        int numOfNewProduct;

        System.out.println("__ADMIN PANEL__");
        System.out.println("Product Type:");
        String input = "";
        while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
            System.out.println("Please Enter '1' for Electrical, '2' for Shoes and '3' for Stationery Products...");
            input = scanner.next();
        }
        switch (input) {
            case "1":
                productType = ProductType.Electrical;
                break;
            case "2":
                productType = ProductType.Shoes;
                break;
            case "3":
                productType = ProductType.Stationery;
                break;
        }
        System.out.println("Product Name:");
        String input2 = scanner.next();
        productName = input2 + scanner.nextLine();
        System.out.println("Product Brand");
        brand = scanner.nextLine();
        System.out.println("Number Of Products You Want To Add To The Store:");
        numOfNewProduct = scanner.nextInt();
        product = new Product(productType, 0, productName, brand, null);
        int numOfStoredProduct = productDao.productCount(product);
        if (numOfStoredProduct > 0) {
            System.out.println("__NUMBER OF AVAILABLE PRODUCT INCREASED BY " + numOfNewProduct);
            System.out.println("Available Same Product: " + (numOfStoredProduct + numOfNewProduct));
            productDao.productCountIncrementer(product, numOfStoredProduct, numOfNewProduct);
        } else {
            System.out.println("price: ");
            price = scanner.nextFloat();
            System.out.println("Technical Specification:");
            scanner.next();
            technicalSpecification = scanner.nextLine();
            product = new Product(productType, price, productName, brand, technicalSpecification);
            boolean insertResult = productDao.insert(product, numOfNewProduct);
            if (insertResult) {
                System.out.println("__PRODUCT INSERTED SUCCESSFULLY!");
            }
        }
    }

    public static void showAllCustomersOrderedByAge() {
        CustomerDao customerDao = new CustomerDao();
        ArrayList<Customer> allCustomers = customerDao.getAllCustomers();
        allCustomers.sort(new AgeComparator());
        for (Customer customer : allCustomers
        ) {
            System.out.println(customer);
        }
    }
}
