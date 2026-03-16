import javax.swing.*;
import java.awt.*;

public class Bonus {

    static JTextField[] fields = new JTextField[9];

    public static void main(String[] args) {

        JFrame frame = new JFrame("3x3 Matrix Determinant");
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3,3));

        for(int i = 0; i < 9; i++){
            fields[i] = new JTextField();
            fields[i].setHorizontalAlignment(JTextField.CENTER);
            grid.add(fields[i]);
        }

        JButton button = new JButton("Calculate");

        JLabel result = new JLabel("Determinant: ");
        result.setHorizontalAlignment(JLabel.CENTER);

        button.addActionListener(e -> {

            int[] matrix = new int[9];

            for(int i = 0; i < 9; i++){
                matrix[i] = Integer.parseInt(fields[i].getText());
            }

            int determinant =
                    matrix[0] * ((matrix[4] * matrix[8]) - (matrix[5] * matrix[7]))
                  - matrix[1] * ((matrix[3] * matrix[8]) - (matrix[5] * matrix[6]))
                  + matrix[2] * ((matrix[3] * matrix[7]) - (matrix[4] * matrix[6]));

            result.setText("Determinant: " + determinant);
        });

        frame.add(grid, BorderLayout.CENTER);
        frame.add(button, BorderLayout.NORTH);
        frame.add(result, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}