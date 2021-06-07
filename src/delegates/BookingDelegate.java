package delegates;

import model.Booking;

import java.util.List;

public interface BookingDelegate {
    public List<Booking> getBookings();
    public boolean addBooking(Booking b);
    public boolean removeBooking();
}
