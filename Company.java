import java.util.ArrayList;

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

    public ArrayList<Attendee> getReps() {
        return reps;
    }

    public int getId() {
        return id;
    }

    public int getNumReps() {
        return numReps;
    }

    public int getNumSeatedReps() {
        return numSeatedReps;
    }


    public void setReps(ArrayList<Attendee> companyReps) {
        reps = companyReps;
        numReps = companyReps.size();
    }

    public void setNumSeatedReps(int companySeatedReps) {
        numSeatedReps = companySeatedReps;
    }


    public String toString() {
        String build = name + " is represented by:\n";
        for (Attendee guest: reps) {
            build += (" - " + guest.getName() + "\n");
        }
        return build;
    }
}
