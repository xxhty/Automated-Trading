package macPackage;

import Model.MongoDB;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Calendar dateCalendar=Calendar.getInstance();
        dateCalendar.set(Calendar.YEAR,2016);
        dateCalendar.set(Calendar.MONTH,1);
        dateCalendar.set(Calendar.DAY_OF_MONTH,1);
        System.out.println(dateCalendar.getTime());
       MongoDB.getStockPrice("ROST",dateCalendar);
    }
}
