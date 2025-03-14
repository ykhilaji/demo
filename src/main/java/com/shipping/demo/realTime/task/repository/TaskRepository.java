package com.shipping.demo.realTime.task.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.shipping.demo.realTime.task.entity.TaskModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskRepository extends ReactiveCrudRepository<TaskModel, Long> {
    
    Flux<TaskModel> findAllByDataContainingIgnoreCase(String name);

    Flux<TaskModel> findByUserIdAndStatus(String userId, String status);

    Mono<Long> countByUserIdAndDateBefore(String userId, LocalDateTime date);

    Mono<Void> deleteByIdIn(List<Long> ids);

    // Méthode personnalisée pour trouver des tâches par jobId, type et statut
   // @Query("SELECT * FROM tasks WHERE job_id = :jobId AND type = :taskType AND status = :taskStatus ORDER BY line_number")
    //Flux<TaskModel> findByJobIdAndTypeAndStatus(Long jobId, TaskType taskType, TaskStatus taskStatus);
    
}
