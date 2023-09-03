package com.springDemo.crudDemo.dao;

import com.springDemo.crudDemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    //    Define field for entity manager
    private EntityManager entityManager;

    //    Inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //    Implement save method
    @Override
    @Transactional // need for performing add/update/delete
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
//        Create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
//              "FROM Student" <- Name of JPA Entity, not database table
//               The syntax is very similar to writing SQL ('ASC' is optional since its default)
                "FROM Student ORDER BY firstName ASC", Student.class);
//        Return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByFirstName(String theFirstName) {
//        Create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
//                                            JPQL named parameters are prefixed with 'a colon'.
//                                            It works as a placeholder
                "FROM Student WHERE firstName=:theData", Student.class);

//        Set query parameters
        theQuery.setParameter("theData", theFirstName); // "theData" comes from JPQL named parameters above
//        Return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional // need for performing add/update/delete
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional // need for performing add/update/delete
    public void delete(Integer id) {
//        Retrieve the student
        Student theStudent = entityManager.find(Student.class, id);
//        Delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional // need for performing add/update/delete
    public void deleteAll() {
        int numberOfRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        System.out.println(numberOfRowsDeleted + " rows of students data have been deleted");
    }

}
