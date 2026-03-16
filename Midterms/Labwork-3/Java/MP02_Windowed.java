import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class MP02_TableGUI {

    JTextField pathField;
    JTable table;
    DefaultTableModel model;

    public MP02_TableGUI() {

        JFrame frame = new JFrame("MP02 - Display First 10 Rows");
        frame.setSize(900,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        JLabel label = new JLabel("Dataset Path:");
        pathField = new JTextField(40);

        JButton loadButton = new JButton("Load CSV");

        topPanel.add(label);
        topPanel.add(pathField);
        topPanel.add(loadButton);

        model = new DefaultTableModel();
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        loadButton.addActionListener(e -> loadCSV());

        frame.setVisible(true);
    }

    void loadCSV() {

        String filePath = pathField.getText();

        model.setRowCount(0);
        model.setColumnCount(0);

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            boolean headerFound = false;
            int rowCount = 0;

            while((line = reader.readLine()) != null) {

                if(!headerFound) {

                    if(line.contains("Candidate") && line.contains("Exam")) {

                        headerFound = true;

                        String[] headers = line.split(",");

                        for(String h : headers) {
                            model.addColumn(h);
                        }

                    }

                    continue;
                }

                if(rowCount >= 10)
                    break;

                if(!line.trim().isEmpty()) {

                    String[] row = line.split(",");

                    model.addRow(row);
                    rowCount++;

                }

            }

            reader.close();

        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(null,
                    "Error reading file: " + e.getMessage());

        }

    }

    public static void main(String[] args) {

        new MP02_TableGUI();

    }
}