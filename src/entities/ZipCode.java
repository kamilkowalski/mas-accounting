package entities;


import java.io.Serializable;

public class ZipCode extends ObjectPlus implements Serializable {

    String code;

    public ZipCode(String code) {
        super();

        if(!isValid(code)) {
            throw new IllegalArgumentException("Niepoprawny kod pocztowy: " + code);
        }

        setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static boolean isValid(String code) {
        return code.matches("^\\d{2}-\\d{3}$");
    }
}
