package com.todoapp.tasks.service;

import com.todoapp.tasks.dto.TaskRequest;
import com.todoapp.tasks.dto.TaskResponse;
import com.todoapp.tasks.dto.TaskStatusRequest;
import com.todoapp.tasks.entity.Task;
import com.todoapp.tasks.entity.User;
import com.todoapp.tasks.model.TaskPriority;
import com.todoapp.tasks.model.TaskStatus;
import com.todoapp.tasks.repository.TaskRepository;
import com.todoapp.tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository, UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskResponse> getAll(){
        return taskRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse getById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return toResponse(task);
    }


    public TaskResponse create(TaskRequest request, Long ownerId){
        User owner = userRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .owner(owner)
                .build();

        Task saved =  taskRepository.save(task);

        return toResponse(saved);
    }

    public TaskResponse changeStatus(TaskStatusRequest request, Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(request.getStatus());
        Task saved = taskRepository.save(task);

        return toResponse(saved);

    }

    public TaskResponse updateTask(TaskRequest request, Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        Task saved = taskRepository.save(task);
        return toResponse(saved);

    }


    public void deleteTask(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }

    private TaskResponse toResponse(Task task){
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .ownerName(task.getOwner().getName())
                .createdAt(task.getCreatedAt())
                .build();
    }


}
