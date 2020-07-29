package DTO;

import java.util.Date;

public class Room_book_DTO {
	//Properties class Room_DTO
	private int room_book_ID;
	private String checkInDate;
	private String checkOutDate;
	private String service;
	private String note;
	private float prePayment;
	private long dayOfStay;
	private float total;
	
	//Contructor class Room_DTO
	public Room_book_DTO(int roomID, String typeOfRoom, String checkInDate, String checkOutDate, String service, String note,
			float prePayment,long dayOfStay, float total) {
		super();
		this.room_book_ID = roomID;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.service = service;
		this.note = note;
		this.prePayment = prePayment;
		this.dayOfStay = dayOfStay;
		this.total = total;
	}

	public Room_book_DTO() {
		super();
	}
	//Getter setter

	public int getRoomID() {
		return room_book_ID;
	}

	public void setRoomID(int roomID) {
		this.room_book_ID = roomID;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public float getPrePayment() {
		return prePayment;
	}

	public void setPrePayment(float prePayment) {
		this.prePayment = prePayment;
	}

	public long getDayOfStay() {
		return dayOfStay;
	}

	public void setDayOfStay(long dayOfStay) {
		this.dayOfStay = dayOfStay;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
}
