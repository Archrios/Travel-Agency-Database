package model;

public class Booking {
    private int bookingID;
    private int numAdults;
    private int numChildren;
    private int customerID;
    private int employeeID;
    private int planID;

    public Booking(int bookingID, int numAdults, int numChildren, int customerID, int employeeID, int planID) {
        this.bookingID = bookingID;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.planID = planID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getNumAdults() {
        return numAdults;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public int getPlanID() {
        return planID;
    }


}
