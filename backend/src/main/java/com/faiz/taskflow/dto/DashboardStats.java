package com.faiz.taskflow.dto;

public class DashboardStats {
    private long totalTasks;
    private long completedTasks;
    private long overdueTasks;
    private long tasksThisWeek;
    private double completionPercentage;

    public DashboardStats(long totalTasks, long completedTasks, long overdueTasks,
                           long tasksThisWeek, double completionPercentage) {
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.overdueTasks = overdueTasks;
        this.tasksThisWeek = tasksThisWeek;
        this.completionPercentage = completionPercentage;
    }

    public long getTotalTasks() { return totalTasks; }
    public long getCompletedTasks() { return completedTasks; }
    public long getOverdueTasks() { return overdueTasks; }
    public long getTasksThisWeek() { return tasksThisWeek; }
    public double getCompletionPercentage() { return completionPercentage; }
}
