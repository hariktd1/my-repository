package com.example.mongo.audit.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mongo.audit.repository.documents.Student;



/**
 * 
 * @author Harikrishnan P 
 * Class StudentRepository
 *
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {
	
	Student findById(String id);
	
	@Query("{'name' : null}")
	List<Student> findStudentByNameIsNull();
	
	Student findByName(String name);

}
