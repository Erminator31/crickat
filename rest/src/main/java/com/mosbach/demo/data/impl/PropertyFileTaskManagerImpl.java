package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PropertyFileTaskManagerImpl implements TaskManager {

    String taskPropertyFile;

    // Singleton
    static PropertyFileTaskManagerImpl propertyFileTaskManager = null;
    private PropertyFileTaskManagerImpl(String taskPropertyFile) {
        this.taskPropertyFile = taskPropertyFile;
    }
    public static PropertyFileTaskManagerImpl getPropertyFileTaskManagerImpl(String userPropertyFile) {
        if (propertyFileTaskManager == null)
            propertyFileTaskManager = new PropertyFileTaskManagerImpl(userPropertyFile);
        return propertyFileTaskManager;
    }

    public List<Task> readAllTasks() {

        final Logger readTaskLogger = Logger.getLogger("ReadTaskLogger");
        readTaskLogger.log(Level.INFO,"Start reading ");

        List<Task> tasks = new ArrayList<>();
        Properties properties = new Properties();

        int i = 1;
        try {
            properties.load(new FileInputStream(taskPropertyFile));
            while (properties.containsKey("task." + i + ".name")) {
                tasks.add(
                    new TaskImpl(
                            properties.getProperty("task." + i + ".name"),
                            Integer.parseInt(
                                properties.getProperty("task." + i + ".priority")
                            )
                    )
                );
                i++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public void addTask(String name, int priority) {
        final Logger createTaskLogger = Logger.getLogger("CreateTaskLogger");
        createTaskLogger.log(Level.INFO,"Start creating task for " + name);

        List<Task> tasks = readAllTasks();
        tasks.add(new TaskImpl(name, priority));
        storeAllTasks(tasks);

    }

    @Override
    public void createTaskTable() {
        // not needed
    }


    public void storeAllTasks(List<Task> tasks) {
        Properties properties = new Properties();
        final AtomicLong counter = new AtomicLong();
        counter.set(1);

        tasks.forEach( task -> {
            properties.setProperty("task." + counter.get() + ".name", task.getName());
            properties.setProperty("task." + counter.get() + ".priority", "" + task.getPriority());
            counter.getAndIncrement();
        });
        try {
            properties.store(new FileOutputStream(taskPropertyFile), null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
