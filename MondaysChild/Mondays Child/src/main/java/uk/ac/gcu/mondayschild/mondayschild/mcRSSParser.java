package uk.ac.gcu.mondayschild.mondayschild;

/**
 * Created by Administrator on 13/12/2014.
 */

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;


/**
 * Created by rla on 17/10/2014.
 */
public class mcRSSParser {

    private mcRSSDataItem RSSDataItem;

    public void setRSSDataItem(String sItemData)
    {
        //Sets all the DataItem variables to the same string
        RSSDataItem.setItemTitle(sItemData);
        RSSDataItem.setItemDesc(sItemData);
        RSSDataItem.setItemLink(sItemData);
    }

    public mcRSSDataItem getRSSDataItem()
    {
        return this.RSSDataItem;
    }

    public mcRSSParser()
    {
        //Creates empty data item
        this.RSSDataItem =  new mcRSSDataItem();
        setRSSDataItem(null);
    }

    public void parseRSSDataItem(String data)
    {
        //This function takes the data input and returns the formatted data the

        //To get the webpage title the String is split in three and the center piece extracted cleaned off and stored
        String[] title = data.split("title>");
        title[1].replace("IMDb",""); title[1].replace("</","");
        RSSDataItem.setItemTitle(title[1]);

        //To separate the data into it's items the first useless chunk is removed
        String[] Hold1=data.split("<div id=\"main\">");
        //Once only the block of items are left the are split into individual items and stored on a array of strings
        String[] Description = Hold1[1].split("li>");
        RSSDataItem.setItemDesc("You share this birthday with celebrities:\n");
        //Each item must be processed separately and combined afterwards
        for(int i = 1;i<Description.length-30;i++)
        {
            //To clean the relevant data we split the string into words
            StringTokenizer temp = new StringTokenizer(Description[i]);
            String holder = "";String holderTwo;

            for(int n = 0;n<4;n++)
            {
                //The word is sent back to a string
                String thang=temp.nextToken().toString();
                //The second word contains the url and first name
                if (n==1)
                {
                    //The string is split to separate the name and url
                    String[] thing = thang.split(">");
                    //The url is cleaned
                    String spllitt = thing[0].replace("href=\"","").replace("\"", "");
                    //the url in the feed is relative to the website, to make it work for us the full url must be used
                    holder = "http://www.imdb.com" + spllitt;
                    RSSDataItem.setItemLink(holder);
                    String string = thing[1].toString() + " ";
                    //The first name is added to the list
                    RSSDataItem.setItemDesc(RSSDataItem.getItemDesc() + string);
                }
                //The third word is the second name
                else if (n==2)
                {
                    //The second name is followed by unnecessary data
                    String[] thing = thang.split("<");
                    String string = thing[0].toString();
                    //The Second name is added to the list
                    RSSDataItem.setItemDesc(RSSDataItem.getItemDesc()+string+": ");
                }
                else if (n==3)
                {
                    holder = "\n" + holder + "\n";
                    //The year has clutter around it
                    holderTwo = thang.replace("(","").replace(",","");
                    // The year and url are added to the string
                    RSSDataItem.setItemDesc(RSSDataItem.getItemDesc() + holderTwo + holder);
                }
            }
        }

    }

    public void parseRSSData(String RSSItemsToParse) throws MalformedURLException {
        URL rssURL = new URL(RSSItemsToParse);
        InputStream rssInputStream;
        try
        {
            XmlPullParserFactory parseRSSfactory = XmlPullParserFactory.newInstance();
            parseRSSfactory.setNamespaceAware(true);
            XmlPullParser RSSxmlPP = parseRSSfactory.newPullParser();
            //Retrieves html from webpage
            String xmlRSS = getStringFromInputStream(getInputStream(rssURL), "UTF-8");
            RSSxmlPP.setInput(new StringReader(xmlRSS));
            //Sends to parser
            parseRSSDataItem(xmlRSS);
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.e("MyTag","End document");
    }

    public InputStream getInputStream(URL url) throws IOException
    {
        return url.openConnection().getInputStream();
    }

    public static String getStringFromInputStream(InputStream stream, String charsetName) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, charsetName);
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }
}
