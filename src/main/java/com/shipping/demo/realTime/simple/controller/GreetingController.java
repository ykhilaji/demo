package com.shipping.demo.realTime.simple.controller;

import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shipping.demo.realTime.simple.service.GreetingService;
import com.shipping.demo.student.service.StudentService;
import com.shipping.demo.student.model.Student;
import com.shipping.demo.realTime.task.entity.TaskModel;
import com.shipping.demo.realTime.task.service.JobService;


@RestController
public class GreetingController {
	private final GreetingService greetingService;
	private final StudentService studentService;
  private final JobService jobService;

	public GreetingController(GreetingService greetingService, StudentService studentService, JobService jobService) {
		this.greetingService = greetingService;
		this.studentService = studentService;
		this.jobService = jobService;
	}
	
	@GetMapping("/api/v1/hello")
	public CompletableFuture<String> hello() {
		return greetingService.hello();
	}

	@GetMapping("/api/v1/main")
	public CompletableFuture<?> main() {
		CompletableFuture<String> greetingFuture = greetingService.hello();
		CompletableFuture<List<Student>> studentsFuture = studentService.all()
			.collectList()
			.toFuture();
		CompletableFuture<List<TaskModel>> tasksFuture = jobService.getTasks()
			.collectList()
			.toFuture();

		return CompletableFuture.allOf(greetingFuture, studentsFuture, tasksFuture)
			.thenApply(v -> {
				Map<String, Object> response = new HashMap<>();
				response.put("greeting", greetingFuture.join());
				response.put("students", studentsFuture.join());
				response.put("tasks", tasksFuture.join());
				return response;
			});
	}
}
