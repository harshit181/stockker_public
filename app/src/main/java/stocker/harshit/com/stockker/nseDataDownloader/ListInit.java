package stocker.harshit.com.stockker.nseDataDownloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import stocker.harshit.com.stockker.AppData;
import stocker.harshit.com.stockker.R;
import stocker.harshit.com.stockker.adapter.RecyclerViewClickListener;
import stocker.harshit.com.stockker.adapter.StockAdapter;

public class ListInit extends AsyncTask<String, Void,ArrayList<StockInfo>  > {

    private Context mContext;
    private RecyclerView recyclerView;
    private StockAdapter adapter;
    private ArrayList<StockInfo> StockInfoArrayList =new ArrayList<StockInfo>();

    public ListInit(Context context){
        mContext = context;
    }


    private ArrayList<StockInfo> download()
    {

        try {
            URL url = new URL("https://www.nseindia.com/content/equities/EQUITY_L.csv");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            //ToDo : Can it be optimised ?
            while ((str = in.readLine()) != null) {
                String[] a=str.split(",");
                StockInfo ab =new StockInfo(a[1],a[0],a[2],a[6]);
                StockInfoArrayList.add(ab);
            }

                    in.close();
            return StockInfoArrayList;
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {

            return null;
        }
    }


    protected ArrayList<StockInfo> doInBackground(String... params) {
        return download();
    }


    protected void onPostExecute(ArrayList<StockInfo> StockInfoArrayList)
    {

        recyclerView = ((Activity)mContext).findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        RecyclerViewClickListener listener = (view, position) -> {
            Intent myIntent = new Intent(mContext,
                    AppData.class);
            myIntent.putExtra("stockInfo",StockInfoArrayList.get(position));
            mContext.startActivity(myIntent);
        };
        adapter = new StockAdapter(mContext, StockInfoArrayList,listener);
        recyclerView.setAdapter(adapter);
        Log.d("size is",""+StockInfoArrayList.size());
        adapter.notifyDataSetChanged();


    }

}
