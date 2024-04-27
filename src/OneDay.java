/*******************************************************************
* Name: Abi Nakhle
* Date: 4/24/24
* Assignment: Course Project
*
* This class represents a OneDay object, which extends the Task 
* class by adding the task's date and if it takes a full day or not. 
* Getters and setters are provided as is a constructor to set all 
* class and super class properties. The toString is overridden and 
* calls the super class toString method to provide the formatted 
* data.
*/
import java.sql.Date;
import java.time.LocalDate;

public class OneDay extends Task{
    private LocalDate TaskDate;
    private boolean FullDay;

    public OneDay(String name, String desc, boolean timeRestricted, 
        String urgency, String repeated, LocalDate taskDate, 
        boolean fullDay) {
            
            super(name, desc, timeRestricted, urgency, repeated);
            TaskDate = taskDate;
            FullDay = fullDay;
    }

    public LocalDate getTaskDate() {
        return TaskDate;
    }
    public void setTaskDate(LocalDate taskDate) {
        TaskDate = taskDate;
    }

    public boolean getFullDay() {
        return FullDay;
    }
    public void setFullDay(boolean fullDay) {
        FullDay = fullDay;
    }

    public int daysUntil(LocalDate taskDate) {
        LocalDate today = java.time.LocalDate.now();
        taskDate - today;
    } 

    @Override
    public String toString() {
        if (FullDay == true) {
            return String.format("%n%s%n%s%s%n%s%s%n%s%s%s%n", 
                super.toString(),
                "Task Date: ", TaskDate,
                "Task is the whole day.",
                "There are ", daysUntil(TaskDate), " days until the task date."
            );
        } else {
            return String.format("%n%s%n%s%s%n%s%s%n%s%s%s%n", 
                super.toString(),
                "Task Date: ", TaskDate,
                "Task is not the whole day.",
                "There are ", daysUntil(TaskDate), " days until the task date."
            );
        }
    }
}
