package model;

import java.time.LocalDate;
import java.util.Date;

public class Review {
    private int reviewID;
    private LocalDate datePosted;
    private double rating;
    private String description;
    private int reviewerCustomerID;
    private int planID;

    public Review(int reviewID, LocalDate datePosted, double rating, String description, int reviewerCustomerID, int planID) {
        this.reviewID = reviewID;
        this.datePosted = datePosted;
        this.rating = rating;
        this.description = description;
        this.reviewerCustomerID = reviewerCustomerID;
        this.planID = planID;
    }

    public int getReviewID() {
        return reviewID;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public int getReviewerCustomerID() {
        return reviewerCustomerID;
    }

    public int getPlanID() {
        return planID;
    }
}
