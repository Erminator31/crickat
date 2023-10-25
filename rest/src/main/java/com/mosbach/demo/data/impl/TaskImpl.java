package com.mosbach.demo.data.impl;


import com.mosbach.demo.data.api.Task;

public class TaskImpl implements Task {

    private String name;
    private int priority;

    public TaskImpl(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
