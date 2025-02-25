package com.coliship.demo.student;

import java.time.Duration;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {
    
  private final StudentRepository studentRepository;

  public Flux<Student> findAll() {
    return studentRepository.findAll()
    .delayElements(Duration.ofSeconds(1));
  }

  public Mono<Student> findById(Long id) {
    return studentRepository.findById(id);
  }

  public Mono<Student> save(StudentBo request) {
    return studentRepository.save(
        Student.builder()
            .firstname(request.getFirstName())
            .lastname(request.getLastName())
            .age(request.getAge())
            .build()
    );
  }
   public Flux<Student> findByFirstname(String firstname) {
    return studentRepository.findAllByFirstnameContainingIgnoreCase(firstname);
  }

  public void deleteById(Long id) {
    studentRepository.deleteById(id).subscribe();
  }
}
