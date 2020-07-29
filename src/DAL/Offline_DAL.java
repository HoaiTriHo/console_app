package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DTO.Account_DTO;
import DTO.Customer_DTO;
import DTO.Room_info_DTO;
import GUI.Offline_GUI;

public class Offline_DAL {
	public void updateStatus(int a) {
		Connection conn = connectionMariaDB_DAL.getConnectionToDB();
		Room_info_DTO room_info_dto = new Room_info_DTO();
		Customer_DTO c_dto =  new Customer_DTO();
		Account_DTO ac_dto = new Account_DTO();
		Offline_GUI gui = new Offline_GUI();
		
		try {
			PreparedStatement pre = conn.prepareStatement("update room_info set Status=? where number_room =?");
			PreparedStatement pre1 = conn.prepareStatement("delete from room_book where number_room = ?");
			pre.setNString(1, "Empty");
			pre.setInt(2, a);
			pre1.setInt(1, a);
			pre1.executeUpdate();
			int i = pre.executeUpdate();
			if(i==1) {
				System.err.println("\n\t"+"***Update success");
				System.err.println("\n\t***Already print the bill");
				gui.showInformation(room_info_dto,c_dto,ac_dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Offline_DAL d = new Offline_DAL();
	}
}
