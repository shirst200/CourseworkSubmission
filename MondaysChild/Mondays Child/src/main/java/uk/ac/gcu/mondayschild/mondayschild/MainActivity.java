package uk.ac.gcu.mondayschild.mondayschild;


        import android.annotation.TargetApi;
        import android.app.DialogFragment;
        import android.app.FragmentManager;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.ActivityInfo;
        import android.os.Build;
        import android.preference.PreferenceManager;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.DatePicker;

        import java.io.IOException;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    //These variables are used to control the objects in the layout
    DatePicker dpBDay;
    Button submitBtn;
    //These variables are used to control the inputs so they can be accessed by other classes
    mcSaveData mcSDPrefs;
    SharedPreferences mySharedPrefs;
    FragmentManager fmAboutDialogue;

    //these variables are to pass to the output screen so it can better process the data
    String sOutputMsg;
    int MonthMsg;
    int DayMsg;

    mcStarSignsInfo userStarSignInfo;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override

    public void onClick(View view)
    {
        //When the button is clicked this function retrieves the data from the date picker and uses it to access the correct variables from the other classes
        mondaysChild mcYourDay = new mondaysChild(dpBDay.getDayOfMonth(),dpBDay.getMonth(),dpBDay.getYear());
        Astrology usersStarSign = new Astrology(dpBDay.getDayOfMonth(),dpBDay.getMonth()+1);
        //The values from the date picker are saved for later use
        mcSDPrefs.savePreferences("mc_DOW", mcYourDay.getiDOW());
        mcSDPrefs.savePreferences("mc_Month", mcYourDay.getiMonth());
        mcSDPrefs.savePreferences("mc_DayBorn", mcYourDay.getsDOW());
        mcSDPrefs.savePreferences("mc_StarSign", usersStarSign.getsStarSign());
        Intent mcOutput_Screen = new Intent(getApplicationContext(), mcOutputScreen.class);
        //The database is retrieved and rebuilt in the application
        mcStarSignsInfoDBMgr dbStarSignMgr = new mcStarSignsInfoDBMgr(this, "starsigns.s3db",null, 1);
        try{
            dbStarSignMgr.dbCreate();
        } catch (IOException e){
            e.printStackTrace();
        }

        //Data is stored ready to be accessed by the mcOutputScreen class
        userStarSignInfo = dbStarSignMgr.findStarSign(usersStarSign.getsStarSign());
        mcOutput_Screen.putExtra("starSignInfo", userStarSignInfo);
        sOutputMsg = mcYourDay.getsOutputMsg();
        mcOutput_Screen.putExtra("mcOutputMsg", sOutputMsg);
        MonthMsg = mcYourDay.getiMonth()+1;
        mcOutput_Screen.putExtra("mcMonthMsg", MonthMsg);
        DayMsg = mcYourDay.getiDOW();
        mcOutput_Screen.putExtra("mcDayMsg", DayMsg);
        Log.e("n", mcYourDay.getsOutputMsg());
        //The mcOutputScreen class is launcehed
        startActivity(mcOutput_Screen);
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //The only orientation we want the application to use is portrait so we need to lock it
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        //The variables need to be assigned to the corresponding objects in the layout
        dpBDay = (DatePicker)findViewById(R.id.datePickerBDay);
        dpBDay.setCalendarViewShown(false);
        submitBtn = (Button)findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);
        mySharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mcSDPrefs = new mcSaveData(mySharedPrefs);
        mcSDPrefs.setDefaultPrefs();
        fmAboutDialogue = this.getFragmentManager();

        Log.e("n", "message");
        userStarSignInfo = new mcStarSignsInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mc_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mBio:
                //When bioactivity is selected from the menu the bioacivity class must be launched
                Intent mcBioDraw = new Intent(this, mcBioActivity.class);
                this.startActivity(mcBioDraw);
                return true;
            case R.id.mQuit:
                //When the quit option is selected from the menu the application must close
                finish();
                return true;
            case R.id.mAbout:
                //When the about option is selected the dialog box must appear
                DialogFragment mcAboutDlg = new mcAboutDialouge();
                mcAboutDlg.show(fmAboutDialogue,"mc_About_Dlg");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void Load(View view)
    {
        //data must be able to be loaded from the save
        Intent mc_savingDataOutput = new Intent(getApplicationContext(), mcOutputScreen.class);
        mcStarSignsInfoDBMgr dbStarSignMgr = new mcStarSignsInfoDBMgr(this, "starsigns.s3db",null, 1);
        userStarSignInfo = dbStarSignMgr.findStarSign(mcSDPrefs.getmcSDStarSign());
        mc_savingDataOutput.putExtra("starSignInfo", userStarSignInfo);
        MonthMsg = mcSDPrefs.getmcSDMonth();
        mc_savingDataOutput.putExtra("mcMonthMsg", MonthMsg);
        DayMsg = mcSDPrefs.getmcSDDOW();
        mc_savingDataOutput.putExtra("mcDayMsg", DayMsg);
        startActivity(mc_savingDataOutput);
    }
}
