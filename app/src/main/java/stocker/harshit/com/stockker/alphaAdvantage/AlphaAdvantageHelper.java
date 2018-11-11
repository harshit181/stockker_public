package stocker.harshit.com.stockker.alphaAdvantage;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import java.net.URL;

public class AlphaAdvantageHelper extends AsyncTask<String, Integer,TimeSeries> {

    private static final String TimeSeriesDaily="Time Series (Daily)";
    private static final String MetaData="Meta Data";
    private static final SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat df2=new SimpleDateFormat("EEEE dd-MMM-yyyy");
    private Context mContext;
    private ViewGroup someView;

    public AlphaAdvantageHelper(Context mcContext,ViewGroup view) {
        this.mContext = mcContext;
        this.someView=view;
    }

    public static TimeSeries getData(String urlString)  {

        TimeSeries data=new TimeSeries();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        StringBuilder urlBuilder =new StringBuilder(urlString);


        URL url=null;
        try {
             url = new URL(urlBuilder.toString());
            String json = IOUtils.toString(url,Charset.defaultCharset());
            JsonReader reader = new JsonReader(new StringReader(json));
            handleObject(reader,data);
           for (Date a:data.getStocksValue().keySet())
           {
                Log.d("DateData",a.toString()+" "+data.getStocksValue().get(a).getClose());

           }

        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        return data;
    }

    private static void handleObject(JsonReader reader,TimeSeries data) throws IOException, ParseException {

        reader.beginObject();

        while (reader.hasNext()) {
            JsonToken token = reader.peek();
             if (token.equals(JsonToken.BEGIN_OBJECT))
                handleObject(reader,data);
            else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
            } else
                handleNonArrayToken(reader, token,data);
        }
        reader.endObject();


    }


    public static void handleNonArrayToken(JsonReader reader, JsonToken token,TimeSeries data) throws IOException, ParseException {

        if (token.equals(JsonToken.NAME)) {
            String name=reader.nextName();
            if (name.equals(MetaData)) {
                skipMetaData(reader);
            }
            else if (name.equals(TimeSeriesDaily))
            {
                reader.beginObject();
                while(reader.hasNext()) {
                    data.getStocksValue().put(df.parse(reader.nextName()), createData(reader));
                }
            }
            else
            System.out.println(name);
        }

        else if (token.equals(JsonToken.STRING))
            System.out.println(reader.nextString());
        else if (token.equals(JsonToken.NUMBER))
            System.out.println(reader.nextDouble());
        else
            handleObject(reader,data);
    }

    public static boolean skipMetaData(JsonReader reader) throws IOException {

        reader.beginObject();

        while (true) {
            JsonToken token = reader.peek();
            if(token.equals(JsonToken.END_OBJECT))
            {
                reader.endObject();
                return true;
            }
            else
            {
                reader.skipValue();
            }

        }
    }

    public static DataObject createData(JsonReader reader) throws IOException {

        DataObject temp=new DataObject();
            reader.beginObject();
            reader.skipValue();
            temp.setOpen(reader.nextString());
            reader.skipValue();
            temp.setHigh(reader.nextString());
        reader.skipValue();
        temp.setLow(reader.nextString());
        reader.skipValue();
        temp.setClose(reader.nextString());
        reader.skipValue();
        temp.setVolume(reader.nextString());
        reader.endObject();
        return temp;
    }



    @Override
    protected TimeSeries doInBackground(String... strings) {
        return getData(strings[0]);
    }

    protected void onPostExecute(TimeSeries tm_srs)
    {
        someView.removeAllViews();
        TextView a =new TextView(mContext);

        String text="Close Price on \n"+df2.format(tm_srs.getStocksValue().lastKey())+"\n"+tm_srs.getStocksValue().get( tm_srs.getStocksValue().lastKey()).getClose();
        a.setText(text);

        a.setTextSize(TypedValue.COMPLEX_UNIT_SP,23);
        someView.addView(a);

    }
}
