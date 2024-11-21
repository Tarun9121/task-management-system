package com.task.task.management.system.convert;

import com.task.task.management.system.dto.TaskDto;
import com.task.task.management.system.entity.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskConvert {
    public Task convert(TaskDto taskDto) {
        Task task = new Task();

        if(!ObjectUtils.isEmpty(taskDto)) {
            BeanUtils.copyProperties(taskDto, task);
        }

        return task;
    }

    public TaskDto convert(Task task) {
        TaskDto taskDto = new TaskDto();

        if(!ObjectUtils.isEmpty(task)) {
            BeanUtils.copyProperties(task, taskDto);
        }

        return taskDto;
    }

    public List<TaskDto> convert(List<Task> allTasks) {
        List<TaskDto> allTaskDto = new ArrayList<>();

        if(!CollectionUtils.isEmpty(allTasks)) {
            allTasks.forEach((task) -> {
                allTaskDto.add(convert(task));
            });
        }

        return allTaskDto;
    }
}
