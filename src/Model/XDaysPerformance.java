package Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

public class XDaysPerformance implements ISecurityFeature {
    private double weight;
    private int days;
    public XDaysPerformance(double weight, int days){
        this.weight = weight;
        this.days = days;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    @Override
    public double calculate(ISecurity s, Date referenceDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.add(Calendar.DATE, -1 * days);
        Date xDaysAgo = c.getTime();

        BigDecimal startPrice = s.getPrice(xDaysAgo);
        BigDecimal endPrice = s.getPrice(referenceDate);
        if(startPrice == null || endPrice == null){
            return -1;
        }
        BigDecimal ratio = endPrice.divide(startPrice, RoundingMode.FLOOR);
        BigDecimal performance = ratio.subtract(new BigDecimal("1"));
        return performance.doubleValue();
    }
}
