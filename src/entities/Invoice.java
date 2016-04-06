package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {

    String number;
    Customer customer;
    List<InvoiceEntry> entries = new ArrayList<>();
    Date creationDate;
    Date paymentDate;

    public Invoice(String number, Customer customer, Date creationDate, Date paymentDate) {
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
}
