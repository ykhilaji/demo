package com.shipping.demo.realTime.Task.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.demo.ServiceShippingDemoApplication;
import com.shipping.demo.realTime.task.dto.EtiquetteModelAdapter;
import com.shipping.demo.realTime.task.repository.TaskRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ServiceShippingDemoApplication.class)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository repository;

    @Test
    void contextLoads() {
        assertNotNull(repository);
    }

    @Test
    public void findAllTest() {
      try {
          var jsonResult = repository.findAll().blockLast();
          assertNotNull(jsonResult);
          ObjectMapper objectMapper = new ObjectMapper();
          EtiquetteModelAdapter etiquette = objectMapper.readValue(jsonResult.getData().toString(), EtiquetteModelAdapter.class);
          System.out.println("Etiquette Model: " + etiquette);
          assertNotNull(etiquette);
      } catch (Exception e) {
          throw new RuntimeException("Failed to parse JSON response", e);
      }
    }
}
