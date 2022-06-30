package com.revature.models;

import java.time.LocalDate;
import java.util.Objects;

public class Payment {
	
	private int id;
	private LocalDate dateDue;
	private double amountDue;
	private double amountReceived;
	public double getAmountReceived() {
		return amountReceived;
	}
	public void setAmountReceived(double amountReceived) {
		this.amountReceived = amountReceived;
	}
	private String status;
	private Item item;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Payment [id=");
		builder.append(id);
		builder.append(", dateDue=");
		builder.append(dateDue);
		builder.append(", amountDue=");
		builder.append(amountDue);
		builder.append(", amountReceived=");
		builder.append(amountReceived);
		builder.append(", status=");
		builder.append(status);
		builder.append(", item=");
		builder.append(item);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		return Objects.hash(amountDue, amountReceived, dateDue, id, item, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Double.doubleToLongBits(amountDue) == Double.doubleToLongBits(other.amountDue)
				&& Double.doubleToLongBits(amountReceived) == Double.doubleToLongBits(other.amountReceived)
				&& Objects.equals(dateDue, other.dateDue) && id == other.id && Objects.equals(item, other.item)
				&& Objects.equals(status, other.status);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDateDue() {
		return dateDue;
	}
	public void setDateDue(LocalDate dateDue) {
		this.dateDue = dateDue;
	}
	public double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

}
