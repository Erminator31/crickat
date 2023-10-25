package com.mosbach.demo.data.api;

import java.util.List;

public interface TaskManager {

    List<Task> readAllTasks();
    void addTask(String name, int priority);
    void createTaskTable();

}
