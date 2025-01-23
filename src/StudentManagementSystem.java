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

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        if (!name.matches("[a-zA-Z]+")) {
            System.out.println("Error: Student name must contain only letters.");
            return;
        }

        if (students.containsKey(name)) {
            System.out.println("Error: Student with this name already exists.");
        } else {
            students.put(name, new Student(name));
            System.out.println("Student " + name + " added successfully.");
        }
    }

    private static void recordGrade(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        if (!students.containsKey(name)) {
            System.out.println("Error: Student not found.");
            return;
        }

        Student student = students.get(name);
        while (true) {
            System.out.print("Enter grade (or type 'done' to stop): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                int grade = Integer.parseInt(input);
                if (grade < 0 || grade > 100) {
                    System.out.println("Grade must be between 0 and 100.");
                } else {
                    student.addGrade(grade);
                    System.out.println("Grade recorded.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid grade. Please enter a numeric value.");
            }
        }
    }
    private static void viewStudentDetails(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        Student student = students.get(name);
        if (student == null) {
            System.out.println("Error: Student not found.");
        } else {
            System.out.println("Student: " + student.name);
            System.out.println("Grades: " + student.getGrades());
            System.out.println("Average Grade: " + student.getAverageGrade());
        }
    }
    private static void viewHighPerformingStudents(Scanner scanner) {
        System.out.print("Enter grade threshold: ");
        double threshold;
        while (true) {
            try {
                threshold = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a numeric value: ");
            }
        }

        System.out.println("Students with average grade above " + threshold + ":");
        boolean found = false;
        for (Student student : students.values()) {
            if (student.getAverageGrade() > threshold) {
                System.out.println(student.name + " - Average Grade: " + student.getAverageGrade());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found with average grade above " + threshold);
        }
    }
}