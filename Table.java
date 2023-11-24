import java.util.ArrayList;

public class Table {
	ArrayList<Attendee> people = new ArrayList<Attendee>();
	int tableNum;

	public Table(int table) {
		tableNum = table;
	}
	
	// An old, unused function to check if a specific person (with their company number passed in) was part of a company.
	public boolean checkForCompany(int checkCompanyNum) {
		for (Attendee guest: people) {
			if (guest.getCompanyNum() == checkCompanyNum) {
				return true;
			}
		}
		return false;
	}

    public ArrayList<Attendee> getPeople() {
        return people;
    }

	public int getTableNum() {
		return tableNum;
	}

	public void addAttendee(Attendee person) {
		people.add(person);
	}


    public String toString() {
        String build = "Table " + tableNum + " includes:\n";
        for (Attendee guest: people) {
            build += (" - " + guest.getName() + " from " + guest.getCompanyName() + " at seat " + guest.getSeatNum() + "\n");
        }
        return build;
    }
}