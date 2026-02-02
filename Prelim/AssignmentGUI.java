import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Attendance Tracker using Java Swing
 * BSCSIT 1203L - Lab Work 1
 */
public class AttendanceTracker {

    public static void main(String[] args) {

        // Create the main window (JFrame)
        JFrame frame = new JFrame("Attendance Tracker");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with grid layout (4 rows, 2 columns)
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Create labels
        JLabel nameLabel = new JLabel("Attendance Name:");
        JLabel courseLabel = new JLabel("Course / Year:");
        JLabel timeLabel = new JLabel("Time In:");
        JLabel signatureLabel = new JLabel("E-Signature:");

        // Create text fields
        JTextField nameField = new JTextField();
        JTextField courseField = new JTextField();
        JTextField timeField = new JTextField();
        JTextField signatureField = new JTextField();

        // Get current system date and time
        String timeIn = LocalDateTime.now().toString();
        timeField.setText(timeIn);
        timeField.setEditable(false); // prevent editing

        // Generate E-Signature using UUID
        String eSignature = UUID.randomUUID().toString();
        signatureField.setText(eSignature);
        signatureField.setEditable(false); // prevent editing

        // Add components to panel
        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(courseLabel);
        panel.add(courseField);

        panel.add(timeLabel);
        panel.add(timeField);

        panel.add(signatureLabel);
        panel.add(signatureField);

        // Add panel to frame
        frame.add(panel);

        // Make the window visible
        frame.setVisible(true);
    }
}
