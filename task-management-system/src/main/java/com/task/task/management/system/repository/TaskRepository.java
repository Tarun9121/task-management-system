package com.task.task.management.system.repository;

import com.task.task.management.system.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    @Query(nativeQuery = true, value="select * from tasks where is_deleted = false")
    List<Task> findAllActiveTasks();
}
