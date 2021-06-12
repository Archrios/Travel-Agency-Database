package controller;

import database.DatabaseConnectionHandler;
import delegates.BookingDelegate;
import delegates.LoginDelegate;
import model.*;
import ui.BookingWindow;
import ui.LoginWindow;

import java.util.List;

public class TravelAgency implements LoginDelegate, BookingDelegate {
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
        System.out.println("email: " + email + ", pword: " + password);
        boolean didConnect = dbHandler.login(email, password);

        if (didConnect) {
            loginWindow.dispose();
            System.out.println("Success!");

            bookingWindow = new BookingWindow();
            bookingWindow.setup(this);
//            transaction.setupDatabase(this);
//            transaction.showMainMenu(this);
        } else {
            loginWindow.handleLoginFailed();
        }
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
