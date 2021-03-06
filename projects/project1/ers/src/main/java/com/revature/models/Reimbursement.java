package com.revature.models;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "reimbursements")
public class Reimbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column(columnDefinition = "NUMERIC(19,2)")
	private double amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Timestamp submitted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Timestamp resolved;

	@Column
	private String description;

	@Column(columnDefinition = "BYTEA")
	private byte[] receipt;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false, insertable = true, updatable = false)
	private User author;

	@ManyToOne
	@JoinColumn(name = "resolver_id", insertable = false, updatable = true)
	private User resolver;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private ReimbStatus reimbStatus;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private ReimbType reimbType;

	public Reimbursement() {
		super();
		id = -1;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public ReimbStatus getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(ReimbStatus reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public ReimbType getReimbType() {
		return reimbType;
	}

	public void setReimbType(ReimbType reimbType) {
		this.reimbType = reimbType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(receipt);
		result = prime * result
				+ Objects.hash(amount, author, description, id, reimbStatus, reimbType, resolved, resolver, submitted);
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
		Reimbursement other = (Reimbursement) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(author, other.author) && Objects.equals(description, other.description)
				&& id == other.id && Arrays.equals(receipt, other.receipt)
				&& Objects.equals(reimbStatus, other.reimbStatus) && Objects.equals(reimbType, other.reimbType)
				&& Objects.equals(resolved, other.resolved) && Objects.equals(resolver, other.resolver)
				&& Objects.equals(submitted, other.submitted);
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + Arrays.toString(receipt) + ", author=" + author
				+ ", resolver=" + resolver + ", reimbStatus=" + reimbStatus + ", reimbType=" + reimbType + "]";
	}

}
