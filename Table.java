import java.util.ArrayList;

public class Table {
	ArrayList<Attendee> people = new ArrayList<Attendee>();
	
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

	public void addAttendee(Attendee person) {
		people.add(person);
	}


    public String toString() {
        String build = "";
        for (Attendee guest: people) {
            build += (guest.getName() + " from company " + guest.getCompanyNum() + "\n");
        }
        return build;
    }
}