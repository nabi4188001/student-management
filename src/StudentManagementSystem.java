package src;

import java.util.*;

public class StudentManagementSystem {
    private static class Student {
        String name;
        List<Integer> grades;

        public Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        public void addGrade(int grade) {
            grades.add(grade);
        }

        public double getAverageGrade() {
            if (grades.isEmpty()) {
                return 0.0;
            }
            double sum = 0;
            for (int grade : grades) {
                sum += grade;
            }
            return Math.round((sum / grades.size()) * 100.0) / 100.0;
        }

        public List<Integer> getGrades() {
            return grades;
        }
    }

    private static Map<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMenu();
            int choice = getChoice(scanner);

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    recordGrade(scanner);
                    break;
                case 3:
                    viewStudentDetails(scanner);
                    break;
                case 4:
                    viewHighPerformingStudents(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nStudent Management System");
        System.out.println("1. Add Student");
        System.out.println("2. Record Grade");
        System.out.println("3. View Student Details");
        System.out.println("4. View High-Performing Students");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }
    private static int getChoice(Scanner scanner) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 5) {
                    throw new IllegalArgumentException("Choice must be between 1 and 5.");
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number between 1 and 5: ");
            } catch (IllegalArgumentException e) {
                System.out.print(e.getMessage() + " Please try again: ");
            }
        }
    }
}