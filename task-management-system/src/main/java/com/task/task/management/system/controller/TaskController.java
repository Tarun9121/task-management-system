package com.task.task.management.system.controller;

import com.task.task.management.system.dto.TaskDto;
import com.task.task.management.system.service.TaskService;
import com.task.task.management.system.service.implementation.TaskServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskServiceImplementation taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/save-new-task")
    public ResponseEntity<TaskDto> createNewTask(@RequestBody TaskDto taskDto) {
              return taskService.createNewTask(taskDto);
    }

    @GetMapping("/get-task/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("taskId") UUID taskId) {
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/get-all-task")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/update-task")
    public ResponseEntity<TaskDto> updateCompleteTask(@RequestBody TaskDto taskDto) {
        return taskService.updateCompleteTask(taskDto);
    }

    @DeleteMapping("/remove-task/{taskId}")
    public ResponseEntity<String> removeTask(@PathVariable("taskId") UUID taskId) {
        return taskService.removeTask(taskId);
    }

}