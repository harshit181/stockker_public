package stocker.harshit.com.stockker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import stocker.harshit.com.stockker.nseDataDownloader.StockInfo;

public class AppData extends Activity {

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

    }
}
