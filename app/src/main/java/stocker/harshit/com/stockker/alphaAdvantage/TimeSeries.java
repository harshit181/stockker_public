package stocker.harshit.com.stockker.alphaAdvantage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

public class TimeSeries {

    private TreeMap<Date,DataObject> stocksValue=new TreeMap<Date,DataObject>();

    public TreeMap<Date, DataObject> getStocksValue() {
        return stocksValue;
    }

    public void setStocksValue(TreeMap<Date, DataObject> stocksValue) {
        this.stocksValue = stocksValue;
    }
}
