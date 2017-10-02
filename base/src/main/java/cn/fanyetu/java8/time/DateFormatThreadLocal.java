package cn.fanyetu.java8.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhanghaonan on 2017/10/2.
 */
public class DateFormatThreadLocal {

	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		}
	};

	public static Date format(String str) throws ParseException {
		return df.get().parse(str);
	}
}
