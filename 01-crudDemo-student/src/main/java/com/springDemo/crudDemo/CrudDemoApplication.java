package com.springDemo.crudDemo;

import com.springDemo.crudDemo.dao.StudentDAO;
import com.springDemo.crudDemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);

//			createMultipleStudents(studentDAO);

//			readStudent(studentDAO);

//			queryForStudents(studentDAO);

//			queryForStudentsByLastName(studentDAO);

			updateStudent(studentDAO);
		};
	}

	private void updateStudent(StudentDAO studentDAO) {
//		retrieve student base on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id : " + studentId);
		Student thisStudent = studentDAO.findById(studentId);
		System.out.println("Before : " + thisStudent);

//		change first name
		System.out.println("Updating the student...");
		thisStudent.setFirstName("Tann");

//		update the student
		studentDAO.update(thisStudent);

//		display the updated student
		System.out.println("After : " + thisStudent);

	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
//		get a list of students
		List<Student> theStudents = studentDAO.findByFirstName("Stelbel");
//		display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
//		Get a list of students
		List<Student> theStudents = studentDAO.findAll();

//		Display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
//		Create a student object
		System.out.println("Creating new student object....");
		Student tempStudent = new Student("Bianchi", "Italy", "bianchi@italy.com");

//		Save the student
		System.out.println("Saving the student");
		studentDAO.save(tempStudent);

//		Display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

//		Retrieve student based on the id: primary key
		System.out.println("Retrieve student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

//		Display student
		System.out.println("The student : " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
//		Create the student object
		System.out.println("Creating 3 new student objects...");
		Student tempStudent1 = new Student("Titann", "Hampton", "titann@hampton.com");
		Student tempStudent2 = new Student("Wilier", "Hampton", "wilier@hampton.com");
		Student tempStudent3 = new Student("Stelbel", "Hampton", "stelbel@hampton.com");

//		Save the student object
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
//		Create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Zukkii", "Hampton", "zukkii@hampton.com");

//		Save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

//		Display id of the saved student
		System.out.println("saved student. Generated id: " + tempStudent.getId());
	}
}
