package ElaheHosseini_HW10_Maktab33.Customer.dto;

import ElaheHosseini_HW10_Maktab33.ProductStore.dto.Product;

import java.util.ArrayList;

public class Customer {
    private String firstName, lastName, phoneNumber, email, address;
    private ArrayList<Product> basket = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstName, String lastName, String phoneNumber, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Product> getBasket() {
        return basket;
    }

    public void addToBasket(Product product) throws BasketOverFlowException {
        int basketMaxLength = 5;
        int basketCurrentSize = 0;
        for (Product p : basket
        ) {
            basketCurrentSize += p.getNumOfAvailableProducts();
        }
        if (basketCurrentSize < basketMaxLength) {
            this.basket.add(product);
        } else if (basketCurrentSize == 5) {
            throw new BasketOverFlowException("__YOUR BASKET IS FULL, YOU CAN'T ADD ANY MORE PRODUCT!");
        } else
            throw new BasketOverFlowException("__YOU CAN ADD ONLY " + (basketMaxLength - basketCurrentSize) +
                    " MORE PRODUCT TO YOUR BASKET!");
    }

    public void setBasket(ArrayList<Product> basket) {
        this.basket = basket;
    }
}

class BasketOverFlowException extends Exception {
    public BasketOverFlowException(String message) {
        super(message);
    }
}
