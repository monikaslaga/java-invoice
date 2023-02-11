package pl.edu.agh.mwo.invoice.product;

import java.io.IOException;
import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Product name cannot be null.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("Price of the product cannot be null or negative.");
        }
        this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        return this.getPrice().multiply(this.taxPercent).add(this.price);
    }
}
