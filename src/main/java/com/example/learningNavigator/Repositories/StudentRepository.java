package com.example.learningNavigator.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.learningNavigator.Entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}