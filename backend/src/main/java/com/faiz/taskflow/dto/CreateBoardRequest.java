package com.faiz.taskflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateBoardRequest {
    @NotBlank(message = "Board name is required")
    @Size(max = 80, message = "Board name must be under 80 characters")
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
