package com.example.learningNavigator.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningNavigator.Entities.Exam;
import com.example.learningNavigator.Entities.Student;
import com.example.learningNavigator.Entities.Subject;
import com.example.learningNavigator.Exceptions.ResourceNotFoundException;
import com.example.learningNavigator.Repositories.ExamRepository;
import com.example.learningNavigator.Repositories.StudentRepository;
import com.example.learningNavigator.Repositories.SubjectRepository;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ExamRepository examRepository;


    @Override
    public Student addStudent(Student student) {
       return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
       return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student is not found with this id "+ id));
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existstudent = getStudentById(id);
        existstudent.setName(student.getName());
        existstudent.setEnrolledSubjects(student.getEnrolledSubjects());
        existstudent.setRegisteredExams(student.getRegisteredExams());
        return studentRepository.save(existstudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    @Override
    public Student registerForExam(Long studentId, Long examId) {
       Student student = getStudentById(studentId);
       Exam exam = examRepository.findById(examId).orElseThrow(() -> new ResourceNotFoundException("Exam registration not found with this id "+examId));
       Subject subject = exam.getSubject();
       if(!student.getEnrolledSubjects().contains(subject)){
        throw new IllegalArgumentException("Student must be enrolled in the subject before registering for the exam");
       }
       student.getRegisteredExams().add(exam);
       return studentRepository.save(student);
    }

}
