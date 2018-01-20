package Model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.*;
import java.io.*;

import QuandlModels.Column;
import QuandlModels.QuandlData;
import com.google.gson.*;

public class QuandlDataRepository implements IDataRepository {
    /*
    *
    *   https://www.quandl.com/api/v3/datatables/WIKI/PRICES.json?ticker=a&qopts.columns=ticker,open,date&api_key=hsm36CYsb94S375CxzCv
    *   https://www.quandl.com/api/v3/datatables/WIKI/PRICES.json?date=2000-12-4&qopts.columns=ticker&api_key=hsm36CYsb94S375CxzCv
    *
    *
    *
    * */
    private String tickerQueryUrl = "https://www.quandl.com/api/v3/datatables/WIKI/PRICES.json?date=%s&qopts.columns=ticker,adj_open,date&api_key=hsm36CYsb94S375CxzCv";
    private Hashtable<String, Hashtable<String, BigDecimal>> cachedPrices;

    public QuandlDataRepository() {
        this.cachedPrices = new Hashtable<>();
    }
    @Override
    public BigDecimal getStockPrice(String ticker, Date inceptionDate) {
        try {
            return cachedPrices.get(cacheStockDataForDate(inceptionDate)).get(ticker);
        }
        catch(Exception ex){
            return null;
        }
    }
    private String cacheStockDataForDate(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1 || dayOfWeek == 7){
            c.add(Calendar.DAY_OF_MONTH, (dayOfWeek % 7 + 1) * -1);
            date = c.getTime();
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        if(!cachedPrices.containsKey(dateString)) {
            String query = String.format(tickerQueryUrl, dateString);
            String json = getJsonResponse(query);
            cacheDataTable(convertJsonToDataTable(json));
        }
        return dateString;
    }
    public List<ISecurity> getActiveStocksByDate(Date date){
        List<ISecurity> ret = new ArrayList<>();
        for (Enumeration<String> tickers = cachedPrices.get(cacheStockDataForDate(date)).keys(); tickers.hasMoreElements();){
            String t = tickers.nextElement();
            Stock s = new  Stock(t, this);
            ret.add(s);
        }
        return ret;

    }
    private void cacheDataTable(QuandlData dt){
        ArrayList<Column> cols = dt.datatable.columns;
        for(int i = 0; i<dt.datatable.data.size(); i++){
            ArrayList<Object> fieldValueList = dt.datatable.data.get(i);
            String dateKey = "";
            String tickerKey = "";
            BigDecimal price = new BigDecimal("0");
            try{
                for(int j = 0; j<fieldValueList.size();j++){
                    switch (cols.get(j).name){
                        case "date":
                            dateKey = fieldValueList.get(j).toString();
                            break;
                        case "adj_open":
                            price = new BigDecimal(fieldValueList.get(j).toString());
                            break;
                        case "ticker":
                            tickerKey = fieldValueList.get(j).toString();
                            break;
                        default:
                            break;
                    }
                }
                if(dateKey != "" &&tickerKey != "" && price.doubleValue() != 0) {
                    if (!cachedPrices.containsKey(dateKey)) {
                        cachedPrices.put(dateKey, new Hashtable<>());
                    }
                    //if (!cachedPrices.get(dateKey).containsKey(tickerKey)) {
                    //   cachedPrices.get(dateKey).put(tickerKey, price);
                    //}

                    cachedPrices.get(dateKey).put(tickerKey, price);
                }
            }
            catch (Exception ex){}
        }
    }
    private String getJsonResponse(String urlToRead) {
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return result.toString();
        }
        catch (Exception ex){
            return null;
        }
    }

    private QuandlData convertJsonToDataTable(String jsonString){
        Gson parser = new Gson();
        return parser.fromJson(jsonString, QuandlData.class);
    }

}
