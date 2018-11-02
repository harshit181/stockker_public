package stocker.harshit.com.stockker.alphaAdvantage;

public class DataBean {

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public TimeSeriesDaily getTimeSeriesDaily() {
        return timeSeriesDaily;
    }

    public void setTimeSeriesDaily(TimeSeriesDaily timeSeriesDaily) {
        this.timeSeriesDaily = timeSeriesDaily;
    }

    private MetaData metaData;
    private TimeSeriesDaily timeSeriesDaily;
}
