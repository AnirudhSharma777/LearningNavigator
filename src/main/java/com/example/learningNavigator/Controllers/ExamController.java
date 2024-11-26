package com.example.learningNavigator.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningNavigator.Entities.Exam;
import com.example.learningNavigator.Entities.Student;
import com.example.learningNavigator.Entities.Subject;
import com.example.learningNavigator.Services.ExamServiceImpl;
import com.example.learningNavigator.Services.StudentServiceImpl;
import com.example.learningNavigator.Services.SubjectServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamServiceImpl examService;

    @Autowired
    private SubjectServiceImpl subjectService;

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/id")
    public ResponseEntity<?> createNewExamById(@RequestBody Exam exam) {
        // Check if the subject exists
        Subject subject = subjectService.getSubjectById(exam.getSubject().getId());
        if (subject == null) {
            return ResponseEntity.badRequest().body("This Subject is not found in the Subject directory");
        }
        exam.setSubject(subject);
        Exam newExam = examService.createExam(exam);
        return ResponseEntity.status(201).body(newExam);
    }

    @PostMapping("/name")
    public ResponseEntity<?> createNewExamByName(@RequestBody Map<String, String> request) {
        // Extract subject name from the request
        String subjectName = request.get("name");

        // Fetch the subject by name
        Subject subject = subjectService.getSubjectByName(subjectName);
        if (subject == null) {
            return ResponseEntity.badRequest()
                    .body("Subject with name '" + subjectName + "' not found in Subject directory");
        }
        // Create a new Exam
        Exam exam = new Exam();
        exam.setSubject(subject);
        Exam newExam = examService.createExam(exam);

        return ResponseEntity.status(201).body(newExam);
    }

    @GetMapping
    public List<Exam> getListExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable("id") Long id){
        Exam exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable("id") Long id, @RequestBody Exam examDetails) {
        //Validate that Subjet exists
        Subject subject = subjectService.getSubjectById(examDetails.getSubject().getId());
        examDetails.setSubject(subject);

        Exam updatedExam = examService.updateExam(id, examDetails);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable("id") Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{examId}/register-student/{studentId}")
    public ResponseEntity<?> registerStudentForExam(@PathVariable Long examId, @PathVariable Long studentId) {
        // Fetch the exam by ID
        Exam exam = examService.getExamById(examId);
        if (exam == null) {
            return ResponseEntity.status(404).body("Exam with ID " + examId + " not found.");
        }
    
        // Fetch the student by ID
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.status(404).body("Student with ID " + studentId + " not found.");
        }
    
        // Check if the student is enrolled in the subject of the exam
        if (!student.getEnrolledSubjects().contains(exam.getSubject())) {
            return ResponseEntity.status(400).body("Student is not enrolled in the subject of this exam.");
        }
    
        // Add the student to the exam and vice versa if not already enrolled
        if (!exam.getEnrolledStudents().contains(student)) {
            exam.getEnrolledStudents().add(student);
        }
    
        if (!student.getRegisteredExams().contains(exam)) {
            student.getRegisteredExams().add(exam);
        }
    
        // Save updates
        examService.createExam(exam); // Persist exam updates
        studentService.addStudent(student); // Persist student updates
    
        return ResponseEntity.ok(exam);
    }
    
}
