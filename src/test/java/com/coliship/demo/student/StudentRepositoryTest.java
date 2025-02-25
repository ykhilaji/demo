package com.coliship.demo.student;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coliship.demo.ServiceColishipApplication;

@SpringBootTest(classes = ServiceColishipApplication.class)
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository repository;


    @Test
    void contextLoads() {
        assertNotNull(repository);
    }

    @Test
    public void findAllTest(){
        var student = repository.findAll()
            .blockLast();  // Récupère le dernier étudiant de la liste
            
        if (student != null) {
            System.out.println("Étudiant trouvé : " + student.toString());
        }
    }
    
}
