package uk.ac.gcu.mondayschild.mondayschild;

import java.io.Serializable;

/**
 * Created by owner on 23/10/2014.
 */
public class mcRSSDataItem implements Serializable {

    private String itemTitle;
    private String itemDesc;
    private String itemLink;

    public String getItemTitle()
    {
        return this.itemTitle;
    }

    public void setItemTitle(String sItemTitle)
    {
        this.itemTitle = sItemTitle;
    }

    public String getItemDesc()
    {
        return this.itemDesc;
    }

    public void setItemDesc(String sItemDesc)
    {
        this.itemDesc = sItemDesc;
    }

    public String getItemLink()
    {
        return this.itemLink;
    }

    public void setItemLink(String sItemLink)
    {
        this.itemLink = sItemLink;
    }

    public mcRSSDataItem()
    {
        this.itemTitle = "";
        this.itemDesc = "";
        this.itemLink = "";
    }

    @Override
    public String toString(){
        String starSignRSSHoroscopeData;
        starSignRSSHoroscopeData = "mcRSSDataItem [itemTitle=" + itemTitle;
        starSignRSSHoroscopeData = ", itemDesc=" + itemDesc;
        starSignRSSHoroscopeData = ", itemLink=" + itemLink +"]";
        return starSignRSSHoroscopeData;
    }
}