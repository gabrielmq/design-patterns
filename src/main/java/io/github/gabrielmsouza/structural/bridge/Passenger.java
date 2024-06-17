package io.github.gabrielmsouza.structural.bridge;

public class Passenger extends Account {
    private String cardHolder;
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    protected Passenger(
            String name,
            String email,
            String document,
            String cardHolder,
            String cardNumber,
            String expirationDate,
            String cvv,
            String password
    ) {
        super(name, email, document, PasswordType.SHA1, password);
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;

        if (cvv.length() != 3) throw new RuntimeException("Invalid cvv");
        this.cvv = cvv;
    }

    public static Passenger create(
            String name,
            String email,
            String document,
            String cardHolder,
            String cardNumber,
            String expirationDate,
            String cvv,
            String password
    ) {
        return new Passenger(name, email, document, cardHolder, cardNumber, expirationDate, cvv, password);
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }
}
