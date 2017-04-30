package Model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import sun.util.resources.cldr.aa.CalendarData_aa_DJ;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by TonyHuang on 4/29/17.
 */
public class Stock {
    private String ticker;
    public Stock(String ticker)
    {
        this.ticker=ticker;
    }
    public BigDecimal getPrice(Calendar theDay)
    {
        return MongoDB.getStockPrice(ticker,theDay);


    }
    public double calculateReturn(Calendar start,Calendar end)
    {
        return MongoDB.getStockPrice(ticker,end).divide(MongoDB.getStockPrice(ticker,start)).subtract(new BigDecimal("1")).doubleValue();
    }

}
