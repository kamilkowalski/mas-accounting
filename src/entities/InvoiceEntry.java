package entities;

import java.io.Serializable;
import java.util.Vector;

public class InvoiceEntry extends ObjectPlus implements Serializable {

    int quantity;

    private Product product;

    private InvoiceEntry(Product product, int quantity) throws Exception {
        super();

        setProduct(product);
        setQuantity(quantity);
    }

    public static InvoiceEntry createInvoiceEntry(Invoice invoice, Product product, int quantity) throws Exception {
        if (invoice == null) {
            throw new Exception("Faktura nie istnieje!");
        }

        InvoiceEntry entry = new InvoiceEntry(product, quantity);
        invoice.addInvoiceEntry(entry);
        return entry;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) throws Exception {
        if (product == null) {
            throw new Exception("Produkt musi istnieć!");
        }

        if (this.product == product) return;

        if (this.product != null) {
            this.product.removeInvoiceEntry(this);
        }

        this.product = product;
        this.product.addInvoiceEntry(this);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getNetPrice() {
        return getProduct().getPrice() * getQuantity();
    }

    public double getGrossPrice() {
        return getProduct().getGrossPrice() * getQuantity();
    }

    @Override
    public String toString() {
        return "" + getProduct().getName() + " | " + getQuantity() + " | " + getNetPrice() + " | " + getGrossPrice();
    }
}
