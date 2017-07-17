package Model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by TonyHuang on 4/29/17.
 */
public class MongoDB {
    MongoClient mongoClient = new MongoClient();
    public static List<Stock> getActiveStockbyDate(Calendar inceptionDate)
    {

        return new ArrayList<Stock>();
    }
    public static BigDecimal getStockPrice(String ticker, Date d)
    {
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017/AutomatedTrading");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("AutomatedTrading");
        MongoCollection<Document> collection = database.getCollection("Stocks");
        Document obj = new Document();
        obj.append("ticker",  ticker);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        obj.append("date", sdf.format(inceptionDate));

        FindIterable<Document> result = collection.find(obj);
        if(result != null) {
            Document myDoc = result.first();
            System.out.println(myDoc.toJson());
            return new BigDecimal(BigDecimal.valueOf(myDoc.getString("open")));
        }

        return new BigDecimal("0.0");
    }

}
