package com.example.reactive.config.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.reactive.config.repository.documents.Student;



/**
 * 
 * @author Harikrishnan P 
 * Class StudentRepository
 *
 */
@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
	
	//Mono<Void> deleteById(String id);
	
}
