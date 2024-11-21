package com.task.task.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
public class BaseResponseDto {
    private HttpStatus status;
    private String message;
}
