package lib.dto;

import java.io.Serializable;

public class AdressDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String street;
    private final String number;

    public AdressDto(String strada, String numar) {
        this.street = strada;
        this.number = numar;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Adress: " + "[" + street + ", " + number + "]";
    }

}
