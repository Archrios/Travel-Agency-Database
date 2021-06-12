package model;

import java.util.Date;

public class Review {
    private int reviewID;
    private Date datePosted;
    private double rating;
    private String Description;
    private int reviewerCustomerID;
    private int planID;

    public Review(int reviewID, Date datePosted, double rating, String description, int reviewerCustomerID, int planID) {
        this.reviewID = reviewID;
        this.datePosted = datePosted;
        this.rating = rating;
        Description = description;
        this.reviewerCustomerID = reviewerCustomerID;
        this.planID = planID;
    }

    public int getReviewID() {
        return reviewID;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return Description;
    }

    public int getReviewerCustomerID() {
        return reviewerCustomerID;
    }

    public int getPlanID() {
        return planID;
    }
}
