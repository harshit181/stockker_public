package stocker.harshit.com.stockker.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import stocker.harshit.com.stockker.R;
import stocker.harshit.com.stockker.nseDataDownloader.StockInfo;

public class StockHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView stockName, stockSymbol, txtGravity, txtDiameter;
    private RecyclerViewClickListener mListener;
    public StockHolder(View itemView) {
        super(itemView);
        stockName = itemView.findViewById(R.id.stockName);
        stockSymbol = itemView.findViewById(R.id.stockSymbol);
        txtGravity = itemView.findViewById(R.id.txtGravity);
        txtDiameter = itemView.findViewById(R.id.txtDiameter);
    }
    public StockHolder(View itemView, RecyclerViewClickListener listener) {
        this(itemView);
        mListener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
    public void setDetails(StockInfo stock) {
        stockName.setText(stock.getStockName());
        stockSymbol.setText(stock.getIsin());
        txtGravity.setText(stock.getSeries());
        txtDiameter.setText(stock.getSymbol());
    }
}