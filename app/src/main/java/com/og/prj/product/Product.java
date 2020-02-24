package com.og.prj.product;

public class Product {

    private Long productId;
    private String productName;
    private double price;
    private String productUrl;

    public Product(Long productId, String productName, double price, String productUrl) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productUrl = productUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
}
