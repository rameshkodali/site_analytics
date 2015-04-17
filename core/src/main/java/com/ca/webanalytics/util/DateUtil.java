package com.ca.webanalytics.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class DateUtil {

	private static final DateFormat YYYY_MM_DDFORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	
	private static XLogger LOGGER = XLoggerFactory.getXLogger(DateUtil.class.getName());

	// convert date to newXMLGregorianCalendar
	/**
	 * Convert Date to Java Calendar
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar cal = null;
		if (date != null) {
			cal = Calendar.getInstance();
			cal.setTime(date);
			// filter out garbage dates
			if (cal.get(Calendar.YEAR) < 1900) {
				cal = null;
			}
		}
		return cal;
	}

	/**
	 * Parse the string to date
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String source) {
		Date formattedDate = null;
		try {
			formattedDate = YYYY_MM_DDFORMATTER.parse(source);
		} catch (Exception e) {
			LOGGER.error("Failed to parse the input date {}; error message : {}", source, e.getMessage());
		}
		return formattedDate;
	}
}
