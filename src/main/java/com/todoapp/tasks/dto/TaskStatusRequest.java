package com.todoapp.tasks.dto;

import com.todoapp.tasks.model.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskStatusRequest {
    @NotNull
    private TaskStatus status;
}
