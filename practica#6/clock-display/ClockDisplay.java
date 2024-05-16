
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class ClockDisplay
{
    //Hours of clock
    private NumberDisplay hours;
    //Minutes of clock
    private NumberDisplay minutes;
    // simulates the actual display
    private String displayString;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     * Precondition: 
     * @param hour must be a value between 0 and 24.
     * @param minute must be a value between 0 and 59.
     * PostCondition: This constructor create a new clock with the @param hour and @param minute inserted by customer.
     */
    public ClockDisplay(int hour, int minute)
    {
        if (hour < 0 || hour > 24) {
            throw new IllegalArgumentException("Value of hour is invalid!");
        }
        if (minute < 0 || minute > 60) {
            throw new IllegalArgumentException("Value of minute is invalid!");   
        }
        
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        /**
         * El @param hour debe ser un valor entre 0 y 24.
         * El @param minute debe ser un valor entre 0 y 59.
         */
        assert hour >= 0 && hour < 24;
        assert minute >= 0 && minute < 60;
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        /**
         * Las primeras 12 horas se presentan como AM.
         */
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue();
        if (hours.getValue() >= 0 && hours.getValue() < 12) {
            displayString = displayString + " AM";
        }
        if (hours.getValue() >= 12 && hours.getValue() < 24) {
            displayString = displayString + " PM";
        }
        
    }
}
