package io.github.gabrielmsouza.structural.bridge;

public class Driver extends Account {
    private String carPlate;

    public Driver(String name, String email, String document, String carPlate, String password) {
        super(name, email, document, PasswordType.SHA1, password);
        if (!carPlate.matches("[A-Z]{3}[0-9]{4}")) throw new RuntimeException("Invalid car plate");
        this.carPlate = carPlate;
    }

    public static Driver create(
            String name,
            String email,
            String document,
            String carPlate,
            String password
    ) {
        return new Driver(name, email, document, carPlate, password);
    }

    public String getCarPlate() {
        return carPlate;
    }
}
