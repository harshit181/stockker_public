package stocker.harshit.com.stockker.alphaAdvantage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaData {


    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getLast_Refreshed() {
        return Last_Refreshed;
    }

    public void setLast_Refreshed(String last_Refreshed) {
        Last_Refreshed = last_Refreshed;
    }



    @Expose
    @SerializedName("1. Information")
    private String Information;
    @Expose
    @SerializedName("2. Symbol")
    private String Symbol;
    @Expose
    @SerializedName("3. Last Refreshed")
    private String Last_Refreshed;
    @Expose
    @SerializedName("4. Interval")
    private String Interval;
    @Expose
    @SerializedName("5. Output Size")
    private String OutputSize;

    public String getInterval() {
        return Interval;
    }

    public void setInterval(String interval) {
        Interval = interval;
    }

    public String getOutputSize() {
        return OutputSize;
    }

    public void setOutputSize(String outputSize) {
        OutputSize = outputSize;
    }

    public String getTimeZone() {
        return TimeZone;
    }

    public void setTimeZone(String timeZone) {
        TimeZone = timeZone;
    }

    @Expose

    @SerializedName("6. Time Zone")
    private String TimeZone;
}
