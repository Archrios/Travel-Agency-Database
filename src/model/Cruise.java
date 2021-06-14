package model;

public class Cruise {
    private int companyID;
    private String features;
    private String cruiseModel;

    public Cruise(int companyID, String features, String cruiseModel) {
        this.companyID = companyID;
        this.features = features;
        this.cruiseModel = cruiseModel;
    }

    public int getCompanyID() {
        return companyID;
    }

    public String getFeatures() {
        return features;
    }

    public String getCruiseModel() {
        return cruiseModel;
    }
}
