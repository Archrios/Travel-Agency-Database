package model;

public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private int accountTier;
    private String email;

    public Employee(int employeeID,String firstName, String lastName, int accountTier, String email) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountTier = accountTier;
        this.email = email;
    }

    public int getEmployeeID() { return employeeID; }

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
