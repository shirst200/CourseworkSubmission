package uk.ac.gcu.mondayschild.mondayschild;

public class Astrology {

    // *********************************************
    // Declare variables etc.
    // *********************************************

    private int iStarSign;

    private String sStarSign;

    // *********************************************
    // Declare getters and setters etc.
    // *********************************************


    public void setiStarSign(int iStarSign)
    {
        this.iStarSign = iStarSign;
    }

    public int getiStarSign()
    {
        return iStarSign;
    }

    public void setsStarSign(String sStarSign)
    {
        this.sStarSign = sStarSign;
    }

    public String getsStarSign()
    {
        return sStarSign;
    }

    // **************************************************
    // Declare constructor and date manipulation methods.
    // **************************************************

    public Astrology()
    {
        // Sets default value based on 1st January
        determineStarSign(1, 1);
    }

    public Astrology(int aDay, int aMonth)
    {
        // Sets value based on aDay, aMonth
        determineStarSign(aDay, aMonth);
    }

    public void determineStarSign(int ssDay, int ssMonth)
    {
        //Checks the imputed month and day to find appropriate star sign
        switch (ssMonth)
        {
            case 1:
                //if January
                if (ssDay > 20)
                {
                    //if after January 19th
                    sStarSign = "Aquarius";
                }
                else
                {
                    //if before January 20th
                    sStarSign = sStarSign + "Capricorn</p>";
                }
                break;
            case 2:
                //if February
                if (ssDay > 19)
                {
                    //If after February 19
                    sStarSign = "Pisces";
                }
                else
                {
                    //If before February 20th
                    sStarSign = "Aquarius";
                }
                break;
            case 3:
                //If March
                if (ssDay > 20)
                {
                    //If after March 20th
                    sStarSign = "Aries";
                }
                else
                {
                    //If before March 21st
                    sStarSign = "Pisces";
                }
                break;
            case 4:
                //If April
                if (ssDay > 21)
                {
                    //If after April 21st
                    sStarSign = "Taurus";
                }
                else
                {
                    //If before April 22nd
                    sStarSign = "Aries";
                }
                break;
            case 5:
                //If May
                if (ssDay > 21)
                {
                    //If after May 21st
                    sStarSign = "Gemini";
                }
                else
                {
                    //If before may 22nd
                    sStarSign = "Taurus";
                }
                break;
            case 6:
                //If June
                if (ssDay > 21)
                {
                    //If after June 21st
                    sStarSign = "Cancer";
                }
                else
                {
                    //If before June 22nd
                    sStarSign = "Gemini";
                }
                break;
            case 7:
                //If July
                if (ssDay > 22)
                {
                    //If after July 22nd
                    sStarSign = "Leo";
                }
                else
                {
                    //If before July 23rd
                    sStarSign = "Cancer";
                }
                break;
            case 8:
                //If August
                if (ssDay > 23)
                {
                    //If after August 23rd
                    sStarSign = "Virgo";
                }
                else
                {
                    //If before August 24th
                    sStarSign = "Leo";
                }
                break;
            case 9:
                //If September
                if (ssDay > 23)
                {
                    //If after September 23rd
                    sStarSign = "Libra";
                }
                else
                {
                    //If before September 24th
                    sStarSign = "Virgo";
                }
                break;
            case 10:
                //If October
                if (ssDay > 23)
                {
                    //If after October 23rd
                    sStarSign = "Scorpio";
                }
                else
                {
                    //If before October 24th
                    sStarSign = "Libra";
                }
                break;
            case 11:
                //if November
                if (ssDay > 22)
                {
                    //If after November 22nd
                    sStarSign = "Sagittarius";
                }
                else
                {
                    //If before November 23rd
                    sStarSign = "Scorpio";
                }
                break;
            case 12:
                //If December
                if (ssDay > 21)
                {
                    //If after the 21st
                    sStarSign = "Capricorn";
                }
                else
                {
                    //If before the 22nd
                    sStarSign = "Sagittarius";
                }
                break;
            default:
                //If not a month(>12||<1)
                sStarSign = "I'm sorry I don't recognise date";
        }
    }
}