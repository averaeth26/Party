import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class Party {
	
public ArrayList<Attendee> generateAttendance(File ) {
    try {
      File inputFile = new File("partyguests.txt");
      Scanner reader = new Scanner(inputFile);
      ArrayList<Attendee> currentAttendants = new ArrayList<Attendee>();
      while (reader.hasNextLine()) {
        String currentLine = reader.nextLine();
        currentAttendants.add(new Attendant());
        System.out.println(currentLine);
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
