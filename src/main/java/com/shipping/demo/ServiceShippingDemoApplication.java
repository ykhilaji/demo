package com.shipping.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import com.shipping.demo.student.model.Student;
import com.shipping.demo.student.repository.StudentRepository;

@SpringBootApplication()
@EnableR2dbcRepositories(basePackages = "com.shipping.demo")
public class ServiceShippingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceShippingDemoApplication.class, args);
	}

	 @Bean
	public CommandLineRunner runner(StudentRepository repository) {
		return args -> {
			for (int i = 0; i < 3000; i++) {
				repository.save(
          Student.builder()
            .firstname("Test" + i)
            .lastname("test" + i)
            .age(i)
            .build()
				).subscribe();
			}
		};
	}

}
