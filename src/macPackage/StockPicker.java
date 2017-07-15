package macPackage;

import Model.MongoDB;
import Model.Stock;

import java.util.*;

/**
 * Created by TonyHuang on 4/29/17.
 */
public class StockPicker {
    public List<Stock> top50(Calendar inceptionDate)
    {

        List<Stock> stockList= MongoDB.getActiveStockbyDate(inceptionDate);
        List<Stock> top50= new LinkedList<Stock>();
        TreeMap<Double,Stock> stockPerformanceTM=new TreeMap<Double,Stock>();
        for (Stock s: stockList)
        {
            inceptionDate.add(Calendar.MONTH,-13);
            Date start=inceptionDate.getTime();
            inceptionDate.add(Calendar.MONTH,14);
            Date end=inceptionDate.getTime();

            stockPerformanceTM.put(s.calculatePerformance(start,end),s);



        }
        Iterator it = stockPerformanceTM.entrySet().iterator();
        int count=50;
        while (it.hasNext() && count>0) {

            Map.Entry pair = (Map.Entry)it.next();

            top50.add((Stock)pair.getValue());
           count--;
        }


        return top50;
    }

}
