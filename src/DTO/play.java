package DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class play {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        
		String a = "2020-02-17";
		String b = "2020-02-18";
		
		Date d1 = Date.valueOf(a);
		Date d2 = Date.valueOf(b);
		
		c1.setTime(d1);
        c2.setTime(d2);
        
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        
        System.out.println(noDay);
	}

}
