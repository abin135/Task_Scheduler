/*******************************************************************
* Name: Abi Nakhle
* Date: 4/28/24
* Assignment: Course Project
*
* This class represents a DateRanged object, which extends the Task 
* class by adding the task's start date and end date. Getters and
* setters are provided as is a constructor to set all class and super
* class properties. The toString is overridden and calls the super 
* class toString method to provide the formatted data.
*/

public class DateRanged extends Task {
    public String StartDate;
    public String EndDate;

    public DateRanged(String name, String desc, boolean timeRestricted, 
        String urgency, String repeated, String startDate, 
        String endDate) {

            super(name, desc, timeRestricted, urgency, repeated);
            StartDate = startDate;
            EndDate = endDate;
    }
    public DateRanged() {
    }

    public String getStartDate() {
        return StartDate;
    }
    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }
    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("%n%s%n%s%s%n%s%s%n", 
            super.toString(),
            "Start Date: ", StartDate,
            "End Date: ", EndDate);
    }
}
