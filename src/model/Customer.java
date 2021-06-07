package model;

public class Customer {
    private String firstName;
    private String lastName;
    private int accountTier;
    private String email;

    public Customer(String firstName, String lastName, int accountTier, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountTier = accountTier;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAccountTier() {
        return accountTier;
    }

    public String getEmail() {
        return email;
    }
}
