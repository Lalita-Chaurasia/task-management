package com.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taskmanager.entity.Task;
import com.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }
    public Task addTask(Task task) {
        return repo.save(task);
    }

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public Task getTaskById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existing = repo.findById(id).orElse(null);

        if (existing != null) {
            existing.setTitle(updatedTask.getTitle());
            existing.setDescription(updatedTask.getDescription());
            existing.setStatus(updatedTask.getStatus());
            return repo.save(existing);
        }
        return null;
    }

    public boolean deleteTask(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
    }

