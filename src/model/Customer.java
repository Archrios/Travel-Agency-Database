package model;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private int accountTier;
    private String email;

    public Customer(int customerID,String firstName, String lastName, int accountTier, String email) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountTier = accountTier;
        this.email = email;
    }

    public int getCustomerID() { return customerID; }

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
