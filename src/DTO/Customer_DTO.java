package DTO;

import java.util.Date;

public class Customer_DTO {
	//properties customer
	private long customerID;
	private String customerName;
	private String dateOfBirth;
	private String address;
	private long telephone;
	
	//Contructor class Customer_DTO
	public Customer_DTO(long customerID, String customerName, String dateOfBirth, String address, long telephone) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.telephone = telephone;
	}
	public Customer_DTO() {
		super();
	}
	//Getter setter
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getTelephone() {
		return telephone;
	}
	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
}
