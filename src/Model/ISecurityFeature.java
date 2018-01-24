package Model;

import java.util.Date;

public interface ISecurityFeature {
    double getWeight();
    double calculate(ISecurity s, Date referenceDate);
}
