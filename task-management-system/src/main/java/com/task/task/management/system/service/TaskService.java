package com.task.task.management.system.service;

import com.task.task.management.system.dto.TaskDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    ResponseEntity<TaskDto> createNewTask(TaskDto taskDto);
    ResponseEntity<TaskDto> getTaskById(UUID taskId);
    ResponseEntity<TaskDto> updateCompleteTask(TaskDto taskDto);
    ResponseEntity<List<TaskDto>> getAllTasks();
    ResponseEntity<String> removeTask(UUID taskId);
}
