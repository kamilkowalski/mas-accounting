package entities;

import java.io.Serializable;
import java.util.Vector;

public class InvoiceEntry extends ObjectPlus implements Serializable {

    String name;
    int quantity;
    double price;

    public InvoiceEntry(String name, int quantity, double price) {
        super();

        setName(name);
        setQuantity(quantity);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return this.price * this.quantity;
    }

    @Override
    public String toString() {
        return getName() + " | " + getQuantity() + "szt. | " + getPrice() + "zł | " + getTotalPrice() + "zł";
    }
}
