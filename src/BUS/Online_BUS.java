package BUS;
import DTO.Room_info_DTO;
import DTO.Customer_DTO;
import DTO.Room_book_DTO;
import DTO.Account_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAL.Online_DAL;
import DTO.Account_DTO;
import DTO.Room_info_DTO;
import GUI.CreateTable;
import GUI.Online_GUI;

public class Online_BUS {
	// Registration form
	public void getDataRegisterForm(Account_DTO dto, String _email, String _username, String _password) {
		dto.setEmail(_email);
		dto.setUserName(_username);
		dto.setPassWord(_password);
		dto.setPerMission(0);
	}
	public void getDataInsertFormAdmin2(Account_DTO dto, String _email, String _username, String _password, int _permission) {
		dto.setEmail(_email);
		dto.setUserName(_username);
		dto.setPassWord(_password);
		dto.setPerMission(_permission);
	}

	public boolean checkData(String _email, String _username, String _password) {
		if (_email.isEmpty() || _username.isEmpty() || _password.isEmpty())
			return false;
		return true;
	}

	public boolean checkLength(String _username, String _password) {
		if (_username.length() < 6 && _password.length() < 6)
			return false;
		return true;
	}

	// Login form
	public void getDataLoginForm(Account_DTO dto, String _username, String _password) {
		dto.setUserName(_username);
		dto.setPassWord(_password);
	}

	// room information
	public void getRoomIformation(Room_info_DTO room_info_dto) {
		Online_DAL dal = new Online_DAL();

		CreateTable tb = new CreateTable();

		ResultSet result = dal.roomInformation(room_info_dto);
	
		try {
			while(result.next()) {
				room_info_dto.roomId[result.getRow()] = result.getInt("id_room_info");
				room_info_dto.numberRoom[result.getRow()] = result.getInt("number_room");
				room_info_dto.typeOfRoom[result.getRow()] = result.getNString("type_of_room");
				room_info_dto._price[result.getRow()] = result.getFloat("price");
				room_info_dto.characteristics[result.getRow()] = result.getNString("Characteristics");
				room_info_dto.status[result.getRow()] = result.getNString("Status");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//check data form table room_infor
	public boolean checkDataTableRoomInfor(Room_info_DTO room_info_dto) {
		Online_DAL dal = new Online_DAL();
	
		ResultSet result = dal.getDataRoomInfor(); 
		 try {
			while(result.next()) {
				 if(room_info_dto.getNumber_room() == result.getInt("number_room")) {
					 return true;
				 }
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return false;
	}
	//get show account
	public void getAccount(Account_DTO ac_dto) {
		Online_DAL dal = new Online_DAL();

		CreateTable tb = new CreateTable();

		ResultSet result = dal.showAccount(ac_dto);
	
		try {
			while(result.next()) {
				ac_dto.array_id_acount[result.getRow()] = result.getInt("id_account");
				ac_dto.array_email[result.getRow()] = result.getString("email");
				ac_dto.array_username[result.getRow()] = result.getNString("username");
				ac_dto.array_password[result.getRow()] = result.getString("password");
				ac_dto.array_permission[result.getRow()] = result.getInt("permission");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//check book room account table
	public boolean checkBookRoomNumberUser(Room_info_DTO dto, int number_room) {
		Online_DAL dal = new Online_DAL();
		ResultSet result = dal.getDataRoomInfor();
		
		try {
			while(result.next()) {
				if(number_room == result.getInt("number_room"))
					return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean checkBookRoomEmptyUser(Room_info_DTO dto, int number_room) {
		Online_DAL dal = new Online_DAL();
		ResultSet result = dal.getDataRoomInfor();
		
		try {
			while(result.next()) {
				if(number_room == result.getInt("number_room")&&result.getNString("Status").equals("Full"))
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//get data form personal information
	public void getDataPersonInfo(Customer_DTO c_dto, long _id, String _name, String date, String _address, long _tel){
		c_dto.setCustomerID(_id);
		c_dto.setCustomerName(_name);
		c_dto.setDateOfBirth(date);
		c_dto.setAddress(_address);
		c_dto.setTelephone(_tel);
	}
	public void getDataRoomBook(Room_book_DTO b_dto, String checkin, String checkout, long daystay, String service, float totalCuerrent) {
		b_dto.setCheckInDate(checkin);
		b_dto.setCheckOutDate(checkout);
		b_dto.setDayOfStay(daystay);
		b_dto.setService(service);
		b_dto.setTotal(totalCuerrent);
	}
	public boolean checkIDCustomer(long id) {
		Online_DAL dal = new Online_DAL();
		ResultSet result = dal.getDataCustomer();
		
		try {
			while(result.next()) {
				if(id == result.getInt("id_customer"))
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//
	public float searchPrice(int a) {
		Online_DAL dal = new Online_DAL();
		ResultSet result = dal.getDataRoomInfor();
		
		try {
			while(result.next()) {
				if(a == result.getInt("number_room")) {
					return result.getFloat("price");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public boolean checkdataRoomInfo(int number_room) {
		Online_DAL dal = new Online_DAL();
		ResultSet result = dal.getDataRoomInfor();
		
		try {
			while(result.next()) {
				if(number_room == result.getInt("number_room"))
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//get data from room info
	public void getDataRoomInfo(Room_info_DTO f, int _number_room, String type, float _price, String _charac) {
		f.setNumber_room(_number_room);
		f.setType_of_room(type);
		f.setPrice(_price);
		f.setCharacteristics(_charac);
	}
	//set data show user book
	public void setRomUserBooked(Room_info_DTO r, Room_book_DTO b, Customer_DTO c) {//Customer_DTO c
		Online_DAL dal = new Online_DAL();
		ResultSet result = dal.showRoomInfoBooked(c.getCustomerID());
		
		try {
			while(result.next()) {
				r.setNumber_room(result.getInt("number_room"));
				r.setType_of_room(result.getNString("type_of_room"));
				b.setCheckInDate(result.getNString("check_in_date"));
				b.setCheckOutDate(result.getNString("check_out_date"));
				b.setDayOfStay(result.getLong("day_of_stay"));
				b.setTotal(result.getFloat("total"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Online_BUS bus = new Online_BUS();
		Account_DTO ac_dto = new Account_DTO();
		Room_info_DTO r = new Room_info_DTO();
		Room_book_DTO b = new Room_book_DTO();
//		bus.getIDLogin(ac_dto);
//		bus.setRomUserBooked(r,b,9000);
		System.out.println(r.getType_of_room());
		
	}
}
