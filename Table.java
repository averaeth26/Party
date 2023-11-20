import java.util.ArrayList;

public class Table {
	int numSeats;
	int numTables = 10;
	ArrayList<Attendee> people = new ArrayList<Attendee>();
	
	public boolean checkForCompany(int checkCompanyNum, ArrayList<Attendee> table) {
		for (Attendee guest: table) {
			if (guest.getCompanyNum() == checkCompanyNum) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Attendee> fillTable (ArrayList<Attendee> attendants) {
		ArrayList<Attendee> table = new ArrayList<Attendee>();  
		for (Attendee attendee: attendants) {
			boolean check = checkForCompany(attendee.getCompanyNum(), attendants);
			if (!check && attendee.getTableNum() <= numTables) {
				table.add(attendee);
			}
			if (table.size() >= numTables) {
				return table;
			}
		}
		return table;	
	}
}
