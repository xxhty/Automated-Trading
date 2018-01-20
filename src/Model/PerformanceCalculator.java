package Model;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
public class PerformanceCalculator {
    public PerformanceCalculator(){}
    public double calculate(ISecurity s, Date start, Date end){
        BigDecimal endPrice = s.getPrice(end);
        BigDecimal startPrice = s.getPrice(start);
        if(startPrice == null || endPrice == null){
            return -1;
        }
        BigDecimal ratio = endPrice.divide(startPrice, RoundingMode.FLOOR);
        BigDecimal performance = ratio.subtract(new BigDecimal("1"));
        return performance.doubleValue();
    }
}
