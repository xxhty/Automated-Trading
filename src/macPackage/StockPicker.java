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

        List<ISecurity> stockList= repo.getActiveStocksByDate(inceptionDate.getTime());
        List<ISecurity> top50= new LinkedList<ISecurity>();
        TreeMap<Double,ISecurity> stockPerformanceTM=new TreeMap<Double,ISecurity>(Comparator.reverseOrder());
        ArrayList<ISecurityFeature> featureList = new ArrayList<ISecurityFeature>();
        featureList.add(new XDaysPerformance(0.5, 365));
        featureList.add(new XDaysPerformance(0.5, 30));

        PerformanceCalculator perfcalc = new PerformanceCalculator(featureList);
        try {
            for (ISecurity s : stockList) {
                stockPerformanceTM.put(perfcalc.calculate(s, inceptionDate.getTime()), s);
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
