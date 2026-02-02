/*
 Programmer: Theodore Kelly Cabrera
 Student ID: 25-2422-239
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class StudentRecords extends JFrame {

    DefaultTableModel model;
    JTable table;

    JTextField tfId, tfFirstName, tfLastName;
    JTextField tfLab1, tfLab2, tfLab3, tfPrelim, tfAttendance;

    public StudentRecords() {
        setTitle("Records - Theodore Kelly Cabrera 25-2422-239");
        setSize(950, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Table
        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Lab Work 1", "Lab Work 2", "Lab Work 3", "Prelim Exam", "Attendance"},
                0
        );
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Input fields
        tfId = new JTextField();
        tfFirstName = new JTextField();
        tfLastName = new JTextField();
        tfLab1 = new JTextField();
        tfLab2 = new JTextField();
        tfLab3 = new JTextField();
        tfPrelim = new JTextField();
        tfAttendance = new JTextField();

        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");

        // ===== Input Panel =====
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5,5));

        // Top grid: ID, Name, Lab1-3
        JPanel fieldsPanel = new JPanel(new GridLayout(2,6,5,5));
        fieldsPanel.add(new JLabel("ID"));
        fieldsPanel.add(new JLabel("First Name"));
        fieldsPanel.add(new JLabel("Last Name"));
        fieldsPanel.add(new JLabel("Lab 1"));
        fieldsPanel.add(new JLabel("Lab 2"));
        fieldsPanel.add(new JLabel("Lab 3"));

        fieldsPanel.add(tfId);
        fieldsPanel.add(tfFirstName);
        fieldsPanel.add(tfLastName);
        fieldsPanel.add(tfLab1);
        fieldsPanel.add(tfLab2);
        fieldsPanel.add(tfLab3);

        // Middle grid: Prelim, Attendance, Add button
        JPanel middlePanel = new JPanel(new GridLayout(2,3,5,5));
        middlePanel.add(new JLabel("Prelim Exam"));
        middlePanel.add(new JLabel("Attendance"));
        middlePanel.add(new JLabel("")); // empty

        middlePanel.add(tfPrelim);
        middlePanel.add(tfAttendance);
        middlePanel.add(btnAdd);

        // Bottom panel: Delete button centered
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnDelete);

        inputPanel.add(fieldsPanel, BorderLayout.NORTH);
        inputPanel.add(middlePanel, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.NORTH);

        // Load CSV
        loadCSV();

        // Add button logic
        btnAdd.addActionListener(e -> {
            String fullName = tfFirstName.getText() + " " + tfLastName.getText();

            model.addRow(new Object[]{
                    tfId.getText(),
                    fullName,
                    tfLab1.getText(),
                    tfLab2.getText(),
                    tfLab3.getText(),
                    tfPrelim.getText(),
                    tfAttendance.getText()
            });

            saveCSV(); // Save after adding

            // Clear inputs
            tfId.setText(""); tfFirstName.setText(""); tfLastName.setText("");
            tfLab1.setText(""); tfLab2.setText(""); tfLab3.setText("");
            tfPrelim.setText(""); tfAttendance.setText("");
        });

        // Delete button logic
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                model.removeRow(row);
                saveCSV(); // Save after deleting
            }
        });

        // Save on window close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveCSV();
            }
        });

        setVisible(true);
    }

    // Load CSV
    void loadCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("MOCK_DATA.csv"))) {
            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 8) continue;

                String id = parts[0];
                String fullName = parts[1] + " " + parts[2];

                model.addRow(new Object[]{
                        id,
                        fullName,
                        parts[3],
                        parts[4],
                        parts[5],
                        parts[6],
                        parts[7]
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading CSV");
        }
    }

    // Save CSV
    void saveCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("MOCK_DATA.csv"))) {
            pw.println("StudentID,FirstName,LastName,LAB WORK 1,LAB WORK 2,LAB WORK 3,PRELIM EXAM,ATTENDANCE GRADE");
            for (int i = 0; i < model.getRowCount(); i++) {
                String id = model.getValueAt(i, 0).toString();
                String[] nameParts = model.getValueAt(i, 1).toString().split(" ", 2);
                String first = nameParts[0];
                String last = nameParts.length > 1 ? nameParts[1] : "";

                pw.println(String.join(",",
                        id,
                        first,
                        last,
                        model.getValueAt(i,2).toString(),
                        model.getValueAt(i,3).toString(),
                        model.getValueAt(i,4).toString(),
                        model.getValueAt(i,5).toString(),
                        model.getValueAt(i,6).toString()
                ));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving CSV");
        }
    }

    public static void main(String[] args) {
        new StudentRecords();
    }
}
