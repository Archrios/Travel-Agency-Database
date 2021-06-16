package delegates;

import java.util.List;
import model.*;
import java.time.LocalDate;

public interface QueryDelegate {
    public boolean databaseSetup();

    public boolean insertVacationPlan(int planID, LocalDate startDate, LocalDate endDate, double price);
    public boolean deleteCustomer(String email);
    public boolean updateReview(int reviewID, String description);
    public List<VacationPlan> selectVacationPrice(double price);
    public List<Integer> selectVacationDestination(String country);
    public List<List<String>> selectCruise(int rating);
    public double selectReviewAverage(int planID);
    public List<List<Integer>> eventCount();
    public List<VacationPlan> vacationBookedByAll();
}
