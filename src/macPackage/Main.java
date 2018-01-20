package macPackage;

import Model.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        QuandlDataRepository repo = new QuandlDataRepository();
/*
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 4);
        Date dateRepresentation = cal.getTime();

        BigDecimal a = repo.getStockPrice("A", dateRepresentation);
        */

        StockPicker sp=new StockPicker(repo);
        Calendar dateCalendar=Calendar.getInstance();
        dateCalendar.set(Calendar.YEAR,2017);
        dateCalendar.set(Calendar.MONTH,Calendar.DECEMBER);
        dateCalendar.set(Calendar.DAY_OF_MONTH,11);
        System.out.println(dateCalendar.getTime());
        List<ISecurity> stocks=sp.top50(dateCalendar);

    }
}
