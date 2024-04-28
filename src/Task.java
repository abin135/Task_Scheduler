/*******************************************************************
* Name: Abi Nakhle
* Date: 4/24/24
* Assignment: Course Project
*
* This class represents a Task object, which is "ultimate" super 
* class. All other classes extend this class at some level. 
* This class includes Name, Desc, TimeRestricted, Urgency, and 
* Repeated properties as well as getters and setters for those 
* properties. The constructor takes parameters to set all properties.
*/
public class Task {
    private String Name;
    private String Desc;
    private boolean TimeRestricted;
    private String Urgency;
    private String Repeated;

    public Task(String name, String desc, boolean timeRestricted, 
        String urgency, String repeated) { 
            Name = name;
            Desc = desc;
            TimeRestricted = timeRestricted;
            Urgency = urgency;
            Repeated = repeated;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }
    public void setDesc(String desc) {
        Desc = desc;
    }

    public boolean getTimeRestricted() {
        return TimeRestricted;
    }
    public void setTimeRestricted(boolean timeRestricted) {
        TimeRestricted = timeRestricted;
    }

    public String getUrgency() {
        return Urgency;
    }
    public void setUrgency(String urgency) {
        Urgency = urgency;
    }

    public String getRepeated() {
        return Repeated;
    }
    public void setRepeated(String repeated) {
        Repeated = repeated;
    }

    public String toString() {
        if (TimeRestricted == true) {
            return String.format("%n%s%s%n%s%s%n%s%n%s%s%n%s%s%s%n", 
                "Task name: ", Name,
                "Description: ", Desc,
                "The task is time restricted.",
                "Urgent: ", Urgency,
                "The task is repeated ", Repeated, ".");
        } else {
            return String.format("%n%s%s%n%s%s%n%s%n%s%s%n%s%s%s%n", 
                "Task name: ", Name,
                "Description: ", Desc,
                "The task is not time restricted.",
                "Urgent: ", Urgency,
                "The task is repeated ", Repeated, ".");
        }
    }
}
