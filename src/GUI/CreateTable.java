package GUI;

public class CreateTable {
	public void createRowForm() {
		for(int i = 0; i<40;i++) {
			System.out.print((char)95);
		}
	}

	public String createSpace() {
		return "\t\t\t\t";
	}
	public String createSpaceSmall() {
		return "\t";
	}
	public String createSpaceQuite() {
		return "\t\t";
	}
	public void createRow() {
		for(int i = 0; i<10;i++) {
			System.out.print((char)95);
		}
	}
}
