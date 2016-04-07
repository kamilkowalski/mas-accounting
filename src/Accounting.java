import entities.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Accounting {

    public static void main(String[] argv) {
        Calendar calendar = Calendar.getInstance();

        Customer agora = new Customer("Agora S.A.", "Agora", new NipCode("5260305644"), new ZipCode("00-732"),
                "Warszawa", "Czerska 8/10");

        List<String> egmontEmails = new ArrayList<>();
        egmontEmails.add("kontakt@egmont.pl");
        egmontEmails.add("biuro@egmont.pl");

        Customer egmont = new Customer("Egmont Sp. z o. o.", "Egmont", egmontEmails, new NipCode("5260207752"),
                new ZipCode("01-029"), "Warszawa", "Dzielna 60");

        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 15);

        Invoice faktura = new Invoice("01/2016", agora, calendar.getTime());
        faktura.addInvoiceEntry(new InvoiceEntry("Papier A4, ryza", 30, 23.70));
        faktura.addInvoiceEntry(new InvoiceEntry("Ołówki, 30 sztuk.", 7, 14.41));
        System.out.println(faktura.toString());

        System.out.println("\n---------------------\n");

        Invoice faktura2 = new Invoice("01/01/2016", egmont, calendar.getTime());
        faktura2.addInvoiceEntry(new InvoiceEntry("HP Pavilion", 2, 3500));
        faktura2.addInvoiceEntry(new InvoiceEntry("Słuchawki Razer", 2, 150));
        System.out.println(faktura2.toString());

        System.out.println("\n---------------------\n");

        System.out.println("Średnia faktur: " + Invoice.getAverageInvoiceValue() + "zł");

        System.out.println("\n---------------------\n");

        VATInvoice fakturaVAT = new VATInvoice("02/2016", agora, calendar.getTime());

        fakturaVAT.addInvoiceEntry(new VATInvoiceEntry("Mysz Logitech MX520", 3, 120.50, 23));
        fakturaVAT.addInvoiceEntry(new VATInvoiceEntry("Drukarka HP LaserJet", 1, 231.90, 8));

        System.out.println(fakturaVAT.toString());

        System.out.println("\n---------------------\n");

        System.out.println("Wpisy ze stawką VAT 23%:");
        for(VATInvoiceEntry entry : fakturaVAT.getInvoiceEntries(23)) {
            System.out.println(entry);
        }

        System.out.println("\n---------------------\n");

        String nip = "123456";
        if(NipCode.isValid(nip)) {
            System.out.println("NIP " + nip + " jest poprawny");
        } else {
            System.out.println("NIP " + nip + " jest niepoprawny");
        }

        System.out.println("\n---------------------\n");

        try {
            VATInvoiceEntry entry = new VATInvoiceEntry("Zły produkt", 1, 50.0, 24);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        System.out.println("\n---------------------\n");

        try {
            ObjectPlus.printClassInstances(Customer.class);
        } catch(Exception e){
            System.out.println("Wyjątek przy wypisywaniu ekstensji: " + e.toString());
        }

        System.out.println("\n---------------------\n");

        try {
            String filePath = "ekstensja.dump";
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            Customer.saveClassInstances(oos);

            System.out.println("Zapisano ekstensję do pliku " + filePath);

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            Customer.readClassInstances(ois);

            System.out.println("Odczytano ekstensję z pliku " + filePath);
        } catch (Exception e){
            System.out.println("Wyjątek przy zapisie i odczycie ekstensji: " + e.toString());
        }


    }
}
