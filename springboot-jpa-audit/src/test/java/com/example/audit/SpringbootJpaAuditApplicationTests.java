package com.example.audit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.audit.repository.StudentRepository;
import com.example.audit.repository.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootJpaAuditApplicationTests {


	@Autowired
	private StudentRepository studentRepository;

	private Student student;
	
	@Test
	public void test() {
		student = new Student();
		student.setName("Hari");
		student.setMark(90);
		student = studentRepository.save(student

		);
		assertThat(student.getCreated()).isNotNull();

		assertThat(student.getModified()).isNotNull();

		assertThat(student.getCreatedBy()).isEqualTo("hariktd1@gmail.com");

		assertThat(student.getModifiedBy()).isEqualTo("hariktd1@gmail.com");
	}
}
