import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MP04_CountRowsGUI {

    JTextField pathField;
    JLabel resultLabel;

    public MP04_CountRowsGUI() {

        JFrame frame = new JFrame("MP04 - Count Valid Rows");
        frame.setSize(600,250); // increased height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top panel (input + button)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Dataset Path:");

        pathField = new JTextField(30);

        JButton countButton = new JButton("Count Rows");

        topPanel.add(label);
        topPanel.add(pathField);
        topPanel.add(countButton);

        // Result panel
        JPanel resultPanel = new JPanel();

        resultLabel = new JLabel("Valid Rows: 0");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));

        resultPanel.add(resultLabel);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(resultPanel, BorderLayout.CENTER);

        countButton.addActionListener(e -> countRows());

        frame.setVisible(true);
    }

    void countRows() {

        String filePath = pathField.getText();
        int validRows = 0;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            boolean headerFound = false;

            while((line = reader.readLine()) != null) {

                if(!headerFound) {

                    if(line.contains("Candidate") && line.contains("Exam")) {
                        headerFound = true;
                    }

                    continue;
                }

                if(!line.trim().isEmpty()) {
                    validRows++;
                }

            }

            reader.close();

            resultLabel.setText("Valid Rows: " + validRows);

        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(null,
                    "Error reading file: " + e.getMessage());

        }

    }

    public static void main(String[] args) {

        new MP04_CountRowsGUI();

    }
}