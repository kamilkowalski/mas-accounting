package entities;

import java.io.Serializable;
import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class Invoice extends ObjectPlus implements Serializable {

    public enum InvoiceType { RECEIPT, VAT };

    String number;
    InvoiceType type;
    Date creationDate;
    Date paymentDate;

    private List<InvoiceEntry> entries = new ArrayList<>();
    private Company recipient;
    private Company issuer;

    private static Set<InvoiceEntry> allEntries = new HashSet<>();

    public Invoice(String number, Company issuer, Company recipient, InvoiceType type, Date creationDate, Date paymentDate) {
        super();

        setNumber(number);
        setIssuer(issuer);
        setRecipient(recipient);
        setType(type);
        setPaymentDate(paymentDate);
        setCreationDate(creationDate);
    }

    public Invoice(String number, Company issuer, Company recipient, InvoiceType type, Date paymentDate) {
        this(number, issuer, recipient, type, new Date(), paymentDate);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Company getIssuer() {
        return issuer;
    }

    public void setIssuer(Company issuer) {
        if (this.issuer == issuer) return;

        if (this.issuer != null) {
            this.issuer.removeIssuedInvoice(getNumber());
        }

        this.issuer = issuer;
        this.issuer.addIssuedInvoice(this);
    }

    public Company getRecipient() {
        return recipient;
    }

    public void setRecipient(Company recipient) {
        if (this.recipient == recipient) return;

        if (this.recipient != null) {
            this.recipient.removeReceivedInvoice(getNumber());
        }

        this.recipient = recipient;
        this.recipient.addReceivedInvoice(this);
    }

    public InvoiceType getType() {
        return type;
    }

    public void setType(InvoiceType type) {
        this.type = type;
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

    public void addInvoiceEntry(InvoiceEntry entry) throws Exception {
        if (!entries.contains(entry)) {
            if (allEntries.contains(entry)) {
                throw new Exception("Pozycja faktury już została dodana do jakiejś faktury!");
            }
            entries.add(entry);
            allEntries.add(entry);
        }
    }

    public List<InvoiceEntry> getInvoiceEntries() {
        return entries;
    }

    public List<InvoiceEntry> getInvoiceEntries(int rate) {
        return entries.stream()
                .filter(entry -> entry.getProduct().getRate() == rate)
                .collect(Collectors.toList());
    }

    public double getTotalPrice() {
        ToDoubleFunction<InvoiceEntry> mapping;

        if (getType() == InvoiceType.RECEIPT) {
            mapping = item -> item.getNetPrice();
        } else {
            mapping = item -> item.getGrossPrice();
        }

        return entries.stream()
                .mapToDouble(mapping)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (type == InvoiceType.RECEIPT) {
            sb.append("Rachunek nr " + getNumber() + "\n\n");
        } else {
            sb.append("Faktura VAT nr " + getNumber() + "\n\n");
        }

        sb.append("Data utworzenia: " + getCreationDate() + "\n");

        sb.append("Termin płatności: " + getPaymentDate() + "\n\n");

        sb.append("Wystawiona przez: \n\n");
        sb.append(getIssuer().toString() + "\n\n");

        sb.append("Wystawiona dla: \n\n");
        sb.append(getRecipient().toString() + "\n\n");

        sb.append("Nazwa | Ilość | Cena jedn. | Cena łączna\n");

        for(InvoiceEntry entry : entries) {
            sb.append(entry.getProduct().getName() + " | " + entry.getQuantity() + " | ");

            if (type == InvoiceType.RECEIPT) {
                sb.append(entry.getProduct().getPrice() + " | " + entry.getNetPrice());
            } else {
                sb.append(entry.getProduct().getGrossPrice() + " | " + entry.getGrossPrice());
            }

            sb.append("\n");
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

    public static void removeFromClassInstances(Invoice invoice) {
        for(InvoiceEntry entry : invoice.getInvoiceEntries()) {
            allEntries.remove(entry);
        }

        if (classInstances.get(invoice.getClass()).contains(invoice)) {
            classInstances.get(invoice.getClass()).remove(invoice);
        }
    }
}
