package com.example.learningNavigator.Services;

import java.util.List;

import com.example.learningNavigator.Entities.Student;

public interface StudentService {

    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student addStudent(Student student);
    Student updateStudent(Long id,Student student);
    void deleteStudent(Long id);
    Student registerForExam(Long studentId, Long examId);
}
