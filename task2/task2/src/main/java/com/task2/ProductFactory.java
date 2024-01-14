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
            case "Electronic":
                product = new Electronics();
                break;
            // Handle other cases...

            default:
                throw new IllegalArgumentException("Invalid product type: " + type);
        }

        // Set and configure product attributes
        //product.setCommonAttribute1(/* value */);
        //product.setCommonAttribute2(/* value */);
        // Set other attributes specific to the product type

        return product;
    }
}
