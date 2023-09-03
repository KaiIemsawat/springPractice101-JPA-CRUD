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

	@Bean										/*DAO stands for Data Access Object*/
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);  				/* CREATE A STUDENT */
//			createMultipleStudents(studentDAO);  		/* CREATE MULTIPLE STUDENTS */
//			readStudent(studentDAO);  					/* GET A STUDENT BY ID */
//			queryForStudents(studentDAO);  				/* FIND ALL STUDENT */
//			queryForStudentsByFirstName(studentDAO);  	/* FIND A STUDENT BY FIRSTNAME */
//			updateStudent(studentDAO); 					/* UPDATE A STUDENT */
//			deleteStudent(studentDAO); 					/* DELETE A STUDENT */
			deleteAllStudents(studentDAO);				/* DELETE ALL STUDENTS */
		};
	}

	/* CREATE A STUDENT */
	private void createStudent(StudentDAO studentDAO) {
//		Create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Bianchi", "Italy", "bianchi@italy.com");

//		Save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

//		Display id of the saved student
		System.out.println("saved student. Generated id: " + tempStudent.getId());
	}

	/* CREATE MULTIPLE STUDENTS */
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

	/* GET A STUDENT BY ID */
	private void readStudent(StudentDAO studentDAO) {
//		Display id of the saved student
		int theId = 1;
		System.out.println("Saved student. Generated id: " + theId);

//		Retrieve student based on the id: primary key
		System.out.println("Retrieve student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

//		Display student
		System.out.println("The student : " + myStudent);
	}

	/* FIND ALL STUDENT */
	private void queryForStudents(StudentDAO studentDAO) {
//		Get a list of students
		List<Student> theStudents = studentDAO.findAll();

//		Display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	/* FIND A STUDENT BY FIRSTNAME */
	private void queryForStudentsByFirstName(StudentDAO studentDAO) {
//		get a list of students
		List<Student> theStudents = studentDAO.findByFirstName("Stelbel");
//		display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	/* UPDATE A STUDENT */
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

	/* DELETE A STUDENT */
	private void deleteStudent(StudentDAO studentDAO) {

//		Use methods from find all students to see what we have before delete
//		Just for comparing purposes
		List<Student> theStudentsBeforeDelete = studentDAO.findAll();
		for (Student tempStudent : theStudentsBeforeDelete) {
			System.out.println(tempStudent);
		}
		System.out.println("----");
//		Deleting
		int studentId = 7; // assign student id that will be deleted
		System.out.println("Deleting student with id : " + studentId);
		studentDAO.delete(studentId);
//		Call print all students again to compare the students in the list
		List<Student> theStudentsAfterDelete = studentDAO.findAll();
		for (Student tempStudent : theStudentsAfterDelete) {
			System.out.println(tempStudent);
		}
	}

	/* DELETE ALL STUDENTS */
	private void deleteAllStudents(StudentDAO studentDAO) {
		studentDAO.deleteAll();
		System.out.println("Deleted all students");
	}
}
