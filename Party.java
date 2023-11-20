import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class Party {
	
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
}
