package com.example.community_event_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Changed to Long for better compatibility
    private String taskName;

    @ManyToOne
    private Event event;  // Many-to-One relationship with Event

    @ManyToOne
    private Employee employee;  // Many-to-One relationship with Employee

    // Constructors, Getters, and Setters
    public Task() {}

    public Task(Long id, String taskName, Event event, Employee employee) {
        this.id = id;
        this.taskName = taskName;
        this.event = event;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
