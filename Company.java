import java.util.ArrayList;
/**
Company.java
@author Ethan Avera
@since 11/23/23
This class handles the functionality of storing the data and people of a company
*/
public class Company {
    ArrayList<Attendee> reps = new ArrayList<Attendee>();
    int numReps;
    String name;
    int id;
    int numSeatedReps = 0;

    public Company(String companyName, ArrayList<Attendee> companyReps, int companyId) {
        reps = companyReps;
        numReps = reps.size();
        name = companyName;
        id = companyId;
    }

    /*
    Getter Function
    Returns the list of representatives for the company as an ArrayList<Attendee>
    */
    public ArrayList<Attendee> getReps() {
        return reps;
    }

    /*
    Getter Function
    Returns the id for the company as an int
    */
    public int getId() {
        return id;
    }

    /*
    Getter Function
    Returns the number of representatives for the company as an int
    */
    public int getNumReps() {
        return numReps;
    }
    
    /*
    Getter Function
    Returns the number of seated representatives for the company as an int
    */
    public int getNumSeatedReps() {
        return numSeatedReps;
    }

    /*
    Getter Function
    Returns the name of the company as an int
    */
    public String getName() {
        return name;
    }

    /*
    Setter Function
    Takes an ArrayList<Attendee> as input and sets it to the representatives.
    Also recalculates numReps based on the newly-changed reprepresnetatives variable.
    */
    public void setReps(ArrayList<Attendee> companyReps) {
        reps = companyReps;
        numReps = companyReps.size();
    }

    /*
    Setter Function
    Takes an int as input and sets it to the number of seated representatives.
    */
    public void setNumSeatedReps(int companySeatedReps) {
        numSeatedReps = companySeatedReps;
    }


    /*
    toString Function: Formats the Company as a string to prepare for it to be printed
    Returns the formatted String
    */
    public String toString() {
        String build = name + " has " + numReps + " representatives:\n";
        for (Attendee guest: reps) {
            build += (" - " + guest.getName() + " at seat " + guest.getSeatNum() + " of table #" + guest.getTableNum() + "\n");
        }
        return build;
    }
}
