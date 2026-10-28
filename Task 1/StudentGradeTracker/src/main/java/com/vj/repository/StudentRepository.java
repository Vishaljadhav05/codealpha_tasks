package com.vj.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vj.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByRollNumber(String rollNumber);

    @Query("""
           select distinct s from Student s left join fetch s.grades
           where lower(s.name) like lower(concat('%', :q, '%'))
              or lower(s.rollNumber) like lower(concat('%', :q, '%'))
           order by s.name
           """)
    List<Student> search(@Param("q") String q);

    @Query("select s from Student s left join fetch s.grades where s.id = :id")
    Optional<Student> findWithGrades(@Param("id") Long id);
}
