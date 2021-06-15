package controller;

import database.DatabaseConnectionHandler;
import delegates.QueryDelegate;
import delegates.LoginDelegate;
import model.*;
import ui.BookingWindow;
import ui.LoginWindow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TravelAgency implements LoginDelegate, QueryDelegate {
    private DatabaseConnectionHandler dbHandler;
    private LoginWindow loginWindow = null;
    private BookingWindow bookingWindow = null;
    private Customer user = null;

    public TravelAgency() {
        dbHandler = new DatabaseConnectionHandler();
    }

    private void start() {
        loginWindow = new LoginWindow();
        loginWindow.showFrame(this);
    }

    public void login(String email, String password) {
        boolean didConnect = dbHandler.login(email, password);

        if (didConnect) {
            loginWindow.dispose();
            System.out.println("Success!");

            bookingWindow = new BookingWindow();
            bookingWindow.setup(this);
        } else {
            loginWindow.handleLoginFailed();
        }
    }


    public boolean insertVacationPlan(int planID, Date startDate, Date endDate, double price){
        return dbHandler.insertVacationPlan(new VacationPlan(planID, startDate,endDate,price));
    }
    public boolean deleteCustomer(String email){
        return dbHandler.deleteCustomer(email);
    }
    public boolean updateReview(int reviewID, String description){
        return dbHandler.updateReview(reviewID,description);
    }
    public List<VacationPlan> selectVacationPrice(double price){
        return dbHandler.selectVacationPrice(price);
    }
    public List<Integer> selectVacationDestination(String country){
        return dbHandler.selectVacationDestination(country);
    }
    public List<ArrayList<String>> selectCruise(int rating){
        return dbHandler.selectCruise(rating);
    }
    public double selectReviewAverage(int planID){
        return dbHandler.selectReviewAverage(planID);
    }
    public List<ArrayList<Integer>> eventCount(){
        return dbHandler.eventCount();
    }
    public List<VacationPlan> vacationBookedByAll(){
        return dbHandler.vacationBookedByAll();
    }


    public List<Booking> getBookings() {
        return null;
    }

    public boolean addBooking(Booking b) {
        return false;
    }

    public boolean removeBooking() {
        return false;
    }

    public void closeHandler() {
        dbHandler.close();
        dbHandler = null;

        System.exit(0);
    }

    public static void main(String[] args) {
        TravelAgency agency = new TravelAgency();
        agency.start();
    }
}
