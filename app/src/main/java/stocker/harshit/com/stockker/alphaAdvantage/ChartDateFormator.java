package stocker.harshit.com.stockker.alphaAdvantage;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChartDateFormator implements IAxisValueFormatter {
    private static final ChartDateFormator ourInstance = new ChartDateFormator();
    private static final SimpleDateFormat df=new SimpleDateFormat("ddMM");
    public static ChartDateFormator getInstance() {
        return ourInstance;
    }

    public  ChartDateFormator() {
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        Float w=value;
        Date temp=new Date(w.longValue());
        return df.format(temp);
    }

    public int getDecimalDigits() { return 1; }
}
