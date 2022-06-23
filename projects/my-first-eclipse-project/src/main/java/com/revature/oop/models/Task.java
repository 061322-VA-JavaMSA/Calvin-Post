package com.revature.oop.models;

import java.time.LocalDate;

public class Task {

	private int id;
	private String description;
	public boolean completed;
	private LocalDate dueDate;
	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + ", completed=" + completed + ", dueDate=" + dueDate
				+ ", userAssigned=" + userAssigned + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public User getUserAssigned() {
		return userAssigned;
	}

	public void setUserAssigned(User userAssigned) {
		this.userAssigned = userAssigned;
	}

	private User userAssigned;
//	private int userIdAssigned; not the "OOP way"
	
	public Task() {
		dueDate = LocalDate.now().plusDays(5);
		description = "None provided";
	}
	
	public Task(String description) {
		this();
		this.description = description;
	}
	
	public void doTask() {
		System.out.println("Doing a task.");
	}
	
	public void doTask(String speed) {
		System.out.println("Doing a task at " + speed + " speed.");
	}
}
