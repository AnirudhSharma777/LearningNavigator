package com.example.learningNavigator.Services;

import java.util.List;

import com.example.learningNavigator.Entities.Exam;

public interface ExamService {

    Exam createExam(Exam exam);
    List<Exam> getAllExams();
    Exam getExamById(Long id);
    Exam updateExam(Long id,Exam exam);
    void deleteExam(Long id);
} 
