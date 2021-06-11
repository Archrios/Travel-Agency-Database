package ui;

import delegates.BookingDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BookingWindow extends JFrame {
    private BookingDelegate delegate;

    public BookingWindow() {
        super("Bookings");
    }

    public void setup(BookingDelegate delegate) {
        this.delegate = delegate;

        setPreferredSize(new Dimension(600, 9*80));
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 9*80));
        for (int i = 0; i < 9; i++) {
            JLabel label = new JLabel("label" + (i+1));
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(600,75));
            button.add(label);
            panel.add(button);
        }

        add(panel);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
