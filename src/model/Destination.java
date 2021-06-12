package model;

public class Destination {
    private int destinationID;
    private String country;
    private String city;

    public Destination(int destinationID, String country, String city) {
        this.destinationID = destinationID;
        this.country = country;
        this.city = city;
    }

    public int getDestinationID() {
        return destinationID;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
