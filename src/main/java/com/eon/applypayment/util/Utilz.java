package com.eon.applypayment.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilz {

	public static String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}
