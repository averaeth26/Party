import java.util.ArrayList;

/**
Attendee.java
@author Ethan Avera
@since 11/16/23
This class handles the functionality of storing the data of each guest attending the party
*/
public class Attendee {
	String name;
	int companyNum;
	String companyName;
	int id;
	int tableNum;
	int seatNum;

	// Attendee Constructor
	public Attendee(String attendeeName, int attendeeCompany, int attendeeId) {
		name = attendeeName;
		companyNum = attendeeCompany;
		id = attendeeId;
		}

	/*
    Getter Function
    Returns the id of the guest as an int
    */
	public int getId() {
		return id;
	}

	/*
    Getter Function
    Returns the table number the guest is at as an int
    */
	public int getTableNum() {
		return tableNum;
	}

	/*
    Getter Function
    Returns the guest's seat number at their table as an int
    */
	public int getSeatNum() {
		return seatNum;
	}
	
	/*
    Getter Function
    Returns the guest's company number as an int
    */
	public int getCompanyNum() {
		return companyNum;
	}

	/*
    Getter Function
    Returns the guest's name as a String
    */
    public String getName() {
        return name;
    }

	/*
    Getter Function
    Returns the name of the guest's company as a String
    */
	public String getCompanyName() {
        return companyName;
    }

	/*
    Setter Function
    Takes an ArrayList<Company> of companies as input
	Sets the guest's company based on this list and the guest's companyNum.
    */
	public void setCompany(ArrayList<Company> companies) {
		companyName = companies.get(companyNum-1).getName();
	}

	/*
    Setter Function
    Takes two ints, table number and seat number, as input
	Sets the guest's tableNum and seatNum based on these variables
    */
	public void setTableAndSeat(int table, int seat) {
		tableNum = table;
		seatNum = seat;
	}
	
	/*
    toString Function: Formats the Attendee as a string to prepare for it to be printed
    Returns the formatted String
    */
	public String toString() {
		return "NAME: " + name + "  " + "COMPANY: " + companyNum + "  " + "ID: " + id;
		}
}