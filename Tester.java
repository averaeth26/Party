import java.util.ArrayList;
public class Tester {
	public static void main(String[] args) {
		Party p1 = new Party();
	    ArrayList<Attendee> attendants = p1.generateAttendance();
	    for (Attendee attendee: attendants) {
			System.out.println(attendee);
		}
  }
}
