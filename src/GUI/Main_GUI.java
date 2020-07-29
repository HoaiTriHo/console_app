package GUI;

import BUS.Online_BUS;
import DAL.Online_DAL;
import DTO.Account_DTO;
import DTO.Room_info_DTO;

public class Main_GUI {

	public static void main(String[] args) {
		Offline_GUI offline = new Offline_GUI();
		Online_GUI online = new Online_GUI();
		Account_DTO dto = new Account_DTO();
		chooseCondition ch = new chooseCondition();
		Online_BUS bus = new Online_BUS();

		System.out.println("Do you want?");
		System.out.println("\t1. Check-in room \t2. Check-out room");
		// Book online or Offline
		if (ch.choose()) {
			System.out.println("You want?\n \t1. Book Online \t2. Book Offline");
			if (ch.choose()) {
				// online
				online.online_GUI(dto);
			} else {
				// offline
				System.out.println("\nAdmin login");
				offline.offline_GUI();
			}
		}
	}
}
