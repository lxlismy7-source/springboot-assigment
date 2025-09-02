package com.agridence.microservice.Assignment.dto;



import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;

@Data
@Builder
public class NoteResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}