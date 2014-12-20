package uk.ac.gcu.mondayschild.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import junit.framework.TestCase;

/**
 * Created by Administrator on 08/12/2014.
 */
public class mcSavingDataOutput extends Activity implements View.OnClickListener
{

    SharedPreferences mcSharedPrefs;
    Button btnBack;
    TextView mcSDODOW;
    TextView mcSDOMonth;
    TextView mcSDODayBorn;
    TextView mcSDStarSign;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_display_shared_prefs);

        btnBack =(Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        mcSDODOW =(TextView) findViewById(R.id.tvDOW);
        mcSDOMonth =(TextView) findViewById(R.id.tvMonth);
        mcSDODayBorn =(TextView) findViewById(R.id.tvDayBorn);
        mcSDStarSign = (TextView) findViewById(R.id.tvStarSign);
        mcSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        loadSavedPreferences();
        Log.e("n", "SDOutput msg");

    }

    private void loadSavedPreferences()
    {
        mcSDODOW.setText(mcSDODOW.getText() + String.valueOf(mcSharedPrefs.getInt("mc_DOW",1)));
        mcSDOMonth.setText(mcSDOMonth.getText() + String.valueOf(mcSharedPrefs.getInt("mc_Month",1)));
        mcSDODayBorn.setText(mcSDODayBorn.getText() + String.valueOf(mcSharedPrefs.getString("mc_DayBorn","Sunday")));
        mcSDStarSign.setText(mcSDStarSign.getText() + mcSharedPrefs.getString("mc_starSign","January"));
    }
    public void onClick(View view)
    {
        setResult(Activity.RESULT_OK);
        finish();
    }

}
