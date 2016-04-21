package entities;

import java.io.Serializable;
import java.util.*;

public class Company extends ObjectPlus implements Serializable {
    String fullName;
    String shortName;
    List<String> emails;
    NipCode nip;
    ZipCode zip;
    String city;
    String address;

    private Map<String, Invoice> issuedInvoices = new TreeMap<>();
    private Map<String, Invoice> receivedInvoices = new TreeMap<>();
    private Set<AssetOwnership> assetOwnerships = new HashSet<>();

    public Company(String fullName, String shortName, List<String> email, NipCode nip, ZipCode zip, String city, String address) {
        super();

        setFullName(fullName);
        setShortName(shortName);
        setEmails(email);
        setNip(nip);
        setZip(zip);
        setCity(city);
        setAddress(address);
    }

    public Company(String fullName, String shortName, NipCode nip, ZipCode zip, String city, String address) {
        this(fullName, shortName, new ArrayList<>(), nip, zip, city, address);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public void addEmail(String email) {
        emails.add(email);
    }

    public NipCode getNip() {
        return nip;
    }

    public void setNip(NipCode nip) {
        this.nip = nip;
    }

    public ZipCode getZip() {
        return zip;
    }

    public void setZip(ZipCode zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addIssuedInvoice(Invoice invoice) {
        if (!issuedInvoices.containsKey(invoice.getNumber())) {
            issuedInvoices.put(invoice.getNumber(), invoice);
            invoice.setIssuer(this);
        }
    }

    public Invoice findIssuedInvoice(String number) throws Exception {
        if (!issuedInvoices.containsKey(number)) {
            throw new Exception("Nie odnaleziono faktury o numerze " + number);
        }

        return issuedInvoices.get(number);
    }

    public void removeIssuedInvoice(String number) {
        if (issuedInvoices.containsKey(number)) {
            issuedInvoices.remove(number);
        }
    }

    public void addReceivedInvoice(Invoice invoice) {
        if (!receivedInvoices.containsKey(invoice.getNumber())) {
            receivedInvoices.put(invoice.getNumber(), invoice);
            invoice.setRecipient(this);
        }
    }

    public Invoice findReceivedInvoice(String number) throws Exception {
        if (!receivedInvoices.containsKey(number)) {
            throw new Exception("Nie odnaleziono faktury o numerze " + number);
        }

        return receivedInvoices.get(number);
    }

    public void removeReceivedInvoice(String number) {
        if (receivedInvoices.containsKey(number)) {
            receivedInvoices.remove(number);
        }
    }

    public void addAssetOwnership(AssetOwnership ownership) {
        if (!assetOwnerships.contains(ownership)) {
            assetOwnerships.add(ownership);
            ownership.setCompany(this);
        }
    }

    public void removeAssetOwnership(AssetOwnership ownership) {
        if (assetOwnerships.contains(ownership)) {
            assetOwnerships.remove(ownership);
        }
    }

    @Override
    public String toString() {
        String output = getFullName() + "\n" + getAddress() + "\n" + getZip().getCode() + " " + getCity();

        for(String email : getEmails()) {
            output += "\n" + email;
        }

        return output;
    }
}
