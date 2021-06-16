package model;


import java.time.LocalDate;

public class VacationPlan {
    private int  planID;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;

    public VacationPlan(int planID, LocalDate startDate, LocalDate endDate, double price) {
        this.planID = planID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public int getPlanID() {
        return planID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }
}
