package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;

import DAL.Online_DAL;
import DTO.Room_info_DTO;

public class Offline_BUS {
	public boolean checkNumberRoom(int a) {
		Online_DAL dal = new Online_DAL();
		ResultSet result = dal.getDataRoomInfor();
		
		try {
			while(result.next()) {
				if(a == result.getInt("number_room"))
					return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public void show(Room_info_DTO d) {
		
	}
}
