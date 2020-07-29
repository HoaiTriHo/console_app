package GUI;
import java.util.Scanner;

public class chooseCondition {
	public boolean choose() {
		int choose;
		Scanner scanner5 = new Scanner(System.in);
		do {
			System.out.print("\tYour choose: ");
			choose = scanner5.nextInt(); 
		}while(choose != 1 && choose != 2);
		
		if(choose == 1)
			return true;
		return false;
	}
	public int choose2() {
		int choose;
		Scanner scanner9 = new Scanner(System.in);
		do {
			System.out.print("\tYour choose: ");
			choose = scanner9.nextInt(); 
		}while(choose != 3 && choose != 4);
		
		if(choose == 3)
			return 3;
		return 4;
	}
}
