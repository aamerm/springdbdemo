package com.example.springdbdemo.services;

import com.example.springdbdemo.models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {
    List<Student> findByName(String name);
}
