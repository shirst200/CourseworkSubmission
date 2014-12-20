package uk.ac.gcu.mondayschild.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 02/10/2014.
 */
public class    mcOutputScreen extends MainActivity implements View.OnClickListener
{
    //These variables are used to control the objects in the layout
    Button btnDatePick;
    Button btnShowSavedData;
    ImageView ivStarSign;
    TextView tvStarSign;
    TextView tvStarSignDates;
    TextView tvStarSignChars;
    TextView tvHoroscope;
    mcSaveData save;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_output_screen);

        //The variables are connected to the objects in the layout
        btnDatePick = (Button)findViewById(R.id.btnPickDate);
        btnDatePick.setOnClickListener(this);
        btnShowSavedData = (Button)findViewById(R.id.btnSavedData);
        btnShowSavedData.setOnClickListener(this);
        tvStarSign = (TextView) findViewById(R.id.tvStarSign);
        tvStarSignDates = (TextView) findViewById(R.id.tvStarSignDates);
        tvStarSignChars = (TextView) findViewById(R.id.tvStarSignChars);
        ivStarSign = (ImageView) findViewById(R.id.imgViewStarSigns);

        Intent iMainAct = getIntent();

        //Data is retrieved from the main script, the information about the star sign and the date information
        mcStarSignsInfo starSignsInfo = (mcStarSignsInfo) iMainAct.getSerializableExtra("starSignInfo");
        int Moth = (int) iMainAct.getIntExtra("mcMonthMsg",1);
        int da = (int) iMainAct.getIntExtra("mcDayMsg",1);

        //The first three text fields are populated with information from the starsign classes and database
        tvStarSign.setText(starSignsInfo.getStarSign());
        tvStarSignDates.setText(starSignsInfo.getStarSignDates());
        tvStarSignChars.setText("You and these celebrities share the following traits:\n"+starSignsInfo.getStarSignCharacteristics());
        //An appropriate image is selected using the star sign
        String sImagePath = "drawable/" + starSignsInfo.getStarSignImg();
        Context appContext = getApplicationContext();
        int imgResID = appContext.getResources().getIdentifier(sImagePath, "drawable", "uk.ac.gcu.mondayschild.mondayschild");
        ivStarSign.setImageResource(imgResID);

        //the rss object is created
        mcRSSDataItem userHoroscope = new mcRSSDataItem();
        String month;
        //if the ints are below 10 they webiste will not be correct as it requires two characters not one
        if (Moth <10){month="0" + Moth;}else{month = "" + Moth;}
        String day;
        if (da <10){day="0" + da;}else{day = "" + da;}
        //once the date is in the correct format it is added to the url
        String RSSFeedURL = "http://www.imdb.com/date/"+month+"-"+day+"/births";
        //the parser retrieves information from the website and formatts it to be used in the application
        mcAsyncRSSParser rssAsyncParse = new mcAsyncRSSParser(this, RSSFeedURL);
        try {
            userHoroscope = rssAsyncParse.execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //The formatted data is displayed on the textview
        tvHoroscope = (TextView) findViewById(R.id.tvStarSignHoroScope);
        tvHoroscope.setText(RSSFeedURL + "\n" + "\n" + userHoroscope.getItemDesc());

    }
    public void mcOutputScreen(mcSaveData save)
    {
        this.save =save;
    }

    public void onClick(View view)
    {
        //Used to return to the main page
        setResult(Activity.RESULT_OK);
        finish();
    }
    public void Save(View view)
    {
        save = new mcSaveData(mcSDPrefs.mcSharedPrefs);
    }
}