package macPackage;

import Model.MongoDB;
import Model.Stock;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        StockPicker sp=new StockPicker();
        Calendar dateCalendar=Calendar.getInstance();
        dateCalendar.set(Calendar.YEAR,2016);
        dateCalendar.set(Calendar.MONTH,1);
        dateCalendar.set(Calendar.DAY_OF_MONTH,1);
        System.out.println(dateCalendar.getTime());
        List<Stock> stocks=sp.top50(dateCalendar);


    }
}
