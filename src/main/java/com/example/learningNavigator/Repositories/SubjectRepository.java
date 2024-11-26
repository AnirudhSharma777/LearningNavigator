package com.example.learningNavigator.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.learningNavigator.Entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long>{
    Subject findByName(String name);
}
