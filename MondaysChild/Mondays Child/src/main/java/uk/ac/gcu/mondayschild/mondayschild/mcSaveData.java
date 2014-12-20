package uk.ac.gcu.mondayschild.mondayschild;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class mcSaveData extends Activity
{

    // *********************************************
    // Declare variables etc.
    // *********************************************

    SharedPreferences mcSharedPrefs;

    private int mcSDDOW;
    private int mcSDMonth;
    private String mcSDDayBorn;
    public String mcSDStarSign;

    // *********************************************
    // Declare getters and setters etc.
    // *********************************************

    private void setmcSDDOW(int isDOW)
    {
        this.mcSDDOW = isDOW;
    }

    public int getmcSDDOW()
    {
        return mcSDDOW;
    }

    private void setmcSDMonth(int isMonth)
    {
        this.mcSDMonth = isMonth;
    }

    public int getmcSDMonth()
    {
        return mcSDMonth;
    }

    private void setmcSDDayBorn(String isDayBorn)
    {
        this.mcSDDayBorn = isDayBorn;
    }

    public String getmcSDDayBorn()
    {
        return mcSDDayBorn;
    }

    public void setmcSDStarSign(String mcSDStarSign)
    {
        this.mcSDStarSign =mcSDStarSign;
    }

    public String getmcSDStarSign()
    {
        return mcSDStarSign;
    }
    // **************************************************
    // Declare constructor and date manipulation methods.
    // **************************************************

    public mcSaveData(SharedPreferences mcSDPrefs)
    {
        setmcSDStarSign("January");
        setmcSDDOW(1);
        setmcSDMonth(1);
        setmcSDDayBorn("Sunday");
        try {
            this.mcSharedPrefs = mcSDPrefs;
        } catch (Exception e) {
            Log.e("n", "Pref Manager is NULL");
        }
        setDefaultPrefs();
    }

    public void savePreferences(String key, boolean value)
    {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void savePreferences(String key, String value)
    {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void savePreferences(String key, int value)
    {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void setDefaultPrefs()
    {
        savePreferences("mc_StarSign","Empty");
        savePreferences("mc_DOW", 1);
        savePreferences("mc_Month", 1);
        savePreferences("mc_DayBorn", "Empty");
    }
}