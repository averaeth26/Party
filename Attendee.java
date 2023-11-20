import java.util.ArrayList;

public class Attendee {
	String name;
	int companyNum;
	String company;
	int id;
	int tableNum;
	int seatNum;

	public Attendee(String attendeeName, int attendeeCompany, int attendeeId) {
		name = attendeeName;
		companyNum = attendeeCompany;
		id = attendeeId;
		}
		
	public int getId() {
		return id;
	}
	
	public int getTableNum() {
		return tableNum;
	}
	
	public int getCompanyNum() {
		return companyNum;
	}

	public void setTableNum(int num) {
		tableNum = num;
	}
	
	public void removeAttendee(Attendee attendee, ArrayList<Attendee> attendants) {
		attendants.remove(attendee.getId()-1);
	}
		
	public String toString() {
		return "NAME: " + name + "  " + "COMPANY: " + companyNum + "  " + "ID: " + id;
		}
}
