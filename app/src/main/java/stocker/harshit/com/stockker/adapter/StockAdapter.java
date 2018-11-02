package stocker.harshit.com.stockker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import stocker.harshit.com.stockker.R;
import stocker.harshit.com.stockker.nseDataDownloader.StockInfo;

public class StockAdapter extends RecyclerView.Adapter<StockHolder> {

    private Context context;
    private ArrayList<StockInfo> stockInfoList;
    private RecyclerViewClickListener mListener;

    public StockAdapter(Context context, ArrayList<StockInfo> stockInfo,RecyclerViewClickListener listener) {
        this.context = context;
        this.stockInfoList = stockInfo;
        this.mListener=listener;
    }

    @NonNull
    @Override
    public StockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row,parent, false);
        return new StockHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StockHolder stockHolder, int position) {
    StockInfo stock=stockInfoList.get(position);
    stockHolder.setDetails(stock);
    }

    @Override
    public int getItemCount() {
        return stockInfoList.size();
    }
}
