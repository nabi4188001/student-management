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

