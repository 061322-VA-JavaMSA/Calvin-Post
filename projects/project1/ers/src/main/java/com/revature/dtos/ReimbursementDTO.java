package com.revature.dtos;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

import com.revature.models.Reimbursement;

public class ReimbursementDTO {

	private int id;
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private byte[] receipt;
	private UserDTO author;
	private UserDTO resolver;
	private String status;
	private String type;
	
	public ReimbursementDTO(Reimbursement r) {
		id = r.getId();
		amount = r.getAmount();
		submitted = r.getSubmitted();
		resolved = r.getResolved();
		description = r.getDescription();
		receipt = r.getReceipt();
		author = new UserDTO(r.getAuthor());
		resolver = new UserDTO(r.getResolver());
		status = r.getReimbStatus().getStatus();
		type = r.getReimbType().getType();
	}

	public ReimbursementDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public UserDTO getAuthor() {
		return author;
	}

	public void setAuthor(UserDTO author) {
		this.author = author;
	}

	public UserDTO getResolver() {
		return resolver;
	}

	public void setResolver(UserDTO resolver) {
		this.resolver = resolver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(receipt);
		result = prime * result
				+ Objects.hash(amount, author, description, id, resolved, resolver, status, submitted, type);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementDTO other = (ReimbursementDTO) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(author, other.author) && Objects.equals(description, other.description)
				&& id == other.id && Arrays.equals(receipt, other.receipt) && Objects.equals(resolved, other.resolved)
				&& Objects.equals(resolver, other.resolver) && Objects.equals(status, other.status)
				&& Objects.equals(submitted, other.submitted) && Objects.equals(type, other.type);
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ReimbursementDTO [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", receipt=" + Arrays.toString(receipt) + ", author="
				+ author + ", resolver=" + resolver + ", status=" + status + ", type=" + type + "]";
	}

	
	
	
}
