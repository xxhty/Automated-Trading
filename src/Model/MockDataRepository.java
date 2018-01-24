package Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MockDataRepository implements IDataRepository{

    @Override
    public BigDecimal getStockPrice(String ticker, Date inceptionDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(inceptionDate);
        switch (c.get(Calendar.YEAR)){
            case 2016:
                switch (ticker) {
                    case "A":
                        return new BigDecimal(100);
                    case "B":
                        return new BigDecimal(50);
                    case "C":
                        return new BigDecimal(1);
                    default:
                        break;
                }
                break;
            case 2017:
                switch (c.get(Calendar.MONTH)) {
                    case Calendar.NOVEMBER:
                        switch (ticker) {
                            case "A":
                                return new BigDecimal(1);
                            case "B":
                                return new BigDecimal(100);
                            case "C":
                                return new BigDecimal(2);
                            default:
                                break;
                        }
                        break;
                    default:
                        switch (ticker) {
                            case "A":
                                return new BigDecimal(1.5);
                            case "B":
                                return new BigDecimal(100);
                            case "C":
                                return new BigDecimal(3);
                            default:
                                break;
                        }
                        break;
                }
            default:
                break;

        }
        return new BigDecimal(3);
    }

    @Override
    public List<ISecurity> getActiveStocksByDate(Date date) {
        String[] list = {"A","B","C"};
        ArrayList<ISecurity> ret = new ArrayList<>();
        for (String t: list) {
            Stock s = new Stock(t, this);
            ret.add(s);
        }
        return ret;
    }
}
