package macPackage;

import Model.MongoDB;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Date date= new Date(2000-1900, 2, 1);
        System.out.println(date.getTime());
        BigDecimal dec = MongoDB.getStockPrice("A",date);
        int i = 0;
    }
}
