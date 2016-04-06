package entities;

public class Customer {
    String fullName;
    String shortName;
    String email;
    NipCode nip;
    ZipCode zip;
    String city;
    String address;

    public Customer(String fullName, String shortName, String email, NipCode nip, ZipCode zip, String city, String address) {
        setFullName(fullName);
        setShortName(shortName);
        setEmail(email);
        setNip(nip);
        setZip(zip);
        setCity(city);
        setAddress(address);
    }

    public Customer(String fullName, String shortName, NipCode nip, ZipCode zip, String city, String address) {
        this(fullName, shortName, null, nip, zip, city, address);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return getFullName() + "\n" + getAddress() + "\n" + getZip().getCode() + " " + getCity();
    }
}
