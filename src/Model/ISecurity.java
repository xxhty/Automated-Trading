package Model;
import java.math.BigDecimal;
import java.util.Date;

public interface ISecurity {
    BigDecimal getPrice(Date dt);
}
