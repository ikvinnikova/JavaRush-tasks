package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University{
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student studentWithAverageGrade = null;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getAverageGrade() == averageGrade) {
                studentWithAverageGrade = students.get(i);
                i = students.size();
            }
        }
        return studentWithAverageGrade;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        double maxGrade = 0;
        Student student = null;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getAverageGrade() > maxGrade) {
                student = students.get(i);
                maxGrade = student.getAverageGrade();
            }
        }
        return student;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        double minGrade = students.get(0).getAverageGrade();
        Student student = null;
        for (int i = 1; i < students.size(); i++) {
            if (students.get(i).getAverageGrade() < minGrade) {
                student = students.get(i);
                minGrade = student.getAverageGrade();
            }
        }
        return student;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}