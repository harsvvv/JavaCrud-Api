package org.example.firstproject.repository;

import org.example.firstproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional findByEmail(String email);

    Optional<Student> findById(Long id);
}
