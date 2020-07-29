package DTO;

public class Room_info_DTO {
	private int room_info_ID;
	private int number_room;
	private String type_of_room;
	private float price;
	private String Characteristics;
	private String Status;
	
	//
	public Room_info_DTO(int room_info_ID, int number_room, String type_of_room, float price, String characteristics, String status) {
		super();
		this.room_info_ID = room_info_ID;
		this.number_room = number_room;
		this.type_of_room = type_of_room;
		this.price = price;
		Characteristics = characteristics;
		Status = status;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	//
	public Room_info_DTO() {
		super();
	}
	//
	public int getRoom_info_ID() {
		return room_info_ID;
	}
	public void setRoom_info_ID(int room_info_ID) {
		this.room_info_ID = room_info_ID;
	}
	public int getNumber_room() {
		return number_room;
	}
	public void setNumber_room(int number_room) {
		this.number_room = number_room;
	}
	public String getType_of_room() {
		return type_of_room;
	}
	public void setType_of_room(String type_of_room) {
		this.type_of_room = type_of_room;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCharacteristics() {
		return Characteristics;
	}
	public void setCharacteristics(String characteristics) {
		Characteristics = characteristics;
	}

	public int[] roomId = new int[100];
	public int[] numberRoom = new int[100];
	public String[] typeOfRoom = new String[100];
	public float[] _price = new float[100];
	public String[] characteristics = new String[100];
	public String[] status = new String[100];
	
	public int roomN;

	public int getRoomN() {
		return roomN;
	}
	public void setRoomN(int roomN) {
		this.roomN = roomN;
	}
}
