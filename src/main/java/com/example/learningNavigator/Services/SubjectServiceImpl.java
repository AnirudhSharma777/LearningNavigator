package com.example.learningNavigator.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningNavigator.Entities.Subject;
import com.example.learningNavigator.Exceptions.ResourceNotFoundException;
import com.example.learningNavigator.Repositories.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject is not found with id " + id));
    }

    @Override
    public Subject getSubjectByName(String name) {
        return subjectRepository.findByName(name);
    }

    @Override
    public Subject updateSubject(Long id, Subject subjectDetails) {
        Subject subject = getSubjectById(id);
        subject.setName(subjectDetails.getName());
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Long id) {
       Subject subject = getSubjectById(id);
       subjectRepository.delete(subject);
    }

}
