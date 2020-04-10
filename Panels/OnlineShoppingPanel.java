package ElaheHosseini_HW10_Maktab33.Panels;

import ElaheHosseini_HW10_Maktab33.Customer.dto.Customer;
import ElaheHosseini_HW10_Maktab33.ProductStore.dao.ProductDao;
import ElaheHosseini_HW10_Maktab33.ProductStore.dto.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineShoppingPanel {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        actionController();
    }

    public static Customer authentication() {
        Customer customer = null;
        boolean validUser = false;
        UserLoginPanel userLoginPanel = new UserLoginPanel();
        UserSignUpPanel userSignUpPanel = new UserSignUpPanel();
        while (!validUser) {
            System.out.println("Enter '1' for login and '2' for signUp:");
            String input = scanner.next();
            switch (input) {
                case "1":
                    try {
                        customer = userLoginPanel.login();
                        validUser = true;
                    } catch (AuthenticationFailedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    customer = userSignUpPanel.signUp();
                    validUser = true;
                    break;
                default:
                    continue;
            }
        }
        return customer;
    }

    public static ArrayList<Product> allStoreProducts() {
        ProductDao productDao = new ProductDao();
        ArrayList<Product> allProducts = productDao.availableProducts();
        return allProducts;
    }

    public static void actionController() {
        Customer customer = authentication();
        boolean endExploring = false;
        ArrayList<Product> allProducts = allStoreProducts();
        while (!endExploring) {
            String key = "";
            while (!key.equals("1") && !key.equals("2") && !key.equals("3") && !key.equals("4")) {
                System.out.println("\nPlease Choose One.");
                System.out.println("* Go For A Shop: ==> 1");
                System.out.println("* Managing Your Basket: ==> 2");
                System.out.println("* Cancel Shopping: ==> 3");
                System.out.println("* Accept Shopping: ==> 4");

                key = scanner.next();
            }
            switch (key) {
                case "1":
                    shopping(customer, allProducts);
                    break;
                case "2":
                    basketManager(customer, allProducts);
                    break;
                case "3":
                    emptyBasket(customer);
                    endExploring = true;
                    break;
                case "4":
                    endShopping(customer);
                    endExploring = true;
            }
        }
    }

    public static void shopping(Customer customer, ArrayList<Product> allProducts) {

        int count = 1;
        for (Product product : allProducts
        ) {
            System.out.println((count++) + "-" + product.toString());
        }
        System.out.println("Witch Product You Want To Buy Just Enter The Proper Number Or Enter 'q' For Backing To The Menu:");
        String input = scanner.next();
        if (input.equals("q")) return;
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("__BAD INPUT!");
            return;
        }
        System.out.println("How Many? ");
        int number = scanner.nextInt();
        if (number > allProducts.get(index - 1).getNumOfAvailableProducts()) {
            System.out.println("__UNFORTUNATELY THIS MUCH OF THIS PRODUCT DOES'T EXIST!");
            return;
        }
        Product newProduct = null;
        try {
            newProduct = (Product) allProducts.get(index - 1).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        newProduct.setNumOfAvailableProducts(number);
        try {
            customer.addToBasket(newProduct);
        } catch (Exception e) {
            e.getMessage();
        }
        int newNumOfAvailableProducts = allProducts.get((index - 1)).getNumOfAvailableProducts() - number;
        allProducts.get(index - 1).setNumOfAvailableProducts(newNumOfAvailableProducts);
        System.out.println("__ADDED TO BASKET SUCCESSFULLY.");
    }

    public static void basketManager(Customer customer, ArrayList<Product> allProducts) {
        boolean backToShopping = false;
        while (!backToShopping) {
            {
                String key = "";
                while (!key.equals("1") && !key.equals("2") && !key.equals("3")) {
                    System.out.println("\nPlease Choose One.");
                    System.out.println("* Showing All Basket Products: ==> 1");
                    System.out.println("* Delete Some Product From Basket: ==> 2");
                    System.out.println("* Back To Shopping: ==> 3");
                    key = scanner.next();
                }
                switch (key) {
                    case "1":
                        showBasket(customer);
                        break;
                    case "2":
                        deleteFromBasket(customer, allProducts);
                        break;
                    case "3":
                        backToShopping = true;
                        break;
                }
            }
        }
    }

    public static void showBasket(Customer customer) {
        float totalPayment = 0;
        int count = 1;
        for (Product product : customer.getBasket()
        ) {
            System.out.println((count++) + "-" + product.basketProductToString());
            totalPayment += product.getPrice() * product.getNumOfAvailableProducts();
        }
        System.out.println("Total Price: " + totalPayment);
    }

    public static void deleteFromBasket(Customer customer, ArrayList<Product> allProducts) {
        showBasket(customer);
        System.out.println("\nWitch Product You Want To Delete From Your Basket, Enter The Proper Number Or Enter 'q' For Backing To The Menu:");
        String input = scanner.next();
        if (input.equals("q")) return;
        int index = 0;
        try {
            index = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("__BAD INPUT!");
            return;
        }
        try {
            getBackProductFromRemovedBasket(customer.getBasket().get(index - 1), allProducts);
            customer.getBasket().remove(index - 1);
            System.out.println("__REMOVED FROM BASKET SUCCESSFULLY.");
        } catch (Exception e) {
            return;
        }
    }

    public static void endShopping(Customer customer) {
        if (customer.getBasket().size() > 0) {
            ProductDao productDao = new ProductDao();
            try {
                productDao.updateStoreAfterShopping(customer.getBasket());
                emptyBasket(customer);
                System.out.println("__THANKS FOR YOUR SHOPPING__");
            } catch (SQLException e) {
                System.out.println("__SHOPPING FAILED!__");
            }

        }
    }

    public static void emptyBasket(Customer customer) {
        ArrayList<Product> basket = customer.getBasket();
        basket.removeAll(basket);
        customer.setBasket(basket);
    }

    public static void getBackProductFromRemovedBasket(Product product, ArrayList<Product> allProducts) {
        for (Product product1 : allProducts
        ) {
            if (product1.equals(product)) {
                product1.setNumOfAvailableProducts(product1.getNumOfAvailableProducts() + product.getNumOfAvailableProducts());
            }
        }
    }
}
