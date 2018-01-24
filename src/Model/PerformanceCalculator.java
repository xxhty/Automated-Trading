package Model;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class PerformanceCalculator {
    private List<ISecurityFeature> featureList;
    public PerformanceCalculator(List<ISecurityFeature> featureList){
        this.featureList = featureList;
    }
    public double calculate(ISecurity s, Date start, Date end){
        double ret = 0;
        for(ISecurityFeature f: featureList){
            ret += f.getWeight() * f.calculate(s);
        }
        return ret;
    }
}
