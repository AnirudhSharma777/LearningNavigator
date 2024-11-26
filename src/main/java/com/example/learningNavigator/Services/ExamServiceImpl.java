package com.example.learningNavigator.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningNavigator.Entities.Exam;
import com.example.learningNavigator.Exceptions.ResourceNotFoundException;
import com.example.learningNavigator.Repositories.ExamRepository;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id " + id));
    }

    @Override
    public Exam updateExam(Long id,Exam exam) {
        Exam existingExam = getExamById(id);
        existingExam.setSubject(exam.getSubject());
        return examRepository.save(existingExam);
    }

   

    @Override
    public void deleteExam(Long id) {
      Exam exam = getExamById(id);
      examRepository.delete(exam);
    }

}
