package com.todoapp.tasks.controller;

import com.todoapp.tasks.dto.TaskRequest;
import com.todoapp.tasks.dto.TaskResponse;
import com.todoapp.tasks.dto.TaskStatusRequest;
import com.todoapp.tasks.entity.Task;
import com.todoapp.tasks.repository.TaskRepository;
import com.todoapp.tasks.service.TaskService;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> getAll(){
        return taskService.getAll();
    }

    @PatchMapping("{id}/status")
    public ResponseEntity<TaskResponse> changeStatus(@PathVariable Long id, @RequestBody @Valid TaskStatusRequest request){
        return ResponseEntity.ok(taskService.changeStatus(request,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid TaskRequest request){
        TaskResponse task = taskService.create(request, 1L);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody @Valid TaskRequest request, @PathVariable Long id){
        return ResponseEntity.ok(taskService.updateTask(request, id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
