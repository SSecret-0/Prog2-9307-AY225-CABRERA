import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class MP03_SearchGUI {

    JTextField pathField;
    JTextField keywordField;
    JTable table;
    DefaultTableModel model;

    public MP03_SearchGUI() {

        JFrame frame = new JFrame("MP03 - Search Dataset");
        frame.setSize(950,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        JLabel pathLabel = new JLabel("Dataset Path:");
        pathField = new JTextField(30);

        JLabel keywordLabel = new JLabel("Keyword:");
        keywordField = new JTextField(15);

        JButton searchButton = new JButton("Search");

        topPanel.add(pathLabel);
        topPanel.add(pathField);
        topPanel.add(keywordLabel);
        topPanel.add(keywordField);
        topPanel.add(searchButton);

        model = new DefaultTableModel();
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        searchButton.addActionListener(e -> searchDataset());

        frame.setVisible(true);
    }

    void searchDataset() {

        String filePath = pathField.getText();
        String keyword = keywordField.getText().toLowerCase();

        model.setRowCount(0);
        model.setColumnCount(0);

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            boolean headerFound = false;

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

                if(line.toLowerCase().contains(keyword)) {

                    String[] row = line.split(",");
                    model.addRow(row);

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

        new MP03_SearchGUI();

    }
}