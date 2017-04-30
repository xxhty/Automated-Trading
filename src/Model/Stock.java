package Model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import java.math.BigDecimal;
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
    public BigDecimal getPrice(Date theDay)
    {
        return MongoDB.getStockPrice(ticker,theDay);


    }
    public double calculateReturn(Date start,Date end)
    {
        return MongoDB.getStockPrice(ticker,end).divide(MongoDB.getStockPrice(ticker,start)).subtract(new BigDecimal("1")).doubleValue();
    }

}
