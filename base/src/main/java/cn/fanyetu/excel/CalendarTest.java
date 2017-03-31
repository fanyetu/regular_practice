package cn.fanyetu.excel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {

	public static void main(String[] args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = "2011-08-23";
		Date dt = sdf.parse(str);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.YEAR, -1);// ÈÕÆÚ¼õ1Äê
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		System.out.println(reStr);

	}

}
