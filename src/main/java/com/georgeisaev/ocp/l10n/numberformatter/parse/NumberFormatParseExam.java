package com.georgeisaev.ocp.l10n.numberformatter.parse;

import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class NumberFormatParseExam {

	public static void main(String[] args) {
		new NumberFormatParseExam().convertAndParse(123456.789);
		new NumberFormatParseExam().convertAndParse(023456.789);
	}

	void convertAndParse(final double number) {
		final Locale frenchLocale = new Locale("fr", "FR");
		NumberFormat formatter = NumberFormat.getInstance(frenchLocale);
		final String numberPresentation = formatter.format(number);
		log.info("Number presentation {}", numberPresentation);
		formatter = NumberFormat.getInstance();
		try {
			final Number parsedNumber = formatter.parse(numberPresentation);
			log.info(number + " " + parsedNumber);
		} catch (ParseException e) {
			log.error("Cannot parse", e);
		}
	}

}
