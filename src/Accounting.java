import entities.*;

import java.util.Calendar;
import java.util.Date;

public class Accounting {

    public static void main(String[] argv) {
        Calendar calendar = Calendar.getInstance();

        Customer agora = new Customer("Agora S.A.", "Agora", new NipCode("5260305644"), new ZipCode("00-732"),
                "Warszawa", "Czerska 8/10");

        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 15);

        Invoice faktura = new Invoice("01/2016", agora, calendar.getTime());

        faktura.addInvoiceEntry(new InvoiceEntry("Papier A4, ryza", 30, 23.70));
        faktura.addInvoiceEntry(new InvoiceEntry("Ołówki, 30 sztuk.", 7, 14.41));

        System.out.println(faktura.toString());
        System.out.println("---------------------");


        VATInvoice fakturaVAT = new VATInvoice("02/2016", agora, calendar.getTime());

        fakturaVAT.addInvoiceEntry(new VATInvoiceEntry("Mysz Logitech MX520", 3, 120.50, 23));
        fakturaVAT.addInvoiceEntry(new VATInvoiceEntry("Drukarka HP LaserJet", 1, 231.90, 23));

        System.out.println(fakturaVAT.toString());
        System.out.println("---------------------\n");


        try {
            VATInvoiceEntry entry = new VATInvoiceEntry("Zły produkt", 1, 50.0, 24);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
