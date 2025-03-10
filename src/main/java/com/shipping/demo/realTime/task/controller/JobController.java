package com.shipping.demo.realTime.task.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.shipping.demo.realTime.task.model.TaskInfra;
import com.shipping.demo.realTime.task.model.TaskModel;
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

   public JobController(Mock mock, JobService jobService) {
    this.mock = mock;
    this.jobService = jobService;
}

@GetMapping("/{sid}/{tasks}")
public Mono<ResponseEntity<Void>> job(@PathVariable String sid, @PathVariable int tasks) {
    return Flux.range(1, tasks)
        .<Void>flatMap(i ->
            Mono.delay(Duration.ofMillis(500))
                .then(Mono.fromFuture(mock.loadJson("tasks.json")))
                .flatMap(data -> {
                    // On suppose que TaskModel.fromJson retourne un Mono<TaskModel>
                    Mono<TaskModel> taskModelMono = TaskModel.fromJson(data);
                    return taskModelMono.flatMap((TaskModel task) -> {
                        // Crée un objet TaskInfra à partir des données
                        TaskInfra taskInfra = new TaskInfra(
                            sid,
                            "task " + i + " complet",
                            convertToJson(sid),
                            task
                        );
                        // Encapsule l'appel dans un Mono explicite de type Void
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





    // Méthode utilitaire pour convertir une String en JsonNode
    private JsonNode convertToJson(String value) {
        return com.fasterxml.jackson.databind.node.JsonNodeFactory.instance.textNode(value);
    }
    
}
