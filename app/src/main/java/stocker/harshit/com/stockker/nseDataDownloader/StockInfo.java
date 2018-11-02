package stocker.harshit.com.stockker.nseDataDownloader;

import android.os.Parcel;
import android.os.Parcelable;

public class StockInfo implements Parcelable {
    protected StockInfo(Parcel in) {
        stockName = in.readString();
        symbol = in.readString();
        series = in.readString();
        isin = in.readString();
    }

    public static final Creator<StockInfo> CREATOR = new Creator<StockInfo>() {
        @Override
        public StockInfo createFromParcel(Parcel in) {
            return new StockInfo(in);
        }

        @Override
        public StockInfo[] newArray(int size) {
            return new StockInfo[size];
        }
    };

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    private String stockName;
    private String symbol;
    private String series;
    private String isin;

    public StockInfo(String stockName, String symbol, String series, String isin) {
        this.stockName = stockName;
        this.symbol = symbol;
        this.series = series;
        this.isin = isin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(stockName);
        parcel.writeString(symbol);
        parcel.writeString(series);
        parcel.writeString(isin);
    }
}
