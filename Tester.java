import java.util.ArrayList;
import java.util.Scanner;

/**
Tester.java
@author Ethan Avera
@since 11/16/23
The tester class contains the main method.
This is the class that is referenced when running the program.
*/
public class Tester {
    /* 
    This is the method that actually runs the program.
    No return type.
    */
	public static void main(String[] args) {
		Party p1 = new Party();
        Intro i1 = new Intro();
        System.out.println("\033[H\033[2J");
        System.out.println("Welcome to Party Planner!\n");
        i1.directions();
        ArrayList<Attendee> attendants = p1.generateAttendance();
        ArrayList<Company> companies = p1.fillCompanies(attendants);
        ArrayList<Table> tables = p1.fillTables(companies);
        Scanner scan = new Scanner(System.in);
        System.out.print("What would you like to do?: ");
        while (true) {
            String userCommand = scan.nextLine();
            System.out.println("\033[H\033[2J");
            i1.directions();
            if (userCommand.equals("s") || userCommand.equals("search")) {
                System.out.println(p1.searchForAttendee(attendants));
            }
            else if (userCommand.equals("a") || userCommand.equals("add")) {
                System.out.println(p1.addAttendee(attendants, companies, tables));
            }
            else if (userCommand.equals("r") || userCommand.equals("remove")) {
                System.out.println(p1.removeAttendee(attendants, companies, tables));
            }
            else if (userCommand.equals("tr") || userCommand.equals("view table roster")) {
                System.out.println(p1.printTableRoster(tables));
            }
            else if (userCommand.equals("atr") || userCommand.equals("view all table rosters")) {
                System.out.println ("There are " + attendants.size() + " guests, sitting in " + tables.size() + " tables:\n");
                for (Table table: tables) {
                    System.out.println(table.toString());
                }
            }
            else if (userCommand.equals("cr") || userCommand.equals("view company roster")) {
                System.out.println(p1.printCompanyRoster(companies));
            }
            else if (userCommand.equals("acr") || userCommand.equals("view all company rosters")) {
                System.out.println ("There are " + attendants.size() + " guests, representing " + companies.size() + " companies:\n");
                for (Company company: companies) {
                    System.out.println(company.toString());
                }
            }
            else if (userCommand.equals("q") || userCommand.equals("quit")) {
                System.out.println("Goodbye!");
                break;
            }
            else {
                System.out.println("Invalid Command! Try again...");
            }
            System.out.print("What would you like to do next?: ");
        }
    }
}
