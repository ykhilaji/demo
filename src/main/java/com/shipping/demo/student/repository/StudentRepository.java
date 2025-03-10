package com.shipping.demo.student.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.shipping.demo.student.model.Student;

import reactor.core.publisher.Flux;


public interface StudentRepository extends ReactiveCrudRepository<Student, Long>{

    Flux<Student> findAllByFirstnameContainingIgnoreCase(String firstname);

}
