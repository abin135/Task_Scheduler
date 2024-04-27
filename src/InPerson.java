/*******************************************************************
* Name: Abi Nakhle
* Date: 4/24/24
* Assignment: Course Project
*
* This class represents an InPerson object, which extends the 
* OneDay class by adding the task's Location. Getters and setters 
* are provided as is a constructor to set all class and super
* class properties. The toString method is also overridden and 
* calls the super class’s method.
*/
import java.time.LocalDate;

public class InPerson extends OneDay {
    private String Location;

    public InPerson(String name, String desc, boolean timeRestricted, 
        String urgency, String repeated, LocalDate taskDate, boolean fullDay, 
        String location) {

            super(name, desc, timeRestricted, urgency, repeated, taskDate, fullDay);
            Location = location;
    }

    public String getLocation() {
        return Location;
    }
    public void setLocation(String location) {
        Location = location;
    }

    @Override
    public String toString() {
        return String.format("%n%s%n%s%n", 
            super.toString(),
            "Task Location: ", Location);
    }
}
