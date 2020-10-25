package com.intellect.spring.boot.cud.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(DateUtil.class);


	/**
	 * Method to Validate String format date.
	 * 
	 * @param Stringdate
	 * 
	 * @return boolean
	 */
	public static boolean isValidDate(String dateStr) {

		String regex = "\\d{0,2}-[a-zA-Z]{3}-\\d{4}";

		if (null != dateStr) {
			if (dateStr.matches(regex)) {
				String[] dateValArray = dateStr.split("-");
				int dateMonth = 0;
				String month = dateValArray[1];
				if(month.equalsIgnoreCase("JAN")) {
					dateMonth = 1;
				}else if(month.equalsIgnoreCase("FEB")) {
					dateMonth = 2;
				}else if(month.equalsIgnoreCase("MAR")) {
					dateMonth = 3;
				}else if(month.equalsIgnoreCase("APR")) {
					dateMonth = 4;
				}else if(month.equalsIgnoreCase("MAY")) {
					dateMonth = 5;
				}else if(month.equalsIgnoreCase("JUN")) {
					dateMonth = 6;
				}else if(month.equalsIgnoreCase("JUL")) {
					dateMonth = 7;
				}else if(month.equalsIgnoreCase("AUG")) {
					dateMonth = 8;
				}else if(month.equalsIgnoreCase("SEP")) {
					dateMonth = 9;
				}else if(month.equalsIgnoreCase("OCT")) {
					dateMonth = 10;
				}else if(month.equalsIgnoreCase("NOV")) {
					dateMonth = 11;
				}else if(month.equalsIgnoreCase("DEC")) {
					dateMonth = 12;
				}
				
				int  dateDay = Integer.parseInt(dateValArray[0]);
				int  dateYear= Integer.parseInt(dateValArray[2]);
				if (dateMonth < 1 || dateMonth > 12) {
					return false;
				} else if (dateDay < 1 || dateDay > 31) {
					return false;
				} else if ((dateMonth == 4 || dateMonth == 6 || dateMonth == 9 || dateMonth == 11)
						&& dateDay == 31) {
					return false;
				} else if (dateMonth == 2) {
					boolean isleap = (dateYear % 4 == 0 && (dateYear % 100 != 0 || dateYear % 400 == 0));
					if (dateDay > 29 || (dateDay == 29 && !isleap)) {
						return false;
					} else {
						return true;
					}
				} else {
					return true;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	
	/**
	 * Method to convert string date to util date.
	 * 
	 * @param dateStr
	 * @return java.util.Date
	 * @throws Exception 
	 */
	public static boolean isFutureDate(String dateStr) throws Exception {

		Date date = new Date();
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
			dateFormatter.setLenient(false);

			if (dateStr != null) {
				date = (Date) dateFormatter.parse(dateStr);
			}
			Date curDate = new Date();
			if(date.after(curDate))
			return false;
			else
				return true;
		} catch (Exception e) {
			logger.info("Exception has occured during date format== "
					+ e.getMessage());
			throw e;
		}
	}

}