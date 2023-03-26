package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    static void resetInvoiceNumber() {
        nextNumber = 0;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }

    private String productList() {
        StringBuilder sb = new StringBuilder();
        products.forEach((product, quantity) -> {
            sb.append("Nazwa: " + product.getName() + "; cena: " + product.getPrice() + "PLN; ilosc: " + quantity);
            sb.append(System.lineSeparator());
        });
        return sb.toString();
    }

    private void productRow(Product p, Integer i) {
    }


    public String printProducsts() {

        return new StringBuilder()
                .append("Nr faktury: " + number)
                .append(System.lineSeparator()) // system-specific newline
                .append(productList())
                .append("Liczba pozycji: " + products.size())
                .toString();
    }

    // Nr faktury: 1
    // Nazwa: Mleko; cena: 4.01PLN; ilosc: 1
    // Nazwa: Jajko; cena: 1.21PLN; ilosc: 12
    // Nazwa: Chleb; cena: 5.21PLN; ilosc: 2

    // Liczba pozycji: 2

}
