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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by TonyHuang on 4/29/17.
 */
public class MongoDB {
    MongoClient mongoClient = new MongoClient();
    public static List<Stock> getActiveStockbyDate(Date inceptionDate)
    {
        return new ArrayList<Stock>();
    }
    public static BigDecimal getStockPrice(String ticker, Calendar inceptionDate)
    {
        MongoClientURI connectionString = new MongoClientURI("mongodb://tnhninc:E4VFGKgEZR3qWbVc@primarycluster-shard-00-00-0ctky.mongodb.net:27017,primarycluster-shard-00-01-0ctky.mongodb.net:27017,primarycluster-shard-00-02-0ctky.mongodb.net:27017/StockPrices?ssl=true&replicaSet=PrimaryCluster-shard-0&authSource=admin");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("StockPrices");
        MongoCollection<Document> collection = database.getCollection("SP500");
        Document obj = new Document();
        obj.append("symbol",  ticker);
        //obj.append("date", inceptionDate);
        FindIterable<Document> result = collection.find(obj);
        if(result != null) {
            Document myDoc = result.first();
            System.out.println(myDoc.toJson());
            return new BigDecimal(myDoc.getDouble("open"));
        }

        return new BigDecimal("0.0");
    }

}
