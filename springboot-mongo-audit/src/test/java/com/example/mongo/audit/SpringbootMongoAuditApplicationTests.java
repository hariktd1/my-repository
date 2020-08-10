package com.example.mongo.audit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mongo.audit.repository.StudentRepository;
import com.example.mongo.audit.repository.documents.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootMongoAuditApplicationTests {

	@Autowired
	private StudentRepository studentRepository;
	
	private Student student;
	
	@Test
	public void test() {
		student = new Student();
		student.setName("Test");
		student.setMark(80);
		student = studentRepository.save(student

		);
		assertThat(student.getId()).isNotNull();

		assertThat(student.getName()).isEqualTo("Test");
		assertThat(student.getCreated()).isNotNull();

		assertThat(student.getModified()).isNotNull();

		assertThat(student.getCreatedBy()).isEqualTo("hariktd1@gmail.com");

		assertThat(student.getModifiedBy()).isEqualTo("hariktd1@gmail.com");
		
		assertThat(student.getMark()).isEqualTo(80);
		
	}
	

}
