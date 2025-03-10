package com.shipping.demo.student;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.shipping.demo.ServiceShippingDemoApplication;
import com.shipping.demo.student.repository.StudentRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = ServiceShippingDemoApplication.class)
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
