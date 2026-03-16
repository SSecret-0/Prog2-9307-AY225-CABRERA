import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class DataAuditGUI extends JFrame {

    private JTextField pathField;
    private JTextArea outputArea;

    public DataAuditGUI() {

        setTitle("Data Cleaning and Validation Report");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // ===== TOP PANEL =====
        JPanel topPanel = new JPanel(new BorderLayout());

        pathField = new JTextField();
        JButton browseButton = new JButton("Browse");
        JButton analyzeButton = new JButton("Analyze");

        topPanel.add(pathField, BorderLayout.CENTER);
        topPanel.add(browseButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ===== CENTER OUTPUT =====
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // ===== BOTTOM BUTTON =====
        add(analyzeButton, BorderLayout.SOUTH);

        // ===== ACTIONS =====

        browseButton.addActionListener(e -> chooseFile());

        analyzeButton.addActionListener(e -> analyzeFile());

        setVisible(true);
    }

    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            pathField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void analyzeFile() {

        String path = pathField.getText().trim();

        File file = new File(path);

        // ===== VALIDATION =====
        if (!file.exists()) {
            showError("File does not exist.");
            return;
        }

        if (!file.isFile()) {
            showError("Path is not a file.");
            return;
        }

        if (!file.canRead()) {
            showError("File is not readable.");
            return;
        }

        if (!path.toLowerCase().endsWith(".csv")) {
            showError("File is not in CSV format.");
            return;
        }

        // ===== PROCESSING =====
        int missingCount = 0;
        int negativeSalesCount = 0;
        int invalidDateCount = 0;
        int duplicateCount = 0;

        int totalRecords = 0;

        Set<String> uniqueRecords = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] data = line.split(",");

                if (data.length < 4) {
                    missingCount++;
                    continue;
                }

                DataRecord record = new DataRecord(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim()
                );

                if (record.hasMissingValues())
                    missingCount++;

                if (record.hasNegativeSales())
                    negativeSalesCount++;

                if (!record.isValidDate())
                    invalidDateCount++;

                String key = record.generateUniqueKey();
                if (uniqueRecords.contains(key))
                    duplicateCount++;
                else
                    uniqueRecords.add(key);

                totalRecords++;
            }

        } catch (IOException e) {
            showError("Error reading file: " + e.getMessage());
            return;
        }

        // ===== OUTPUT REPORT =====
        outputArea.setText("");
        outputArea.append("===== DATA QUALITY REPORT =====\n\n");
        outputArea.append("Total Records Processed : " + totalRecords + "\n");
        outputArea.append("Missing Values Detected : " + missingCount + "\n");
        outputArea.append("Negative Sales Detected : " + negativeSalesCount + "\n");
        outputArea.append("Invalid Dates Detected  : " + invalidDateCount + "\n");
        outputArea.append("Duplicate Records Found : " + duplicateCount + "\n");
        outputArea.append("\n================================");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DataAuditGUI::new);
    }
}