package uk.ac.gcu.mondayschild.mondayschild;

/**
 * Created by Administrator on 13/12/2014.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;

/**
 * Created by owner on 23/10/2014.
 */
public class mcAsyncRSSParser extends AsyncTask<String, Integer, mcRSSDataItem>{
    private Context appContext;
    private String urlRSSToParse;

    public mcAsyncRSSParser(Context currentAppContext, String urlRSS)
    {
        appContext = currentAppContext;
        urlRSSToParse = urlRSS;
    }

    @Override
    protected void onPreExecute(){
        Toast.makeText(appContext, "Parsing started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected mcRSSDataItem doInBackground(String... params)
    {
        mcRSSDataItem parsedData;
        mcRSSParser rssParser = new mcRSSParser();
        try{
            rssParser.parseRSSData(urlRSSToParse);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        parsedData = rssParser.getRSSDataItem();
        return parsedData;
    }

    @Override
    protected void onPostExecute(mcRSSDataItem result) {
        Toast.makeText(appContext, "Parsing finished!", Toast.LENGTH_SHORT).show();
    }
}
