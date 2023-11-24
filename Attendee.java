import java.util.ArrayList;

public class Attendee {
	String name;
	int companyNum;
	String companyName;
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

	public int getSeatNum() {
		return seatNum;
	}
	
	public int getCompanyNum() {
		return companyNum;
	}

    public String getName() {
        return name;
    }

	public String getCompanyName() {
        return companyName;
    }

	public void setCompany(ArrayList<Company> companies) {
		companyName = companies.get(companyNum-1).getName();
	}

	public void setTableAndSeat(int table, int seat) {
		tableNum = table;
		seatNum = seat;
	}
		
	public String toString() {
		return "NAME: " + name + "  " + "COMPANY: " + companyNum + "  " + "ID: " + id;
		}
}