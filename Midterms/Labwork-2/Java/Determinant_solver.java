public class determinant_solver {

    static int[] matrix = {
        2, 3, 1,
        5, 1, 4,
        3, 2, 6
    };

    public static void main(String[] args) {
        int determinant =
            matrix[0] * ((matrix[4] * matrix[8]) - (matrix[5] * matrix[7]))
          - matrix[1] * ((matrix[3] * matrix[8]) - (matrix[5] * matrix[6]))
          + matrix[2] * ((matrix[3] * matrix[7]) - (matrix[4] * matrix[6]));

        System.out.println(determinant);
    }
}
