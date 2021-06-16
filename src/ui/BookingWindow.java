package ui;

import delegates.QueryDelegate;
import model.VacationPlan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookingWindow extends JFrame implements ActionListener {
    private QueryDelegate delegate;
    private InputWindow iw;
    private final String[] buttonLabels;

    public BookingWindow() {
        super("Queries");
        buttonLabels = new String[]{
                "Insert New Vacation Plan",
                "Delete User by Email",
                "Update Review",
                "Select Vacations by Price",
                "Select Plan IDs by Country",
                "Select Cruises by Rating",
                "Get Average Review Rating of Vacation Plan",
                "Get Number of Events For Each Vacation Plan",
                "Find Vacations that are Booked by All Users"
        };
    }

    public void setup(QueryDelegate delegate) {
        this.delegate = delegate;

        JPanel mainPanel = new JPanel();
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        mainPanel.setLayout(gb);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 9; i++) {
            JLabel label = new JLabel(buttonLabels[i]);
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(400,50));
            button.add(label);
            button.addActionListener(this);
            button.setActionCommand(Integer.toString(i));

            c.gridwidth = GridBagConstraints.REMAINDER;
            c.insets = new Insets(3, 10, 3, 10);
            c.anchor = GridBagConstraints.CENTER;
            gb.setConstraints(button, c);

            mainPanel.add(button);
        }
        setContentPane(mainPanel);

        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (iw != null) iw.dispose();
        switch (e.getActionCommand()) {
            case "0":
                insertVacationPlanAction();
                break;
            case "1":
                deleteCustomerAction();
                break;
            case "2":
                updateReviewAction();
                break;
            case "3":
                selectVacationPriceAction();
                break;
            case "4":
                selectVacationDestinationAction();
                break;
            case "5":
                selectCruiseAction();
                break;
            case "6":
                selectReviewAverageAction();
                break;
            case "7":
                handleSelectEventCount();
                break;
            case "8":
                handleSelectVacationAll();
                break;
        }
    }

    private void insertVacationPlanAction() {
        iw = new InputWindow(new FieldType[] {FieldType.INTEGER, FieldType.DATE, FieldType.DATE, FieldType.DOUBLE},
                new String[] {"Plan ID", "Start Date", "End Date", "Price"}, 0,this);
    }

    private void deleteCustomerAction() {
        iw = new InputWindow(new FieldType[] {FieldType.STRING}, new String[] {"Email"}, 1, this);
    }

    private void updateReviewAction() {
        iw = new InputWindow(new FieldType[] {FieldType.INTEGER, FieldType.STRING}, new String[] {"Review ID", "New Description"}, 2, this);
    }

    private void selectVacationPriceAction() {
        iw = new InputWindow(new FieldType[] {FieldType.DOUBLE}, new String[] {"Price"}, 3, this);
    }

    private void selectVacationDestinationAction() {
        iw = new InputWindow(new FieldType[] {FieldType.STRING}, new String[] {"Country"}, 4, this);
    }

    private void selectCruiseAction() {
        iw = new InputWindow(new FieldType[] {FieldType.INTEGER}, new String[] {"Rating"}, 5, this);
    }

    private void selectReviewAverageAction() {
        iw = new InputWindow(new FieldType[] {FieldType.INTEGER}, new String[] {"Vacation Plan ID"}, 6, this);
    }

    protected void handleQuery(int queryNum, JTextField[] fieldArray) {
        String[] inputs = new String[fieldArray.length];
        for (int i = 0; i < fieldArray.length; i++) {
            inputs[i] = fieldArray[i].getText();
        }

        switch (queryNum) {
            case 0:
                handleInsertVacationPlan(inputs);
                break;
            case 1:
                handleDeleteCustomer(inputs);
                break;
            case 2:
                handleUpdateReview(inputs);
                break;
            case 3:
                handleSelectVacationPrice(inputs);
                break;
            case 4:
                handleSelectVacationDestination(inputs);
                break;
            case 5:
                handleSelectCruise(inputs);
                break;
            case 6:
                handleSelectReviewAverage(inputs);
                break;
        }
    }

    private void handleInsertVacationPlan(String[] inputs) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        try {
            int planID = Integer.parseInt(inputs[0]);
            LocalDate startDate = LocalDate.parse(inputs[1],formatter);
            LocalDate endDate = LocalDate.parse(inputs[2],formatter);
            double price = Double.parseDouble(inputs[3]);

            actionDialogBox(delegate.insertVacationPlan(planID, startDate, endDate, price));
        } catch (Exception e) {
            actionDialogBox(false);
        }
    }

    private void handleDeleteCustomer(String[] inputs) {
        actionDialogBox(delegate.deleteCustomer(inputs[0]));
    }

    private void handleUpdateReview(String[] inputs) {
        int reviewID = Integer.parseInt(inputs[0]);
        actionDialogBox(delegate.updateReview(reviewID, inputs[1]));
    }

    private void handleSelectVacationPrice(String[] inputs) {
        double price = Double.parseDouble(inputs[0]);
        List<VacationPlan> result = delegate.selectVacationPrice(price);
        if (result == null) {
            actionDialogBox(false);
            return;
        }

        Object[][] data = new Object[result.size()][4];
        String[] columnNames = {"Plan ID", "Start Date", "End Date", "Price"};
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        for (int i = 0; i < result.size(); i++) {
            VacationPlan plan = result.get(i);
            data[i][0] = plan.getPlanID();
            data[i][1] = plan.getStartDate().toString();
            data[i][2] = plan.getEndDate().toString();
            data[i][3] = plan.getPrice();
        }
        new OutputWindow(data, columnNames);
    }

    private void handleSelectVacationDestination(String[] inputs) {
        List<Integer> result = delegate.selectVacationDestination(inputs[0]);
        if (result == null) {
            actionDialogBox(false);
            return;
        }

        Object[][] data = new Object[result.size()][1];
        String[] columnNames = {"Plan ID"};
        for (int i = 0; i < result.size(); i++) {
            data[i][0] = result.get(i);
        }
        new OutputWindow(data, columnNames);
    }

    private void handleSelectCruise(String[] inputs) {
        int rating = Integer.parseInt(inputs[0]);
        List<List<String>> result = delegate.selectCruise(rating);
        if (result == null) {
            actionDialogBox(false);
            return;
        }

        Object[][] data = new Object[result.size()][4];
        String[] columnNames = {"Company Name", "Rating", "Cruise Model", "Features"};
        for (int i = 0; i < result.size(); i++) {
            data[i][0] = result.get(i).get(0);
            data[i][1] = result.get(i).get(1);
            data[i][2] = result.get(i).get(2);
            data[i][3] = result.get(i).get(3);
        }
        new OutputWindow(data, columnNames);
    }

    private void handleSelectReviewAverage(String[] inputs) {
        int planID = Integer.parseInt(inputs[0]);
        double result = delegate.selectReviewAverage(planID);
        if (result == -1) {
            actionDialogBox(false);
        } else if (result == 0) {
            JOptionPane.showMessageDialog(this, "There are no reviews for this plan");
        } else {
            JOptionPane.showMessageDialog(this, "The average rating is " + result);
        }
    }

    private void handleSelectEventCount() {
        List<List<Integer>> result = delegate.eventCount();
        if (result == null) {
            actionDialogBox(false);
            return;
        }

        Object[][] data = new Object[result.size()][2];
        String[] columnNames = {"Number of Events", "Plan ID"};
        for (int i = 0; i < result.size(); i++) {
            data[i][0] = result.get(i).get(0);
            data[i][1] = result.get(i).get(1);
        }
        OutputWindow ow = new OutputWindow(data, columnNames);
    }

    private void handleSelectVacationAll() {
        List<VacationPlan> result = delegate.vacationBookedByAll();
        if (result == null) {
            actionDialogBox(false);
            return;
        }

        Object[][] data = new Object[result.size()][4];
        String[] columnNames = {"Plan ID", "Start Date", "End Date", "Price"};
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        for (int i = 0; i < result.size(); i++) {
            VacationPlan plan = result.get(i);
            data[i][0] = plan.getPlanID();
            data[i][1] = plan.getStartDate().toString();
            data[i][2] = plan.getEndDate().toString();
            data[i][3] = plan.getPrice();
        }
        new OutputWindow(data, columnNames);
    }

    private void actionDialogBox(boolean isSuccessful) {
        if (isSuccessful) {
            JOptionPane.showMessageDialog(this, "Query Successfully Executed!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Database Error!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

