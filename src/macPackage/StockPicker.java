package macPackage;

import Model.*;

import java.io.Console;
import java.util.*;

/**
 * Created by TonyHuang on 4/29/17.
 */
public class StockPicker {
    private IDataRepository repo;
    public StockPicker(IDataRepository repo){
        this.repo = repo;
    }
    public List<ISecurity> top50(Calendar inceptionDate)
    {
        inceptionDate.add(Calendar.MONTH, -13);
        Date start = inceptionDate.getTime();
        inceptionDate.add(Calendar.MONTH, 12);
        Date end = inceptionDate.getTime();

        List<ISecurity> stockList= repo.getActiveStocksByDate(inceptionDate.getTime());
        List<ISecurity> top50= new LinkedList<ISecurity>();
        TreeMap<Double,ISecurity> stockPerformanceTM=new TreeMap<Double,ISecurity>(Comparator.reverseOrder());
        PerformanceCalculator perfcalc = new PerformanceCalculator();
        try {
            for (ISecurity s : stockList) {
                stockPerformanceTM.put(perfcalc.calculate(s, start, end), s);
            }
        }
        catch (Exception ex){

        }
        Iterator it = stockPerformanceTM.entrySet().iterator();
        int count=50;
        while (it.hasNext() && count>0) {

            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(((Stock)pair.getValue()).getTicker() + ": " + pair.getKey());
            top50.add((Stock)pair.getValue());
           count--;
        }


        return top50;
    }

}
