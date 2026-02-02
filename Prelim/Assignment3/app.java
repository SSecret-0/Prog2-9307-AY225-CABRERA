import java.util.Scanner;

public class PrelimLabWork3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // User input
        System.out.print("Enter Attendance score (0-100): ");
        double attendance = sc.nextDouble();

        System.out.print("Enter Lab Work 1 grade: ");
        double lab1 = sc.nextDouble();

        System.out.print("Enter Lab Work 2 grade: ");
        double lab2 = sc.nextDouble();

        System.out.print("Enter Lab Work 3 grade: ");
        double lab3 = sc.nextDouble();

        // Computations
        double labAverage = (lab1 + lab2 + lab3) / 3.0;
        double classStanding = (attendance * 0.4) + (labAverage * 0.6);

        double requiredToPass = (75 - (classStanding * 0.3)) / 0.7;
        double requiredExcellent = (100 - (classStanding * 0.3)) / 0.7;

        // Output
        System.out.println("\n=== Computed Results ===");
        System.out.printf("Attendance Score: %.2f\n", attendance);
        System.out.printf("Lab Work Average: %.2f\n", labAverage);
        System.out.printf("Class Standing: %.2f\n", classStanding);
        System.out.printf("Required Prelim Exam Score to Pass (75): %.2f\n", requiredToPass);
        System.out.printf("Required Prelim Exam Score to Achieve Excellent (100): %.2f\n", requiredExcellent);

        // Remarks
        if (requiredToPass <= 0) {
            System.out.println("Remark: You already secured passing grade.");
        } else if (requiredToPass <= 100) {
            System.out.println("Remark: Passing is achievable with Prelim Exam.");
        } else {
            System.out.println("Remark: Passing requires more than 100, check class standing.");
        }

        sc.close();
    }
}
