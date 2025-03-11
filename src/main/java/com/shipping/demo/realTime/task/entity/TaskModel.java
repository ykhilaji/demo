package com.shipping.demo.realTime.task.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "tasks")
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TaskModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    
    @Column(nullable = false, length = 255)
    @JsonProperty("user_id")
    private String userId;
    
    @Column(length = 1000)
    @Nullable
    @JsonProperty("user_data")
    private JsonNode userData;
    
    @Column(name = "job_id", length = 20)
    @JsonProperty("job_id")
    private Long jobId;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @JsonProperty("status")
    private TaskStatus status;
    
    @Column(length = 2000)
    @JsonProperty("data")
    private JsonNode data;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @JsonProperty("type")
    private TaskType taskType;
    
    @Column(length = 2000)
    @Nullable
    @JsonProperty("response")
    private JsonNode response;
    
    @Column(name = "is_visible", columnDefinition = "boolean default true")
    @JsonProperty("visible")
    private Boolean visible;
    
    @Column(name = "created_date")
    @Nullable
    @JsonProperty("date")
    private LocalDateTime date;
    
    @Column(name = "file_name", length = 500)
    @Nullable
    @JsonProperty("filename")
    private String filename;
    
    @Column(name = "line_number", length = 10)
    @Nullable
    @JsonProperty("line_number")
    private Integer lineNumber;
    
    @Column(name = "print_results", length = 2000)
    @Nullable
    @JsonProperty("print_results")
    private JsonNode printResults;

    // Add default constructor for Jackson deserialization
    public TaskModel() {
    }

    
}
