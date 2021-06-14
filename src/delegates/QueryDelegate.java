package delegates;

import model.Booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.*;

public interface QueryDelegate {

    public boolean insertVacationPlan(int planID, Date startDate, Date endDate, double price);
    public boolean deleteCustomer(String email);
    public boolean updateReview(int reviewID, String description);
    public List<VacationPlan> selectVacationPrice(double price);
    public List<Integer> selectVacationDestination(String country);
    public List<ArrayList<String>> selectCruise(int rating);
    public double selectReviewAverage(int planID);
    public List<ArrayList<Integer>> eventCount();
    public List<VacationPlan> vacationBookedByAll();
}
