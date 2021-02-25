package com.georgeisaev.ocp.l10n.dateformat.getDateInstance;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatExam {

	public static void main(String[] args) {

		final Date currentDate = new Date();
		final Locale locale = Locale.getDefault();
		final DateFormat dateFormat = DateFormat.getDateInstance();
		System.out.println(locale.getCountry() + " " + dateFormat.format(currentDate));

	}

}
