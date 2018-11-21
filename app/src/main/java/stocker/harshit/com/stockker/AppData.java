package stocker.harshit.com.stockker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.ColorInt;
import stocker.harshit.com.stockker.alphaAdvantage.AlphaAdvantageHelper;
import stocker.harshit.com.stockker.alphaAdvantage.TimeSeries;
import stocker.harshit.com.stockker.nseDataDownloader.StockInfo;


public class AppData extends Activity {

    private static final String api ="5K2KPSPH03LTFLDP";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.data_activity);
        TextView a =findViewById(R.id.textView2);
        TextView b =findViewById(R.id.textView3);
        Intent i = getIntent();

        StockInfo qwe= i.getParcelableExtra("stockInfo");
        a.setText(qwe.getStockName());
        b.setText(qwe.getSymbol());
        LinearLayout ln=findViewById(R.id.card1Layout);
        ProgressBar prgs=new ProgressBar(this);
        ln.addView(prgs);
        ln.setGravity(Gravity.CENTER);
       // AlphaAdvantageHelper.getData("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=NSE:"+qwe.getSymbol()+"&apiKey="+api)
         AlphaAdvantageHelper helper=new AlphaAdvantageHelper(this,ln);
        helper.execute("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=NSE:"+qwe.getSymbol()+"&apikey="+api);


    }
}
