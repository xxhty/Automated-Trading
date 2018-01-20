package Model;
import java.math.BigDecimal;
import java.util.Date;

public interface IInvestment {
    ISecurity getSecurity();
    BigDecimal getShares();
    Date getDatePurchased();
    Date getDateSold();
}
