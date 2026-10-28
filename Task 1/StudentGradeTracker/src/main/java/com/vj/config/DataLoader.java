package com.vj.config;

import com.vj.model.Grade;
import com.vj.model.Student;
import com.vj.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/** Seeds a few demo students the first time the app runs against an empty database. */
@Component
public class DataLoader implements CommandLineRunner {

    private final StudentRepository repository;

    public DataLoader(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }
        String[] subjects = {"Mathematics", "Physics", "English"};
        repository.save(student("S001", "Aarav Sharma", subjects, new double[]{92, 88, 79}));
        repository.save(student("S002", "Priya Verma", subjects, new double[]{67, 74, 81}));
        repository.save(student("S003", "Rohan Mehta", subjects, new double[]{45, 52, 38}));
    }

    private Student student(String roll, String name, String[] subjects, double[] scores) {
        Student s = new Student();
        s.setRollNumber(roll);
        s.setName(name);
        for (int i = 0; i < subjects.length; i++) {
            Grade g = new Grade();
            g.setSubject(subjects[i]);
            g.setScore(scores[i]);
            s.addGrade(g);
        }
        return s;
    }
}
