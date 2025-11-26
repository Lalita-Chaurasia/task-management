package com.taskmanager.controller;

import com.taskmanager.entity.Task;
import com.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;
    public TaskController(TaskService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task saved = service.addTask(task);
        return ResponseEntity.ok(saved);
    }
    @GetMapping
    public ResponseEntity<List<Task>> all() {
        return ResponseEntity.ok(service.getAllTasks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable Long id) {
    	Task task=service.getTaskById(id);
    	if(task==null) {
    		return ResponseEntity.notFound().build();
    	}
        return ResponseEntity.ok(task);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        Task updated = service.updateTask(id, task);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteTask(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
