package com.example.learningNavigator.Services;

import java.util.List;

import com.example.learningNavigator.Entities.Subject;

public interface SubjectService {    
    List<Subject> getAllSubjects();
    Subject getSubjectById(Long id);
    Subject getSubjectByName(String name);
    Subject createSubject(Subject subject);
    Subject updateSubject(Long id,Subject subjectDetails);
    void deleteSubject(Long id);
} 
