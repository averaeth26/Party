import java.util.ArrayList;

public class Tester {
	public static void main(String[] args) {
		Party p1 = new Party();
        ArrayList<Attendee> attendants = p1.generateAttendance();
        ArrayList<Company> companies = p1.fillCompanies(attendants);
        ArrayList<Table> tables = p1.fillTables(companies);
        // for (Company company: companies) {
        //     System.out.println(company.toString());
        // }
        int sum = 0;
        for (Table table: tables) {
            System.out.println(table.toString());
            sum += table.getPeople().size();
        }
        System.out.println(sum);

	    // for (Attendee attendee: attendants) {
		// 	System.out.println(attendee);
		// }
        // for (int tableNum = 0; tableNum < numTables; tableNum++) {
        //     tables.add(new Table());
        //     tables.get(tableNum).fillTable(attendants, tableNum);
        // }
        // int sum = 0;
        // for (Table table: tables) {
        //     System.out.println(table);
        //     sum += table.getPeople().size();
        // }
        // System.out.println(sum);
    }
}