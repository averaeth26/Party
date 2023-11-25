import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
Party.java
@author Ethan Avera
@since 11/16/23
This class controls all of the overarching functions that tie all of the other classes together.
 */
public class Party {
    
    int numTables = 10;
    int numSeats = 10;
    Scanner scan = new Scanner(System.in);
	
    /*
    This function reads the "partyguests.txt" file
    It then creates and returns an ArrayList<Attendee> of attendants generated from the file. 
    */
    public ArrayList<Attendee> generateAttendance() {
        ArrayList<Attendee> currentAttendants = new ArrayList<Attendee>();
        try {
            File inputFile = new File("partyguests.txt");
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String currentLine = reader.nextLine();
                String[] data = currentLine.split(",");
                currentAttendants.add(new Attendee(data[2] + " " + data[1],  Integer.parseInt(data[3]), Integer.parseInt(data[0])));
            }
            reader.close();
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
        return currentAttendants;
    }

    /*
    This function reads the "companies.txt" file
    It then creates and returns an ArrayList<Company> of companies generated from the file
    */
    public ArrayList<Company> generateCompanies() {
        ArrayList<Company> currentCompanies = new ArrayList<Company>();
        try {
            File inputFile = new File("companies.txt");
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String currentLine = reader.nextLine();
                String[] data = currentLine.split(",");
                if (!currentLine.equals("")) {
                    currentCompanies.add(new Company(data[1], new ArrayList<Attendee>(), Integer.parseInt(data[0])));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
        return currentCompanies;
    }

    /*
    This function fills the list of companies generated above
    To do this, it takes the ArrayList<Attendee> of attendants as a parameter and adds each one to the correct company
    It then returns the filled ArrayList<Company> of companies
    */
    public ArrayList<Company> fillCompanies(ArrayList<Attendee> attendants) {
        ArrayList<Company> companies = generateCompanies();
        ArrayList<Attendee> delete = new ArrayList<Attendee>();
        for (Company company: companies) {
            ArrayList<Attendee> currentReps = company.getReps();
            for (Attendee guest: attendants) {
                guest.setCompany(companies);
                if (guest.getCompanyNum() == company.getId()) {
                    if (currentReps.size() >= numSeats) { // Ensures that at most ten representatives will be allowed from any one company
                        System.out.println("Unfortunately, " + guest.getName() + " from " + company.getName() + " could not be seated and has been removed from the party.\n");
                        delete.add(guest);
                        continue;
                    }
                    currentReps.add(guest);
                }
            }
            company.setReps(currentReps);
        }
        attendants.removeAll(delete); // removeAll() is from the Oracle Java Docs (https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/util/ArrayList.html)
        companies = sortCompanies(companies);
        return companies;
    }

    /*
    This function sorts the companies
    It takes an ArrayList<Company> as a parameter, and returns another ArrayList<Company>
    It sorts them based on the size (number of representatives) of each company
    This is necessary because, to fill the companies optimally, the larger companies need to be prioritized
    */
    public ArrayList<Company> sortCompanies(ArrayList<Company> companies) { // Selection Sort, adapted from Runestone 7.6
        for (int i = companies.size()-1; i >= 1; i--) {
            int maxIndex = i;
            for (int j = i-1; j >= 0; j--) {
                if (companies.get(j).getNumReps() < companies.get(maxIndex).getNumReps()) {
                    maxIndex = j;
                }
            }
            Company temp = companies.get(i);
            companies.set(i, companies.get(maxIndex));
            companies.set(maxIndex, temp);
        }
        return companies;
    }

    /*
    This Method fills all tables based on the inputted ArrayList<Companies>
    It then returns an ArrayList<Table> of filled tables.
    Challenge: Getting the fillTables method to work and ensuring it correctly places as many guests as possible
    Solution: Sorting the list of companies by number of representatives to prioritize seating people from larger company.
              This makes sure the companies who need to seat more tables have those tables available
              when their turn comes along to be placed on the seating chart.
    */
    public ArrayList<Table> fillTables (ArrayList<Company> companies) {
        ArrayList<Table> currentTables = new ArrayList<Table>();
        for (int i = 0; i < numTables; i++) {
            currentTables.add(new Table(i+1));
            Table currentTable = currentTables.get(i);
            for (Company company: companies) {
                if (company.getNumSeatedReps() >= company.getNumReps()) {
                    continue;
                }
                Attendee currentGuest = company.getReps().get(company.getNumSeatedReps());
                if (currentGuest.getTableNum() == 0) {
                    currentTable.addAttendee(currentGuest);
                    company.setNumSeatedReps(company.getNumSeatedReps()+1);
                    currentGuest.setTableAndSeat(i+1, currentTable.getPeople().size());
                }
                if (currentTable.getPeople().size() >= numSeats) {
                    break;
                }
            }
		}
        return currentTables;
	}

    /*
    This function finds an attendee based on their name or ID
    It takes the ArrayList<Attendee> of Attendants as a parameter
    It then returns a String with info about its findings
    */
    public String searchForAttendee(ArrayList<Attendee> Attendants) {
        System.out.print("Enter a name or ID to search for: ");
        String query = scan.nextLine();
        System.out.println();
        for (Attendee guest: Attendants) {
            if (query.equalsIgnoreCase(guest.getName()) || query.equalsIgnoreCase(Integer.toString(guest.getId()))) {
                return guest.getName() + " " + "(ID #" + guest.getId() + ") is at seat "
                 + guest.getSeatNum() + " of table " + guest.getTableNum() + ".\n";
            }
        }
        return ("The guest you're looking for doesn't exist!\n");
    }

    /*
    This function returns a formatted roster (the toString()) for a specified table
    It takes an ArrayList<Table> as a parameter, and returns a formatted String
    */
    public String printTableRoster(ArrayList<Table> tables) {
        System.out.print("Which table would you like to view a roster for? (Enter Table Number): ");
        String table = scan.nextLine();
        while (!table.matches("[0-9]+")) { // matches() is from W3Schools (https://www.w3schools.com/java/java_ref_string.asp)
            System.out.print("Not a number! Try again: ");
            table = scan.nextLine();
        }
        int tableNum = Integer.parseInt(table);
        System.out.println();
        if (tableNum > numTables) {
            return "The table you're looking for doesn't exist!\n";
        }
        return(tables.get(tableNum-1)).toString();
    }
    /*
    This function returns a formatted roster (the toString()) for a specified company
    It takes an ArrayList<Company> as a parameter, and returns a formatted String
    */
    public String printCompanyRoster(ArrayList<Company> companies) {
        System.out.print("Which company would you like to view a roster for? (Enter Company Name): ");
        String companyName = scan.nextLine();
        System.out.println();
        for (Company company: companies) {
            if (company.getName().equalsIgnoreCase(companyName)) {
                return company.toString();
            }
        }
        return "The company you're looking for doesn't exist!\n";
        
    }

    /* 
    This function adds a specified attendee based on an inputted name and company
    It takes an ArrayList<Attendee> of attendants, an ArrayList<Company> of companies, and an ArrayList<Table> of tables as parameters
    It returns a String describing the results of the function call
    Challenge: Getting addAttendees to properly put the newly created attendant in the roster.
    After 4 attempted solutions and many bugs, I managed to get it working by resetting each guest's seat,
    then calling the fillTables function to redo the seating charts entirely.
    */
    public String addAttendee(ArrayList<Attendee> attendants, ArrayList<Company> companies, ArrayList<Table> tables) {
		System.out.print("What is the name of the attendee you would like to add?: ");
        String attendeeName = scan.nextLine();
        for (Attendee guest: attendants) {
            if (attendeeName.equalsIgnoreCase(guest.getName())) {
                return "This guest is already going to the party!\n";
            }
        }
        System.out.print("What company does the attendee you would like to add work for? (Enter Company Name): ");
        String attendeeCompanyName = scan.nextLine();
        int attendeeCompanyNum = -1;
        for (Company company: companies) {
            if (company.getName().equalsIgnoreCase(attendeeCompanyName)) {
                if (company.getNumReps() >= 10) {
                    return "This company cannot have any more representatives at the party! This guest could not be added.\n";
                }
                attendeeCompanyName = company.getName(); // Fixes any capitalization mistakes in the original company input
                attendeeCompanyNum = company.getId();
                break;
            }
        }
        if (attendeeCompanyNum < 0) {
            return "This company is not at the party! This guest could not be added.\n";
        }

        int attendeeId = findLowestAvailableId(attendants);
        if (attendeeId < 0) {
           return "The Party is full! This guest could not be added.\n";
        }
        Attendee currentAttendee = new Attendee(attendeeName, attendeeCompanyNum, attendeeId);
        attendants.add(currentAttendee);
        // Adding updated values to companies
        companies.clear();  // clear() and addAll() are from the Oracle Java Docs (https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
        companies.addAll(fillCompanies(attendants));
        // Resetting all seats to redo seat assignments with the newly added guest
        for (Attendee guest: attendants) {
            guest.setTableAndSeat(0, 0);
        }
        // Adding updated values to tables
        tables.clear();
        tables.addAll(fillTables(companies));
        return "Successfully added guest! " + attendeeName + " (ID#" + attendeeId +") is now located at seat " + currentAttendee.getSeatNum()
        + " of table #" + currentAttendee.getTableNum() + ".\n";
	}

    /*
    Helper function for the addAttendee() function
    This function finds the lowest ID that is not taken by a guest
    If no such ID exists, it returns -1
    This function takes an ArrayList<Attendee> as a parameter and returns an ID as an int
    */
    public int findLowestAvailableId(ArrayList<Attendee> attendants) {
        for (int i = 1; i <= numTables*numSeats; i++) {
            boolean available = true;
            for (Attendee guest: attendants) {
                if (guest.getId() == i) {
                    available = false;
                }
            }
            if (available) {
                return i;
            }
        }
        return -1;
    }
    /*
    This function removes a specified attendee based on an inputted name or company
    It takes an ArrayList<Attendee> of attendants, an ArrayList<Company> of companies, and an ArrayList<Table> of tables as parameters
    It returns a String describing the results of the function call
    */
    public String removeAttendee(ArrayList<Attendee> attendants, ArrayList<Company> companies, ArrayList<Table> tables) {
        System.out.print("Enter the name or ID of an attendee to remove: ");
        String attendeeInfo = scan.nextLine();
        System.out.println();
        boolean exists = false;
        Attendee currentGuest = new Attendee("", 0, 0);
        for (Attendee guest: attendants) {
            if (attendeeInfo.equalsIgnoreCase(guest.getName()) || attendeeInfo.equalsIgnoreCase(Integer.toString(guest.getId()))) {  
                exists = true;
                currentGuest = guest;
            }
        }
        if (!exists) {
            return ("The guest you're looking for doesn't exist!\n");
        }
        attendants.remove(currentGuest);
         // Adding updated values to companies
        companies.clear();  // clear() and addAll() are from the Oracle Java Docs (https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
        companies.addAll(fillCompanies(attendants));
        // Resetting all seats to redo seat assignments with the newly added guest
        for (Attendee guest: attendants) {
            guest.setTableAndSeat(0, 0);
        }
        // Adding updated values to tables
        tables.clear();
        tables.addAll(fillTables(companies));
        return "Successfully removed " + currentGuest.getName() + "!\n";
    }
}
