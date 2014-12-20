package uk.ac.gcu.mondayschild.mondayschild;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 7 * Created by rla on 11/09/2014.
 * 8
 */

public class mondaysChild {


    // *********************************************
    // Declare variables etc.
    // *********************************************

    private int iDOW;
    private int iMonth;
    private int iYear;
    private int iDayOfWeek;

    private String sDOW;
    private String sLineFromPoem;
    private String[] saLinesOfPoem;
    private String[] saDOW;
    private String sOutputMsg;

    // *********************************************
    // Declare getters and setters etc.
    // *********************************************

    private void setiDOW(int isDOW)
    {
        this.iDOW = isDOW;
    }

    public int getiDOW()
    {
        return iDOW;
    }

    private void setiMonth(int isMonth)
    {
        this.iMonth = isMonth;
    }

    public int getiMonth()
    {
        return iMonth;
    }

    private void setiYear(int isYear)
    {
        this.iYear = isYear;
    }

    public int getiYear()
    {
        return iYear;
    }

    private void setiDayOfWeek(int siDayOfWeek)
    {
        this.iDayOfWeek = siDayOfWeek;
    }

    public int getiDayOfWeek()
    {
        return iDayOfWeek;
    }

    private void setsDOW(int iDay)
    {
        this.sDOW = saDOW[iDay];
    }

    public String getsDOW()
    {
        return sDOW;
    }

    private void setsLineFromPoem(int iPoemLine)
    {
        this.sLineFromPoem = saLinesOfPoem[iPoemLine];
    }

    public String getsLineFromPoem()
    {
        return sLineFromPoem;
    }

    private void setSaLinesOfPoem()
    {
        //Dec;ares appropriate string array for the days of the week
        saLinesOfPoem = new String[7];
        saLinesOfPoem[0] = "And the child that is born on the Sabbath day, Is bonny and blithe, and good and gay.";
        saLinesOfPoem[1] = "Mondays child is fair of face";
        saLinesOfPoem[2] = "Tuesdays child is full of grace";
        saLinesOfPoem[3] = "Wednesdays child is full of woe";
        saLinesOfPoem[4] = "Thursdays child has far to go";
        saLinesOfPoem[5] = "Fridays child is loving and giving";
        saLinesOfPoem[6] = "Saturdays child works hard for his living";
    }

    public String[] getSaLinesOfPoem()
    {
        return saLinesOfPoem;
    }

    private void setSaDOW()
    {
        //Sets the name associated with each day of the week using a array of strings
        saDOW = new String[7];
        saDOW[0] = "Sunday";
        saDOW[1] = "Monday";
        saDOW[2] = "Tuesday";
        saDOW[3] = "Wednesday";
        saDOW[4] = "Thursday";
        saDOW[5] = "Friday";
        saDOW[6] = "Saturday";
    }

    public String[] getSaDOW()
    {
        return saDOW;
    }

    private void setsOutputMsg(String sOutMsg)
    {
        this.sOutputMsg = sOutMsg;
    }

    public String getsOutputMsg()
    {
        return sOutputMsg;
    }

    // **************************************************
    // Declare constructor and date manipulation methods.
    // **************************************************
    public mondaysChild() {
        // Fill the day and poem arrays
        setSaDOW();
        setSaLinesOfPoem();
        // Use the Calendar to instantiate a calendar object for today's date
        Calendar cCal = Calendar.getInstance();
        // store the date, month, year
        setiDOW(cCal.get(Calendar.DAY_OF_WEEK));
        setiMonth(cCal.get(Calendar.MONTH));
        setiYear(cCal.get(Calendar.YEAR));
    }

    public mondaysChild(int mcDOW, int mcMonth, int mcYear)
    {
        // Fill the day and poem arrays
        setSaDOW();
        setSaLinesOfPoem();
        // store the date, month, year
        setiDOW(mcDOW);
        setiMonth(mcMonth);
        setiYear(mcYear);
        // Use the GregorianCalendar to instantiate a calendar object for the Birthday
        GregorianCalendar gcBDay = new GregorianCalendar(mcYear, mcMonth, mcDOW);
        // Set the day of the week integer using the Birthday
        setiDayOfWeek(gcBDay.get(Calendar.DAY_OF_WEEK) - 1);
        // Set the string day of the week
        setsDOW(getiDayOfWeek());
        // Set the line from the poem based on the integer day of the week
        setsLineFromPoem(getiDayOfWeek());
        // Create temporary string for output message
        String sTempStr = "You were born on a " + getsDOW() + "\n" +
                getsLineFromPoem();
        setsOutputMsg(sTempStr);
    }

}

