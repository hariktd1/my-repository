package com.example.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.audit.repository.entity.Student;

/**
 * 
 * @author Harikrishnan P
 * Class StudentRepository
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
