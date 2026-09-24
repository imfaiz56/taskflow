package com.faiz.taskflow.dto;

public class MoveTaskRequest {
    private Long targetColumnId;
    private Integer newPosition;

    public Long getTargetColumnId() { return targetColumnId; }
    public void setTargetColumnId(Long targetColumnId) { this.targetColumnId = targetColumnId; }
    public Integer getNewPosition() { return newPosition; }
    public void setNewPosition(Integer newPosition) { this.newPosition = newPosition; }
}
