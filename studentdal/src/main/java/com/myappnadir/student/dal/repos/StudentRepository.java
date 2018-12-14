package com.myappnadir.student.dal.repos;



import org.springframework.data.jpa.repository.JpaRepository;

import com.myappnadir.student.dal.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
