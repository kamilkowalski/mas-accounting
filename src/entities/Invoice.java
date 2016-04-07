package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Invoice extends ObjectPlus implements Serializable {

    String number;
    Customer customer;
    List<InvoiceEntry> entries = new ArrayList<>();
    Date creationDate;
    Date paymentDate;

    public Invoice(String number, Customer customer, Date creationDate, Date paymentDate) {
        super();

        setNumber(number);
        setCustomer(customer);
        setPaymentDate(paymentDate);
        setCreationDate(creationDate);
    }

    public Invoice(String number, Customer customer, Date paymentDate) {
        this(number, customer, new Date(), paymentDate);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void addInvoiceEntry(InvoiceEntry entry) {
        entries.add(entry);
    }

    public List<? extends InvoiceEntry> getInvoiceEntries() {
        return entries;
    }

    public double getTotalPrice() {
        return entries.stream()
                .mapToDouble(InvoiceEntry::getTotalPrice)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Faktura nr " + getNumber() + "\n\n");

        sb.append("Data utworzenia: " + getCreationDate() + "\n");

        sb.append("Termin płatności: " + getPaymentDate() + "\n\n");

        sb.append(getCustomer().toString() + "\n\n");

        sb.append("Nazwa | Ilość | Cena jedn. | Cena łączna\n");

        for(InvoiceEntry entry : entries) {
            sb.append(entry.toString()+"\n");
        }

        sb.append("SUMA: " + getTotalPrice());

        return sb.toString();
    }

    public static double getAverageInvoiceValue() {
        Vector<ObjectPlus> invoices = ObjectPlus.getClassInstancesVector(Invoice.class);
        if (invoices.size() == 0) return 0.0;

        double sum = 0;
        for(ObjectPlus obj : invoices) {
            Invoice invoice = (Invoice)obj;
            sum += invoice.getTotalPrice();
        }

        return sum / invoices.size();
    }
}
