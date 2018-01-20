package Model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface IDataRepository {
    BigDecimal getStockPrice(String ticker, Date inceptionDate);
    List<ISecurity> getActiveStocksByDate(Date date);

}
