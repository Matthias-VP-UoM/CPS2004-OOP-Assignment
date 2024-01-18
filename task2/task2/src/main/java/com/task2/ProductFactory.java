package com.task2;

public class ProductFactory implements PFInterface {
    @Override
    public Product createProduct(String type) {
        Product product;

        // Instantiate the product based on the type
        switch (type) {
            case "Book":
                product = new Book();
                break;
            case "Computer Game":
                product = new ComputerGame();
                break;
            case "Clothing":
                product = new Clothing();
                break;
            case "Electronics":
                product = new Electronics();
                break;
            default:
                throw new IllegalArgumentException("Invalid product type: " + type);
        }

        return product;
    }
}
