package ui;

import delegates.BookingDelegate;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BookingWindow extends JFrame {
    private BookingDelegate delegate;

    public BookingWindow() {
        super("Bookings");
    }

    public void setup(BookingDelegate delegate) {
        this.delegate = delegate;

        // anonymous inner class for closing the window
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // size the window to obtain a best fit for the components
        this.pack();

        // center the frame
        this.setLocationRelativeTo(null);

        // make the window visible
        this.setVisible(true);
    }
}
