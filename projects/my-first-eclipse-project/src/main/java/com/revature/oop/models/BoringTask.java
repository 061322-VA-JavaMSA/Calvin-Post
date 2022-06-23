package com.revature.oop.models;

public class BoringTask extends Task {
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoringTask [toString()=");
		builder.append(super.toString());
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getDescription()=");
		builder.append(getDescription());
		builder.append(", isCompleted()=");
		builder.append(isCompleted());
		builder.append(", getDueDate()=");
		builder.append(getDueDate());
		builder.append(", getUserAssigned()=");
		builder.append(getUserAssigned());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}

	public BoringTask() {
		super();
	}
	
	public BoringTask(String desc) {
		super(desc);
	}

}
