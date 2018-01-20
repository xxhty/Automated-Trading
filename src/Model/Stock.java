package Model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by TonyHuang on 4/29/17.
 */
public class Stock implements ISecurity{
    private String ticker;
    private IDataRepository repo;
    public Stock(String ticker, IDataRepository repo)
    {
        this.ticker=ticker;
        this.repo = repo;
    }
    public BigDecimal getPrice(Date d)
    {
        return repo.getStockPrice(ticker,d);

    }
    public String getTicker()
    {
        return ticker;
    }

}
