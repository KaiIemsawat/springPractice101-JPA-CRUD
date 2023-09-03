package com.springDemo.crudDemo.dao;

import com.springDemo.crudDemo.entity.Student;

import java.util.List;

/*DAO stands for Data Access Object*/
public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByFirstName(String theFirstNae);

    void update(Student theStudent);

    void delete(Integer id);

    void deleteAll();
}
