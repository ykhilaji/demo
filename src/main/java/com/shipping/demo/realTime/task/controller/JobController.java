package com.shipping.demo.realTime.task.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.demo.realTime.task.converter.JacksonConfig;
import com.shipping.demo.realTime.task.entity.TaskInfra;
import com.shipping.demo.realTime.task.entity.TaskModel;
import com.shipping.demo.realTime.task.service.JobService;
import com.shipping.demo.realTime.task.utils.Mock;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/job")
public class JobController {

   private final Mock mock;
   private final JobService jobService;
   private final JacksonConfig objectMapper;

   public JobController(Mock mock, JobService jobService, JacksonConfig objectMapper) {
       this.mock = mock;
       this.jobService = jobService;
       this.objectMapper = objectMapper;
   }

   @PostMapping("/{sid}/{tasks}")
   public Mono<ResponseEntity<Void>> job(@PathVariable String sid, @PathVariable int tasks) {
       return Flux.range(1, tasks)
           .<Void>flatMap(i ->
               Mono.delay(Duration.ofMillis(500))
                   .then(Mono.fromFuture(mock.loadJson("tasks.json")))
                   .flatMap((JsonNode data) -> {
                      Mono<JsonNode> taskMono = Mono.just(data);
                       Mono<TaskModel> taskModelMono = fromJson(data);
                       return taskModelMono.flatMap((TaskModel task) -> {
                           TaskInfra taskInfra = new TaskInfra(
                               sid,
                               "task " + i + " complet",
                               convertToJson(sid),
                               task
                           );
                           return Mono.<Void>fromRunnable(() -> jobService.onTask(taskInfra));
                       }).switchIfEmpty(
                           Mono.fromRunnable(() ->
                               System.err.println("Erreur de conversion JSON pour task " + i)
                           )
                       );
                   })
           )
           .then(Mono.just(ResponseEntity.noContent().build()));
   }

   private JsonNode convertToJson(String value) {
       return objectMapper.objectMapper().getNodeFactory().textNode(value);
   }

   public  Mono<TaskModel> fromJson(JsonNode json) {
    try {
        TaskModel task = objectMapper.objectMapper().treeToValue(json, TaskModel.class);
        return Mono.just(task);
    } catch (Exception e) {
        return Mono.error(new RuntimeException("Erreur de conversion JSON en TaskModel", e));
    }
}
}
