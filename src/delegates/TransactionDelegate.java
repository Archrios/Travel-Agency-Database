package delegates;
import model.*;

import java.util.Date;

public interface TransactionDelegate {
    public void insertVacationPlan(int planID, Date startDate, Date endDate, double price);
    public void deleteCustomer(String email);
    public void updateReview(int reviewID);
    public void selectVacationPrice(double price);
    public void selectVacationDestination(String country);
    public void selectCruise(int rating);
    public void selectReviewAverage(int planID);
    public void eventCount();
    public void vacationBookedByAll();
}
