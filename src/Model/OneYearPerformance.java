package Model;

import java.time.chrono.IsoChronology;

public class OneYearPerformance implements ISecurityFeature {
    private double weight;
    public OneYearPerformance(double weight){
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    @Override
    public double calculate(ISecurity s) {
        return 0;
    }
}
