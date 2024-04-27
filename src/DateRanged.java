/*******************************************************************
* Name: Abi Nakhle
* Date: 4/24/24
* Assignment: Course Project
*
* This class represents a DateRanged object, which extends the Task 
* class by adding the task's start date and end date. Getters and
* setters are provided as is a constructor to set all class and super
* class properties. The toString is overridden and calls the super 
* class toString method to provide the formatted data.
*/
import java.time.LocalDate;

public class DateRanged extends Task {
    private LocalDate StartDate;
    private LocalDate EndDate;

    public DateRanged(String name, String desc, boolean timeRestricted, 
        String urgency, String repeated, LocalDate startDate, 
        LocalDate endDate) {

            super(name, desc, timeRestricted, urgency, repeated);
            StartDate = startDate;
            EndDate = endDate;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }
    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }
    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }

    public int daysLeft(LocalDate StartDate, LocalDate EndDate) {
        StartDate - EndDate;
    }

    @Override
    public String toString() {
        return String.format("%n%s%n%s%s%n%s%s%n", 
            super.toString(),
            "Start Date: ", StartDate,
            "End Date: ", EndDate,
            "There are ", daysLeft(StartDate, EndDate), " days left for the task.");
    }
}
