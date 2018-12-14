package com.myappnadir.student.dal;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myappnadir.student.dal.entities.Student;
import com.myappnadir.student.dal.repos.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentdalApplicationTests {

	@Autowired
	private StudentRepository studentRepository;
	@Test
	public void testCreateStudent() {
		
		Student student = new Student();
		student.setName("John");
		student.setCourse("Java web service");
		student.setFee(30d);
		studentRepository.save(student);
	}
	@Test
	public void testFindStudentById() {
		Long id =1l;
		Student student=studentRepository.findById(id).orElseThrow(null);
		System.out.println(student);
	}

	@Test
	public void testApdateStudent() {
		Long id =1l;
		Student student=studentRepository.findById(id).orElseThrow(null);
		student.setFee(40d);
	}
	@Test
	public void testDeleteStudent() {
		
		Student student = new Student();
		student.setId(1l);
		studentRepository.delete(student);
	}
}
