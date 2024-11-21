package com.task.task.management.system.service.implementation;

import com.task.task.management.system.constants.TaskManagementSystem;
import com.task.task.management.system.convert.TaskConvert;
import com.task.task.management.system.dto.TaskDto;
import com.task.task.management.system.entity.Task;
import com.task.task.management.system.enums.TaskStatus;
import com.task.task.management.system.exception.ApiException;
import com.task.task.management.system.repository.TaskRepository;
import com.task.task.management.system.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImplementation implements TaskService {

    private final TaskConvert taskConvert;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImplementation(TaskConvert taskConvert, TaskRepository taskRepository) {
        this.taskConvert = taskConvert;
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<TaskDto> createNewTask(TaskDto taskDto) {
        try {
            if(ObjectUtils.isEmpty(taskDto)) {
                throw new ApiException(TaskManagementSystem.TASK_EMPTY);
            }

            Task newTask = taskConvert.convert(taskDto);
            newTask.setTaskstatus(TaskStatus.ON_GOING.toString());
            Task savedTask = taskRepository.save(newTask);
            TaskDto savedTaskDto = taskConvert.convert(savedTask);

            return ResponseEntity.status(HttpStatus.OK).body(savedTaskDto);
        } catch (RuntimeException e) {
            TaskDto errorDto = new TaskDto();
            errorDto.setMessage(e.getMessage());
            errorDto.setStatus(HttpStatus.BAD_REQUEST);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
    }

    @Override
    public ResponseEntity<TaskDto> getTaskById(UUID taskId) {
        TaskDto taskDto = new TaskDto();
        try {
            Task existingTask = taskRepository.findById(taskId)
                    .orElseThrow(() -> new ApiException(TaskManagementSystem.TASK_NOT_FOUND));
            taskDto = taskConvert.convert(existingTask);
            return ResponseEntity.status(HttpStatus.OK).body(taskDto);
        } catch(Exception e) {
            taskDto.setStatus(HttpStatus.BAD_REQUEST);
            taskDto.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(taskDto);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<TaskDto> updateCompleteTask(TaskDto taskDto) {
        try {
            if(ObjectUtils.isEmpty(taskDto)) {
                throw new ApiException(TaskManagementSystem.TASK_EMPTY + " cannot update");
            }
            Task updateTask = taskConvert.convert(taskDto);
            Task updatedTask = taskRepository.save(updateTask);

            TaskDto updatedTaskDto = taskConvert.convert(updatedTask);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTaskDto);
        } catch(Exception e) {
            TaskDto errorDto = new TaskDto();
            errorDto.setStatus(HttpStatus.BAD_REQUEST);
            errorDto.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
    }

    @Override
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        try {
            List<Task> allTasks = taskRepository.findAllActiveTasks();
            List<TaskDto> allTaskDtos = taskConvert.convert(allTasks);
            return ResponseEntity.status(HttpStatus.OK).body(allTaskDtos);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<String> removeTask(UUID taskId) {
        try {
            Task existingTask = taskRepository.findById(taskId)
                    .orElseThrow(() -> new ApiException(TaskManagementSystem.TASK_NOT_FOUND));

            taskRepository.delete(existingTask);
            return ResponseEntity.status(HttpStatus.OK).body(TaskManagementSystem.TASK_REMOVED_SUCCESSFUL);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TaskManagementSystem.SOMETHING_WENT_WRONG);
        }
    }
}