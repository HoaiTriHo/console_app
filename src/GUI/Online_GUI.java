package GUI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;
import java.sql.Date;
import DTO.Account_DTO;
import DTO.Customer_DTO;
import DTO.Room_book_DTO;
import DTO.Room_info_DTO;
import BUS.Online_BUS;
import DAL.Online_DAL;
import java.util.*;

public class Online_GUI extends CreateTable {

	public void register_Account(Account_DTO dto) {

		Scanner scanner = new Scanner(System.in);
		Online_BUS bus = new Online_BUS();

		String email, username, password;

		System.out.print("\n" + createSpace() + "Fill out the form \n");
		System.out.print(createSpace());
		createRowForm();

		System.out.print("\n" + createSpace() + "*Email: ");
		email = scanner.nextLine();
		System.out.print("\n" + createSpace() + "*Username: ");
		username = scanner.nextLine();
		System.out.print("\n" + createSpace() + "*Password: ");
		password = scanner.nextLine();

		System.out.print(createSpace());
		createRowForm();

		if (bus.checkData(email, username, password)) {
			if (bus.checkLength(username, password))
				bus.getDataRegisterForm(dto, email, username, password);
			else {
				System.err.println("\n" + createSpace() + "**Please username and password length > 6**");
				register_Account(dto);
			}
		} else {
			System.err.println("\n" + createSpace() + "**Please fill full the fields**");
			register_Account(dto);
		}
	}
	public void login_Account(Account_DTO dto) {
		Scanner scanner = new Scanner(System.in);
		String email, username, password;
		chooseCondition ch = new chooseCondition();
		Online_BUS bus = new Online_BUS();

		System.out.println("Want login, right?\n\t1. Yes \t2. No");

		if (ch.choose()) {
			System.out.print("\n" + createSpace() + "Log in form \n");
			System.out.print(createSpace());
			createRowForm();

			System.out.print("\n" + createSpace() + "*Username: ");
			username = scanner.nextLine();

			System.out.print("\n" + createSpace() + "*Password: ");
			password = scanner.nextLine();
			
			bus.getDataLoginForm(dto, username, password);
			System.out.print(createSpace());
			createRowForm();
		}
	}

	// login failed method
	public void loginChecked(Account_DTO ac_dto) {
		Online_DAL online_dal = new Online_DAL();
		Customer_DTO c_dto = new Customer_DTO();
		CreateTable tb = new CreateTable();
		Room_info_DTO room_info_dto = new Room_info_DTO();

		if (online_dal.checkPermisstion(ac_dto) == 0) {
			// login success
			System.err.println("\n" + tb.createSpace() + "Login success in user");
			showRomInformation(room_info_dto, c_dto, ac_dto);
		}
		if (online_dal.checkPermisstion(ac_dto) == 1) {/////////////////////////////////////////////login admin///////////////
			System.err.println("\n" + tb.createSpace() + "Login success in admin1");																
		}
		if (online_dal.checkPermisstion(ac_dto) == 2) {
			System.err.println("\n" + tb.createSpace() + "Login success in admin2\n");
			admin2Permission();
		} else {
			// login success
			while (online_dal.checkPermisstion(ac_dto) == -1) {
				System.err.println("\n" + tb.createSpace() + "Login failed!!!!!");
				login_Account(ac_dto);
				loginChecked(ac_dto);
			}
		}
	}
	// bookRoom
	public void bookRoom() {
		CreateTable tb = new CreateTable();
		System.out.print("\n" + tb.createSpace());
		tb.createRowForm();
		System.out.print("Wellcome Customer");
		tb.createRowForm();
	}
	// admin2 permission
	public void admin2Permission() {
		CreateTable tb = new CreateTable();
		chooseCondition ch = new chooseCondition();
		Room_info_DTO room_info_dto = new Room_info_DTO();
		Customer_DTO c_dto = new Customer_DTO();
		Account_DTO dto = new Account_DTO();
		Scanner scanner1 = new Scanner(System.in);
		int a;
		System.out.print("\n" + tb.createSpace());
		tb.createRowForm();
		System.out.print("Administration");
		tb.createRowForm();

		System.out.print("\n\n" + tb.createSpace());
		System.out.println("1. Room information management \t 2. Account management \t3. Log out");
		System.out.print(tb.createSpaceSmall() + "\t\t");

		System.out.print("\tYour choose: ");
		a = scanner1.nextInt();
		if (a == 1)
			// Room information
			showRomInformationAdmin2(room_info_dto, c_dto, dto);
		else if (a == 2) {
			showAccount(dto);
		} else {
			System.err.println("\t**You logged out!!");
			online_GUI(dto);
		}
	}
	//show rominformation admin2
	public void showRomInformationAdmin2(Room_info_DTO room_info_dto, Customer_DTO c_dto, Account_DTO ac_dto) {
		Online_DAL online_dal = new Online_DAL();
		Online_BUS bus = new Online_BUS();
		CreateTable tb = new CreateTable();

		chooseCondition ch = new chooseCondition();
		Scanner scanner = new Scanner(System.in);
		Scanner scanner1 = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);

		ResultSet result = online_dal.roomInformation(room_info_dto);
		bus.getRoomIformation(room_info_dto);

//		System.out.print("\n");
//		tb.createRowForm();
		System.out.print("\tRoom information table");
		tb.createRowForm();

		System.err.print("\n\n" + tb.createSpaceSmall() + "ID");
		System.err.print(tb.createSpaceSmall() + "Number room");
		System.err.print(tb.createSpaceSmall() + "Style of room");
		System.err.print(tb.createSpaceQuite() + "Price");
		System.err.print(tb.createSpaceQuite() + "Characteristics");
		System.err.println(tb.createSpaceQuite() + "Status");
		try {
			while (result.next()) {
				System.out.print(tb.createSpaceSmall() + room_info_dto.roomId[result.getRow()]);
				System.out.print(tb.createSpaceSmall() + room_info_dto.numberRoom[result.getRow()]);
				System.out.print(
						tb.createSpaceSmall() + tb.createSpaceSmall() + room_info_dto.typeOfRoom[result.getRow()]);
				System.out.print(
						tb.createSpaceQuite() + tb.createSpaceSmall() + room_info_dto._price[result.getRow()] + "$");
				System.out.print(tb.createSpaceQuite() + room_info_dto.characteristics[result.getRow()]);
				System.out.println(tb.createSpaceSmall() + room_info_dto.status[result.getRow()]);

			}
			System.out.println("\nAction: 1. Insert \t2. Delete\t 3. Exist");
			int a;
			System.out.print("\tYour choose: ");
			a = scanner.nextInt();
			if (a == 1) {
				// insert room
				insert_RoomInfo(room_info_dto);
			}
			if(a == 2) {
				//delete room
				System.out.print("\tYou want delete ID: ");
				a = scanner.nextInt();
				online_dal.deleteRominfo(a);
			}
			if(a == 3){
				// log out
//				System.err.println("\t**You logged out!!");
				admin2Permission();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// insert room info table
	public void insert_RoomInfo(Room_info_DTO f_dto) {

		Scanner scanner = new Scanner(System.in);
		Scanner scanner1 = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		Scanner scanner3 = new Scanner(System.in);
		Online_BUS bus = new Online_BUS();
		Online_DAL dal = new Online_DAL();
		String styleOfRoom, charac;
		int permission, number_room;
		float price;
		do {
		System.out.print("\n" + createSpace() + "Fill out the form \n");
		System.out.print(createSpace());
		createRowForm();

		System.out.print("\n" + createSpace() + "*Number room: ");
		number_room = scanner.nextInt();
		System.out.print("\n" + createSpace() + "*Style of room: ");
		styleOfRoom = scanner1.nextLine();
		System.out.print("\n" + createSpace() + "*Price: ");
		price = scanner3.nextFloat();

		System.out.print("\n" + createSpace() + "*Characteristics: ");
		charac = scanner2.nextLine();

		System.out.print(createSpace());
		createRowForm();
		}
		while(bus.checkdataRoomInfo(number_room));
		bus.getDataRoomInfo(f_dto, number_room, styleOfRoom, price, charac);
		dal.insertRoomInfo(f_dto);
		
	}
	// show Account table
	public void showAccount(Account_DTO ac_dto) {
		CreateTable tb = new CreateTable();
		Online_DAL online_dal = new Online_DAL();
		Online_BUS bus = new Online_BUS();
		ResultSet result = online_dal.showAccount(ac_dto);
		bus.getAccount(ac_dto);
		Scanner scanner = new Scanner(System.in);

		System.err.print("\n\n" + tb.createSpaceSmall() + "ID");
		System.err.print(tb.createSpaceSmall() + "Email");
		System.err.print(tb.createSpace() + "Username");
		System.err.print(tb.createSpaceQuite() + "Password");
		System.err.println(tb.createSpaceQuite() + "Permission");

		try {
			while (result.next()) {
				System.out.print(tb.createSpaceSmall() + ac_dto.array_id_acount[result.getRow()]);
				System.out.print(tb.createSpaceSmall() + ac_dto.array_email[result.getRow()]);
				System.out.print(tb.createSpaceQuite() + ac_dto.array_username[result.getRow()]);
				System.out.print(tb.createSpaceQuite() + ac_dto.array_password[result.getRow()]);
				System.out.println(tb.createSpaceQuite() + ac_dto.array_permission[result.getRow()]);
			}
			System.out.println("\nAction: 1. Insert \t 2. Delete \t3. Exist");
			int a;
			System.out.print("\tYour choose: ");
			a = scanner.nextInt();

			if (a == 1) {
				insert_Account(ac_dto);
				online_dal.insertDataAdmin2(ac_dto);
				showAccount(ac_dto);
			}
			if (a == 2) {
				System.out.print("\tRow want to delete: ");
				ac_dto.setAccountID(scanner.nextInt());
				online_dal.deleteAccountAdmin2(ac_dto);
			}
			if (a == 3) {
				admin2Permission();
			}
			if (a == 4) {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showRomInformation(Room_info_DTO room_info_dto, Customer_DTO c_dto, Account_DTO ac_dto) {
		Online_DAL online_dal = new Online_DAL();
		Online_BUS bus = new Online_BUS();
		CreateTable tb = new CreateTable();

		chooseCondition ch = new chooseCondition();
		Scanner scanner = new Scanner(System.in);
		Scanner scanner1 = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);

		ResultSet result = online_dal.roomInformation(room_info_dto);
		bus.getRoomIformation(room_info_dto);

//		System.out.print("\n");
//		tb.createRowForm();
		System.out.print("\tRoom information table");
		tb.createRowForm();

		System.err.print("\n\n" + tb.createSpaceSmall() + "ID");
		System.err.print(tb.createSpaceSmall() + "Number room");
		System.err.print(tb.createSpaceSmall() + "Style of room");
		System.err.print(tb.createSpaceQuite() + "Price");
		System.err.print(tb.createSpaceQuite() + "Characteristics");
		System.err.println(tb.createSpaceQuite() + "Status");
		try {
			while (result.next()) {
				System.out.print(tb.createSpaceSmall() + room_info_dto.roomId[result.getRow()]);
				System.out.print(tb.createSpaceSmall() + room_info_dto.numberRoom[result.getRow()]);
				System.out.print(
						tb.createSpaceSmall() + tb.createSpaceSmall() + room_info_dto.typeOfRoom[result.getRow()]);
				System.out.print(
						tb.createSpaceQuite() + tb.createSpaceSmall() + room_info_dto._price[result.getRow()] + "$");
				System.out.print(tb.createSpaceQuite() + room_info_dto.characteristics[result.getRow()]);
				System.out.println(tb.createSpaceSmall() + room_info_dto.status[result.getRow()]);

			}
			System.out.println("\nAction: 1. Book room \t 2. Log out");
			int a;
			if (ch.choose()) {
				// book room
				do {
					System.out.print("\tNumber room you want book:");
					a = scanner.nextInt();
					room_info_dto.setRoomN(a);
					float price = bus.searchPrice(a);
					room_info_dto.setPrice(price);
				}
				while(bus.checkBookRoomNumberUser(room_info_dto, a));
				if(bus.checkBookRoomEmptyUser(room_info_dto, a)) {
					System.err.print("\n\n\t**Room full, please choose again!\n\n");
					showRomInformation(room_info_dto, c_dto, ac_dto);
				}
				else {
					//personal information
					System.out.println("\tYou booked number room: " + a);
					System.out.println("\n\tPlease fill your personal information: ");
					formPersonInfo(c_dto);
					online_dal.insertPersonalInfo(c_dto, ac_dto, ac_dto.getAccountID());
					//table detail book room
					System.out.println("\tPlease fill table below:");
					Room_book_DTO b_dto = new Room_book_DTO();
					formRoomBook(b_dto, room_info_dto, c_dto);
				}
			}
			else{
				// log out
				System.err.println("\t**You logged out!!");
				online_GUI(ac_dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void formPersonInfo(Customer_DTO dto) {
		long id, tel;
		String name, address;
		String dateOfBirth;
		Scanner scanner = new Scanner(System.in);
		Scanner scanner1 = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		Scanner scanner3 = new Scanner(System.in);
		Scanner scanner4 = new Scanner(System.in);
		Online_BUS bus = new Online_BUS();
		
		do {
			System.out.print("\n" + createSpace() + "Personal Information form \n");
			System.out.print(createSpace());
			createRowForm();
			System.out.print("\n" + createSpace() + "*ID Card: ");
			id = scanner.nextLong();
			System.out.print("\n" + createSpace() + "*Name: ");
			name = scanner1.nextLine();
			System.out.print("\n" + createSpace() + "*Date of birth: ");
			dateOfBirth = scanner2.nextLine();
			System.out.print("\n" + createSpace() + "*Address: ");
			address = scanner3.nextLine();
			System.out.print("\n" + createSpace() + "*Telephone: ");
			tel = scanner4.nextLong();
			bus.getDataPersonInfo(dto, id, name, dateOfBirth, address, tel);
			System.out.print(createSpace());
			createRowForm();
			if(bus.checkIDCustomer(id)) {
				System.err.print("\n\n\t\t\t\t**ID card exist! Please try again\n");
			}
			}while(bus.checkIDCustomer(id));
	}
	//form room book
	public void formRoomBook(Room_book_DTO b_dto, Room_info_DTO rom_info_dto, Customer_DTO c_dto) {
		int dayOfStay;
		float repayment, total;
		String check_in, check_out, service;
		Scanner scanner = new Scanner(System.in);
		Scanner scanner1 = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		Online_BUS bus = new Online_BUS();
		Online_DAL dal = new Online_DAL();
		Room_info_DTO info_dto = new Room_info_DTO();
		
		System.out.print("\n" + createSpace() + "Book Information form \n");
		System.out.print(createSpace());
		createRowForm();
		
		System.out.print("\n" + createSpace() + "*Check in date(yyyy-mm-dd): ");
		check_in = scanner.nextLine();
		System.out.print("\n" + createSpace() + "*Check out date(yyyy-mm-dd): ");
		check_out = scanner.nextLine();
		long stay = returnDay(check_in, check_out);
		System.out.println("\n" + createSpace() + "*Day of stay: "+stay);
		System.out.print("\n" + createSpace() + "*Service(y/n): ");
		service = scanner.nextLine();
		float money;
		if(service.equals("n"))
			money = rom_info_dto.getPrice()*stay;
		else
			money = rom_info_dto.getPrice()*stay*2;
		System.out.print("\n" + createSpace() + "*Total current: "+money+"$");
		
		bus.getDataRoomBook(b_dto, check_in, check_out, stay, service, money);
		//call insert method
		
		dal.insertRoomBook(b_dto, rom_info_dto, c_dto);
		dal.updateRomInfo(rom_info_dto.getRoomN());////////////////////////////////tat update
		System.out.print(createSpace());
		createRowForm();
		System.out.println();
		//call method show room information book
		showRoomUserBooked(rom_info_dto, b_dto, c_dto);
	}
	public long returnDay(String a, String b) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
		
		Date d1 = Date.valueOf(a);
		Date d2 = Date.valueOf(b);
		
		c1.setTime(d1);
        c2.setTime(d2);
        
       long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        
        return noDay;
	}

	// insert acount table
	public void insert_Account(Account_DTO dto) {

		Scanner scanner = new Scanner(System.in);
		Online_BUS bus = new Online_BUS();

		String email, username, password;
		int permission;

		System.out.print("\n" + createSpace() + "Fill out the form \n");
		System.out.print(createSpace());
		createRowForm();

		System.out.print("\n" + createSpace() + "*Email: ");
		email = scanner.nextLine();
		System.out.print("\n" + createSpace() + "*Username: ");
		username = scanner.nextLine();
		System.out.print("\n" + createSpace() + "*Password: ");
		password = scanner.nextLine();

		System.out.print("\n" + createSpace() + "*Permission: ");
		permission = scanner.nextInt();

		System.out.print(createSpace());
		createRowForm();

		if (bus.checkData(email, username, password)) {
			if (bus.checkLength(username, password))
				bus.getDataInsertFormAdmin2(dto, email, username, password, permission);
			else {
				System.err.println("\n" + createSpace() + "**Please username and password length > 6**");
				register_Account(dto);
			}
		} else {
			System.err.println("\n" + createSpace() + "**Please fill full the fields**");
			register_Account(dto);
		}
	}
	public void showRoomUserBooked(Room_info_DTO f_dto, Room_book_DTO b_dto, Customer_DTO c_dto) {
		Online_BUS bus = new Online_BUS();
		bus.setRomUserBooked(f_dto,b_dto,c_dto);
		CreateTable tb  =new CreateTable();
		
		System.out.print("\n\n\tRoom information you booked");
		tb.createRowForm();

		System.err.print("\n\n" + tb.createSpaceSmall() + "Number room");
		System.err.print(tb.createSpaceSmall() + "Type of room");
		System.err.print(tb.createSpaceSmall() + "Check in date");
		System.err.print(tb.createSpaceQuite() + "Check out date");
		System.err.print(tb.createSpaceQuite() + "Day of stay");
		System.err.println(tb.createSpaceQuite() + "Total");
		
		System.out.print(tb.createSpaceSmall()+f_dto.getNumber_room());
		System.out.print(tb.createSpaceQuite()+f_dto.getType_of_room());
		System.out.print(tb.createSpaceQuite()+b_dto.getCheckInDate());
		System.out.print(tb.createSpaceQuite()+b_dto.getCheckOutDate());
		System.out.print(tb.createSpaceQuite()+b_dto.getDayOfStay());
		System.out.println(tb.createSpaceQuite()+"\t"+b_dto.getTotal()+"$");
		
		System.err.println("\n\t**Book room sucess, thank you");
		
		chooseCondition ch = new chooseCondition();
		Account_DTO ac_dto = new Account_DTO();
		System.out.println("\n\t1. Login");
		if(ch.choose()) {
			System.err.println("\t**You logged out!!");
			online_GUI(ac_dto);
		}
			
	}

	public void online_GUI(Account_DTO dto) {
		chooseCondition ch = new chooseCondition();
		Online_DAL online_dal = new Online_DAL();

		System.out.println("Do you have an account?\n\t1. Register \t2. Login");
		if (ch.choose()) {
			register_Account(dto);
			online_dal.insertData(dto);
		} else {
			// Login
			login_Account(dto);
			loginChecked(dto);
		}
	}
	//
	public static void main(String[] args) {
		Online_GUI g = new Online_GUI();
		Account_DTO ac_dto = new Account_DTO();
		Customer_DTO c = new Customer_DTO();
		Room_info_DTO room_info_dto = new Room_info_DTO();
		Room_book_DTO b_dto = new Room_book_DTO();
//		g.showRomInformation(room_info_dto, c);
//		g.showRomInformation(room_info_dto,c,ac_dto);
//		System.out.println(room_info_dto.getNumberR());
//		g.insert_RoomInfo(ac_dto);
		g.showRoomUserBooked(room_info_dto, b_dto, c);

	}
}
