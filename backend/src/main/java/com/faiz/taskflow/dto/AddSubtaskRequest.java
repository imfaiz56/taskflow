package com.faiz.taskflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddSubtaskRequest {
    @NotBlank(message = "Subtask title is required")
    @Size(max = 150, message = "Subtask title must be under 150 characters")
    private String title;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
