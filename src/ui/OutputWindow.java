package ui;

import javax.swing.*;
import java.awt.*;


public class OutputWindow extends JFrame {
    public OutputWindow(Object[][] data, String[] columnNames) {
        super("Result Set");

        JTable table = new JTable(data, columnNames);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);

        setContentPane(scrollPane);

        setPreferredSize(new Dimension(600, 400));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
