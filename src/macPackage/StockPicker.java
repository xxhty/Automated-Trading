package macPackage;

import Model.MongoDB;
import Model.Stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TonyHuang on 4/29/17.
 */
public class StockPicker {
    public List<Stock> basicAlgorithm(Date inceptionDate)
    {

        List<Stock> stockList= MongoDB.getActiveStockbyDate(inceptionDate);

        return stockList;
    }

}
