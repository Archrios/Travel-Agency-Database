package controller;

import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import ui.LoginWindow;

public class TravelAgency implements LoginWindowDelegate {
    private DatabaseConnectionHandler dbHandler;
    private LoginWindow loginWindow = null;

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
//            TerminalTransactions transaction = new TerminalTransactions();
//            transaction.setupDatabase(this);
//            transaction.showMainMenu(this);
        } else {
            loginWindow.handleLoginFailed();
        }
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
