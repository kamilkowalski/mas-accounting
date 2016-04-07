package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer extends ObjectPlus implements Serializable {
    String fullName;
    String shortName;
    List<String> emails;
    NipCode nip;
    ZipCode zip;
    String city;
    String address;

    public Customer(String fullName, String shortName, List<String> email, NipCode nip, ZipCode zip, String city, String address) {
        super();

        setFullName(fullName);
        setShortName(shortName);
        setEmails(email);
        setNip(nip);
        setZip(zip);
        setCity(city);
        setAddress(address);
    }

    public Customer(String fullName, String shortName, NipCode nip, ZipCode zip, String city, String address) {
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

    @Override
    public String toString() {
        String output = getFullName() + "\n" + getAddress() + "\n" + getZip().getCode() + " " + getCity();

        for(String email : getEmails()) {
            output += "\n" + email;
        }

        return output;
    }
}
