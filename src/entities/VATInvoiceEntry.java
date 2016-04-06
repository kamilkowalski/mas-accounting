package entities;

import java.util.Arrays;

public class VATInvoiceEntry extends InvoiceEntry {

    int rate;

    public static int[] availableRates = new int[]{0, 4, 5, 7, 8, 23};

    public VATInvoiceEntry(String name, int quantity, double price, int rate) {
        super(name, quantity, price);
        setRate(rate);
    }

    public int getRate() {
        return rate;
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

    @Override
    public double getTotalPrice() {
        double multiplier = 1 + (getRate() / 100.0);
        return super.getTotalPrice() * multiplier;
    }

    public double getNetPrice() {
        return super.getTotalPrice();
    }

    @Override
    public String toString() {
        return getName() + " | " + getQuantity() + "szt. | " + getPrice() + "zł | " + getNetPrice() + "zł | " + getTotalPrice() + "zł";
    }
}
