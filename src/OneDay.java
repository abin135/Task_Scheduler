/*******************************************************************
* Name: Abi Nakhle
* Date: 4/28/24
* Assignment: Course Project
*
* This class represents a OneDay object, which extends the Task 
* class by adding the task's date and if it takes a full day or not. 
* Getters and setters are provided as is a constructor to set all 
* class and super class properties. The toString is overridden and 
* calls the super class toString method to provide the formatted 
* data.
*/

public class OneDay extends Task{
    public String TaskDate;
    public boolean FullDay;

    public OneDay(String name, String desc, boolean timeRestricted, 
        String urgency, String repeated, String taskDate, 
        boolean fullDay) {
            
            super(name, desc, timeRestricted, urgency, repeated);
            TaskDate = taskDate;
            FullDay = fullDay;
    }

    public OneDay() {
    }

    public String getTaskDate() {
        return TaskDate;
    }
    public void setTaskDate(String taskDate) {
        TaskDate = taskDate;
    }

    public boolean getFullDay() {
        return FullDay;
    }
    public void setFullDay(boolean fullDay) {
        FullDay = fullDay;
    }

    @Override
    public String toString() {
        if (FullDay == true) {
            return String.format("%n%s%n%s%s%n%s%s%n", 
                super.toString(),
                "Task Date: ", TaskDate,
                "Task is the whole day."
            );
        } else {
            return String.format("%n%s%n%s%s%n%s%s%n", 
                super.toString(),
                "Task Date: ", TaskDate,
                "Task is not the whole day."
            );
        }
    }
}
