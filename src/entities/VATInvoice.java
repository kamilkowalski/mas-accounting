package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VATInvoice extends Invoice implements Serializable {

    List<VATInvoiceEntry> entries = new ArrayList<VATInvoiceEntry>();

    public VATInvoice(String number, Customer customer, Date creationDate, Date paymentDate) {
        super(number, customer, creationDate, paymentDate);
    }

    public VATInvoice(String number, Customer customer, Date paymentDate) {
        this(number, customer, new Date(), paymentDate);
    }

    public void addInvoiceEntry(VATInvoiceEntry entry) {
        entries.add(entry);
    }

    public List<VATInvoiceEntry> getInvoiceEntries() {
        return entries;
    }

    public List<VATInvoiceEntry> getInvoiceEntries(double taxRate) {
        return entries.stream()
                .filter(entry -> entry.getRate() == taxRate)
                .collect(Collectors.toList());
    }

    public double getNetPrice() {
        return entries.stream()
                .mapToDouble(entry -> ((VATInvoiceEntry)entry).getNetPrice())
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Faktura nr " + getNumber() + "\n\n");

        sb.append("Data utworzenia: " + getCreationDate() + "\n");

        sb.append("Termin płatności: " + getPaymentDate() + "\n\n");

        sb.append(getCustomer().toString() + "\n\n");

        sb.append("Nazwa | Ilość | Cena jedn. | Cena netto | Cena brutto\n");

        for(VATInvoiceEntry entry : entries) {
            sb.append(entry.toString()+"\n");
        }

        return sb.toString();
    }
}
