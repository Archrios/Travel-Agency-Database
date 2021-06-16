package model;


import java.util.Date;

public class VacationPlan {
    private int  planID;
    private Date startDate;
    private Date endDate;
    private double price;

    public VacationPlan(int planID, Date startDate, Date endDate, double price) {
        this.planID = planID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public int getPlanID() {
        return planID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }
}
