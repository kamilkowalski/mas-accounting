package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Product extends ObjectPlus implements Serializable {
    String name;
    double price;
    int rate;

    private Set<InvoiceEntry> invoiceEntries = new HashSet<>();

    public static int[] availableRates = new int[]{0, 4, 5, 7, 8, 23};

    public Product(String name, double price, int rate) {
        super();

        setName(name);
        setPrice(price);
        setRate(rate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void addInvoiceEntry(InvoiceEntry entry) throws Exception {
        if (!this.invoiceEntries.contains(entry)) {
            this.invoiceEntries.add(entry);
            entry.setProduct(this);
        }
    }

    public void removeInvoiceEntry(InvoiceEntry entry) {
        if (this.invoiceEntries.contains(entry)) {
            this.invoiceEntries.remove(entry);
        }
    }

    public void setRate(int rate) {
        boolean found = false;

        for(int i = 0; i<availableRates.length; i++){
            if(availableRates[i] == rate) found = true;
        }

        if(!found) {
            throw new IllegalArgumentException("Niepoprawna stawka VAT: " + rate);
        }

        this.rate = rate;
    }

    public double getGrossPrice() {
        return (this.rate / 100.0) * this.price + this.price;
    }

    @Override
    public String toString() {
        return getName();
    }
}
