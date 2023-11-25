import java.util.ArrayList;

/**
Table.java
@author Ethan Avera
@since 11/16/23
This class handles the functionality of storing the data and people of a table
*/
public class Table {
	ArrayList<Attendee> people = new ArrayList<Attendee>();
	int tableNum;

	// Table Constructor
	public Table(int table) {
		tableNum = table;
	}
	
	/*
	An old, unused function to check if a specific person (with their company number passed in) is part of a company.
	Returns a boolean (true if the person is in a company, and false if they aren't)
	*/
	public boolean checkForCompany(int checkCompanyNum) {
		for (Attendee guest: people) {
			if (guest.getCompanyNum() == checkCompanyNum) {
				return true;
			}
		}
		return false;
	}

	
    /*
    Getter Function
    Returns the list of guests at the table as an ArrayList<Attendee>
    */
    public ArrayList<Attendee> getPeople() {
        return people;
    }

	/*
    Getter Function
    Returns the table number as an int
    */
	public int getTableNum() {
		return tableNum;
	}

	/*
    Setter Function
    Adds a guest to the ArrayList of people at the table.
    */
	public void addAttendee(Attendee person) {
		people.add(person);
	}

    /*
    toString Function: Formats the Table as a string to prepare for it to be printed
    Returns the formatted String
    */
    public String toString() {
        String build = "Table " + tableNum + " includes:\n";
        for (Attendee guest: people) {
            build += (" - " + guest.getName() + " from " + guest.getCompanyName() + " at seat " + guest.getSeatNum() + "\n");
        }
        return build;
    }
}