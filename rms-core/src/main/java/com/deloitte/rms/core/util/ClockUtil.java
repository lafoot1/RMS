package com.deloitte.rms.core.util;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class ClockUtil {
	
	public Calendar getCurrentTime() {
		return (Calendar) Calendar.getInstance().clone();
	}
	
	public Calendar getCurrentDate() {
		Calendar cal = getCurrentTime();
		cal.clear(Calendar.HOUR);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.clear(Calendar.AM_PM);
		return cal;
	}
}
