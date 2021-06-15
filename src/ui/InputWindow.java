package ui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class InputWindow extends JFrame  {
    private static final int TEXT_FIELD_WIDTH = 10;
    private JTextField[] inputFields;
    private BookingWindow bw;
    // 0 -> int
    // 1 -> double
    // 2 -> String
    // 3 -> Date
    public InputWindow(FieldType[] inputArray, String[] labelArray, int queryNum, BookingWindow bw) {
        super("Required Inputs");
        this.bw = bw;

        inputFields = new JTextField[inputArray.length];

        JPanel contentPane = new JPanel();
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < inputArray.length; i++) {
            JLabel fieldLabel = new JLabel(labelArray[i] + ": ");
            inputFields[i] = new JTextField(TEXT_FIELD_WIDTH);

            // place the label
            c.gridwidth = GridBagConstraints.RELATIVE;
            c.insets = new Insets(10, 10, 5, 0);
            gb.setConstraints(fieldLabel, c);
            contentPane.add(fieldLabel);

            // place the field
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.insets = new Insets(10, 0, 5, 10);
            gb.setConstraints(inputFields[i], c);
            contentPane.add(inputFields[i]);
        }

        JButton submitButton = new JButton("OK");
        submitButton.addActionListener(e -> {
            if (validateFields(inputArray)) {
                bw.handleQuery(queryNum, inputFields);
                dispose();
            } else {
                popupError();
                clearFields();
            }
        });
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(submitButton, c);
        contentPane.add(submitButton);

        setContentPane(contentPane);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private boolean validateFields(FieldType[] inputArray) {
        for (int i = 0; i < inputFields.length; i++) {
            String s = inputFields[i].getText();
            if (s.isEmpty()) return false;
            switch (inputArray[i]) {
                case INTEGER:
                    try {
                        Integer.parseInt(s);
                    } catch (Exception e) {
                        return false;
                    }
                    break;
                case DOUBLE:
                    try {
                        Double.parseDouble(s);
                    } catch (Exception e) {
                        return false;
                    }
                    break;
                case DATE:
                    try {
                        new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(s);
                    } catch (Exception e) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    private void popupError() {
        JOptionPane.showMessageDialog(this,"Fields are incorrect\n Please try again");
    }

    private void clearFields() {
        for (JTextField field : inputFields) {
            field.setText("");
        }
    }
}
