package model;

public class TransportationCompany {
    private int companyID;
    private String companyName;
    private int rating;

    public TransportationCompany(int companyID, String companyName, int rating) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.rating = rating;
    }

    public int getCompanyID() {
        return companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getRating() {
        return rating;
    }
}
