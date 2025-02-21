package com.example.community_event_api.repository.service;

import com.example.community_event_api.entity.Task;
import com.example.community_event_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Method to save task
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    // Method to get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Method to get task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Method to update task
    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTaskName(updatedTask.getTaskName());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // Method to delete task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Page<Task> getAllTasks(Pageable pageable) {
     
        throw new UnsupportedOperationException("Unimplemented method 'getAllTasks'");
    }
}
