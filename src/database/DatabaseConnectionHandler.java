package database;

import model.Customer;
import model.Review;
import model.VacationPlan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;


public class DatabaseConnectionHandler {
    private static final String MYSQL_URL = "jdbc:mysql://localhost/traveldb";
    private static final String USERNAME = "traveldb";
    private static final String PASSWORD = "123456";

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            connection = DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to MySQL!");
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public List<VacationPlan> displayVacationPlan() {
        ArrayList<VacationPlan> result = new ArrayList<VacationPlan>();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Vacation_Plan");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                VacationPlan vp = new VacationPlan(rs.getInt("Plan_ID"),
                        rs.getDate("Start_Date").toLocalDate(), rs.getDate("End_Date").toLocalDate(),rs.getDouble("Price"));
                result.add(vp);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
        return result;
    }

    public List<Customer> displayCustomer(){
        ArrayList<Customer> result = new ArrayList<Customer>();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Customer_Account");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Customer c = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getInt(4), rs.getString(5));
                result.add(c);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
        return result;
    }
    public List<Review> displayReview(){
        ArrayList<Review> result = new ArrayList<Review>();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Review");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Review r = new Review(rs.getInt(1),rs.getDate(2).toLocalDate(),rs.getDouble(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6));
                result.add(r);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
        return result;
    }

    public boolean insertVacationPlan(VacationPlan vp) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Vacation_Plan VALUES (?,?,?,?)");
            ps.setInt(1, vp.getPlanID());
            ps.setDate(2,  java.sql.Date.valueOf(vp.getStartDate()));
            ps.setDate(3, java.sql.Date.valueOf(vp.getEndDate()));
            ps.setDouble(4, vp.getPrice());
            ps.executeUpdate();
            connection.commit();

            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            return false;
        }
    }

    public boolean deleteCustomer(String email) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Customer_Account WHERE Email = ?");
            ps.setString(1, email);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Customer with email of " + email + " does not exist!");
            }
            connection.commit();

            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            return false;
        }
    }

    public boolean updateReview(int reviewID, String description) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Review SET Description = ? WHERE Review_ID = ?");
            ps.setString(1, description);
            ps.setInt(2, reviewID);


            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Review " + reviewID + " does not exist!");
            }
            connection.commit();

            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            return false;
        }
    }

    public List<VacationPlan> selectVacationPrice(double price) {
        ArrayList<VacationPlan> result = new ArrayList<VacationPlan>();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Vacation_Plan WHERE Price <= ?");
            ps.setDouble(1,price);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                VacationPlan vp = new VacationPlan(rs.getInt("Plan_ID"),
                        rs.getDate("Start_Date").toLocalDate(), rs.getDate("End_Date").toLocalDate(),rs.getDouble("Price"));
                result.add(vp);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
        return result;
    }

    public List<Integer> selectVacationDestination(String country) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        try{
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT Plan_ID FROM Vacation_Destination NATURAL JOIN Destination WHERE Destination.Country = ?");
            ps.setString(1,country);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Integer i = rs.getInt("Plan_ID");
                result.add(i);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
        return result;
    }

    public List<List<String>> selectCruise(int rating) {
        ArrayList<List<String>> result = new ArrayList<List<String>>();
        try{
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT Company_Name, Rating, Cruise_Model, Features " +
                            "FROM Transportation_Company NATURAL JOIN Cruise WHERE Transportation_Company.Rating >= ?");
            ps.setInt(1,rating);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                List<String> list = new ArrayList<String>();
                String companyName = rs.getString("Company_Name");
                String Rating = String.valueOf(rs.getInt("Rating"));
                String cruiseModel = rs.getString("Cruise_Model");
                String features = rs.getString("Features");
                list.add(companyName);
                list.add(Rating);
                list.add(cruiseModel);
                list.add(features);
                result.add(list);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
        return result;
    }

    /*
    * Not too sure about the line where we retrieve the data from the query.
    * also, if this function returns -1, it means there's no reviews for it or smth like that?
    * unless the query actually sets it as 0 when there's no tuples
    *
    */
    public double selectReviewAverage(int planID) {
        double reviewAverage = -1;
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT AVG(Rating) as Average FROM Review WHERE Plan_ID = ?" );
            ps.setInt(1,planID);

            ResultSet rs = ps.executeQuery();

            rs.first();
            //THIS LINE
            //go by column label of AVG(Rating) or go by index of 1?
            //reviewAverage = rs.getDouble("AVG(Rating)");
            reviewAverage = rs.getDouble("Average");
            rs.close();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return reviewAverage;
    }

    public List<List<Integer>> eventCount() {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        try{
            PreparedStatement ps = connection.prepareStatement(
                    "Select Count(Event_ID), Plan_ID from (Select * from Vacation_Event Natural Join Event where Description LIKE '%Tasting%') as Events Group by Plan_ID");

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                List<Integer> list = new ArrayList<Integer>();
                //Integer eventCount = rs.getInt("Count(EventID)");
                Integer eventCount = rs.getInt(1);
                Integer plan_ID = rs.getInt("Plan_ID");
                list.add(eventCount);
                list.add(plan_ID);
                result.add(list);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
        return result;
    }

    public List<VacationPlan> vacationBookedByAll() {
        ArrayList<VacationPlan> result = new ArrayList<VacationPlan>();
        try{
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Vacation_Plan WHERE NOT EXISTS" +
//                    " ((SELECT c.Customer_ID from Customer c)" +
//                    " EXCEPT" +
//                    " (SELECT b.Customer_ID from Booking b" +
//                    " WHERE c.Customer_ID = b.Customer_ID))");

//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Vacation_Plan vp WHERE NOT EXISTS" +
//                    " (SELECT c.Customer_ID FROM Customer_Account c LEFT JOIN Booking b ON c.Customer_ID = b.Customer_ID AND b.Plan_ID= vp.Plan_ID WHERE b.Customer_ID IS NULL)");

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Vacation_Plan vp WHERE NOT EXISTS " +
                    "(SELECT c.Customer_ID from Customer_Account c WHERE NOT EXISTS (SELECT b.Customer_ID from Booking b WHERE c.Customer_ID = b.Customer_ID AND b.Plan_ID = vp.Plan_ID))");



            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                VacationPlan vp = new VacationPlan(rs.getInt("Plan_ID"),
                        rs.getDate("Start_Date").toLocalDate(), rs.getDate("End_Date").toLocalDate(),rs.getDouble("Price"));
                result.add(vp);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
        return result;
    }


    public boolean login(String email, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Email FROM Customer_Login WHERE Email = ? && Password = ?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (!rs.first()) {
                return false;
            }

            //Customer c = new Customer();

            rs.close();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
