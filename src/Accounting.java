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

        Company agora = new Company("Agora S.A.", "Agora", new NipCode("5260305644"), new ZipCode("00-732"),
                "Warszawa", "Czerska 8/10");

        List<String> egmontEmails = new ArrayList<>();
        egmontEmails.add("kontakt@egmont.pl");
        egmontEmails.add("biuro@egmont.pl");

        Company egmont = new Company("Egmont Sp. z o. o.", "Egmont", egmontEmails, new NipCode("5260207752"),
                new ZipCode("01-029"), "Warszawa", "Dzielna 60");

        Asset samochod = new Asset("Toyota Corolla", 15000, 15);

        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 15);

        Date from = calendar.getTime();

        calendar.set(Calendar.YEAR, 2016);

        Date to = calendar.getTime();

        AssetOwnership ownershipEgmont = new AssetOwnership(samochod, egmont, from, to);
        AssetOwnership ownershipAgora = new AssetOwnership(samochod, agora, to, null);

        try {
            ObjectPlus.printClassInstances(AssetOwnership.class);
            ObjectPlus.printClassInstances(Asset.class);
            ObjectPlus.printClassInstances(Company.class);
        } catch(Exception e) {
            System.out.println(e);
        }

        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 15);

        // Example products
        Product papier = new Product("Papier A4, ryza", 23.70, 23);
        Product olowki = new Product("Ołówki, 30 szt.", 14.41, 23);
        Product laptop = new Product("HP Pavilion", 3500, 8);
        Product sluchawki = new Product("Słuchawki Razer", 150, 8);

        Invoice rachunek = new Invoice("01/2016", agora, egmont, Invoice.InvoiceType.RECEIPT, calendar.getTime());
        try {
            InvoiceEntry.createInvoiceEntry(rachunek, papier, 30);
            InvoiceEntry.createInvoiceEntry(rachunek, olowki, 7);
        } catch(Exception e) {
            System.out.println(e);
        }
        System.out.println(rachunek.toString());

        System.out.println("\n---------------------\n");

        Invoice fakturaVat = new Invoice("01/01/2016", egmont, agora, Invoice.InvoiceType.VAT, calendar.getTime());
        try {
            InvoiceEntry.createInvoiceEntry(fakturaVat, laptop, 2);
            InvoiceEntry.createInvoiceEntry(fakturaVat, sluchawki, 2);
            InvoiceEntry.createInvoiceEntry(fakturaVat, papier, 5);
        } catch(Exception e) {
            System.out.println(e);
        }
        System.out.println(fakturaVat.toString());

        System.out.println("\n---------------------\n");

        System.out.println("Średnia faktur: " + Invoice.getAverageInvoiceValue() + "zł");

        System.out.println("\n---------------------\n");


        System.out.println("Wpisy ze stawką VAT 23%:");
        for(InvoiceEntry entry : fakturaVat.getInvoiceEntries(23)) {
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
            Product zlyProdukt = new Product("Zły produkt", 50.0, 24);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        System.out.println("\n---------------------\n");

        try {
            ObjectPlus.printClassInstances(Company.class);
        } catch(Exception e){
            System.out.println("Wyjątek przy wypisywaniu ekstensji: " + e.toString());
        }

        System.out.println("\n---------------------\n");

        try {
            String filePath = "ekstensja.dump";
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            Company.saveClassInstances(oos);

            System.out.println("Zapisano ekstensję do pliku " + filePath);

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            Company.readClassInstances(ois);

            System.out.println("Odczytano ekstensję z pliku " + filePath);
        } catch (Exception e){
            System.out.println("Wyjątek przy zapisie i odczycie ekstensji: " + e.toString());
        }

        System.out.println("\n---------------------\n");


        Invoice nowaFaktura = new Invoice("02/2016", egmont, agora, Invoice.InvoiceType.VAT, calendar.getTime());
        Invoice innaFaktura = new Invoice("03/2016", agora, egmont, Invoice.InvoiceType.RECEIPT, calendar.getTime());

        try {
            InvoiceEntry entry = InvoiceEntry.createInvoiceEntry(nowaFaktura, olowki, 4);
            innaFaktura.addInvoiceEntry(entry);
        } catch(Exception e) {
            System.out.println(e);
        }

        System.out.println("\n---------------------\n");

        Invoice.removeFromClassInstances(nowaFaktura);

        try {
            ObjectPlus.printClassInstances(Invoice.class);
        } catch(Exception e){
            System.out.println("Wyjątek przy wypisywaniu ekstensji: " + e.toString());
        }
    }
}
