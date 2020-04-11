package ElaheHosseini_HW12_Maktab33.ProductStore.dto;

import java.util.Objects;

public class Product implements Cloneable {
    private ProductType productType;
    private float price;
    private String productName, brand, technicalSpecification;
    private int NumOfAvailableProducts;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Product() {
    }

    public Product(ProductType productType, float price, String productName, String brand, String technicalSpecification) {
        this.productType = productType;
        this.price = price;
        this.productName = productName;
        this.brand = brand;
        this.technicalSpecification = technicalSpecification;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTechnicalSpecification() {
        return technicalSpecification;
    }

    public void setTechnicalSpecification(String technicalSpecification) {
        this.technicalSpecification = technicalSpecification;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getNumOfAvailableProducts() {
        return NumOfAvailableProducts;
    }

    public void setNumOfAvailableProducts(int numOfAvailableProducts) {
        NumOfAvailableProducts = numOfAvailableProducts;
    }

    @Override
    public String toString() {
        return "PRODUCT TYPE=" + productType +
                " , PRICE=" + " $" + price +
                " , PRODUCT NAME='" + productName + '\'' +
                " , BRAND='" + brand + '\'' +
                " , NUMBER OF AVAILABLE PRODUCTS= " + NumOfAvailableProducts +
                " , TECHNICAL SPECIFICATION='" + technicalSpecification;
    }

    public String basketProductToString() {
        return "NUMBER OF PRODUCTS=" + NumOfAvailableProducts +
                " , PRODUCT NAME='" + productName + '\'' +
                " , BRAND='" + brand + '\'' +
                " , PRICE='" + price + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productType == product.productType &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(brand, product.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, productName, brand);
    }
}
