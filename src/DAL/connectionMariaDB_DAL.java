package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DTO.Account_DTO;

public class connectionMariaDB_DAL {
	public static Connection getConnectionToDB() {
		String classDriver = "org.mariadb.jdbc.Driver";
		String connectionUrl="jdbc:mariadb://localhost:3306/hotel_management_app_console";
		String user ="root";
		String  passWord= "hoaitri7";
		
		try {
			Class.forName(classDriver);
			return DriverManager.getConnection(connectionUrl, user, passWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
