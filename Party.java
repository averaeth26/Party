import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Party {
    
    int numTables = 10;
    int numSeats = 10;
   
	
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

    public ArrayList<Company> fillCompanies(ArrayList<Attendee> attendants) {
        ArrayList<Company> companies = generateCompanies();
        for (Company company: companies) {
            ArrayList<Attendee> currentReps = company.getReps();
            for (Attendee guest: attendants) {
                if (currentReps.size() >= numSeats) { // Ensures that at most ten representatives will be allowed from any one company
                    break;
                }
                if (guest.getCompanyNum() == company.getId()) {
                    currentReps.add(guest);
                }
            }
            company.setReps(currentReps);
        }
        companies = sortCompanies(companies);
        return companies;
    }

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

    // Challenge: Getting the fillTables method to work and ensuring it correctly places as many guests as possible
    public ArrayList<Table> fillTables (ArrayList<Company> companies) {
        ArrayList<Table> currentTables = new ArrayList<Table>();
        for (int i = 0; i < numTables; i++) {
            currentTables.add(new Table());
            Table currentTable = currentTables.get(i);
            for (Company company: companies) {
                System.out.println(company.getNumSeatedReps() + "   " + company.getNumReps());
                if (company.getNumSeatedReps() >= company.getNumReps()) {
                    continue;
                }
                // boolean check = checkForCompany(company.get(i).getCompanyNum());
                Attendee currentGuest = company.getReps().get(company.getNumSeatedReps());
                if (currentGuest.getTableNum() == 0) {
                    currentTable.addAttendee(currentGuest);
                    company.setNumSeatedReps(company.getNumSeatedReps()+1);
                    currentGuest.setTableNum(i+1);
                }
                if (currentTable.getPeople().size() >= numSeats) {
                    break;
                }
            }
		}
        return currentTables;
	}
}
