package com.georgeisaev.ocp.l10n.dateformat.ofPattern;


import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

// @formatter:off
/**
 * Illustrates the result of {@code eeee} pattern.
 *
 *           e       1      append special localized WeekFields element for numeric day-of-week
 *           ee      2      append special localized WeekFields element for numeric day-of-week, zero-padded
 *           eee     3      appendText(ChronoField.DAY_OF_WEEK, TextStyle.SHORT)
 *           eeee    4      appendText(ChronoField.DAY_OF_WEEK, TextStyle.FULL)
 *           eeeee   5      appendText(ChronoField.DAY_OF_WEEK, TextStyle.NARROW)
 *
 * @see DateTimeFormatterBuilder#appendPattern
 */
// @formatter:on
@Slf4j
public class DayOfWeekFormatterPatternExam {

	private static final DateTimeFormatter NUMERIC_DAY_OF_WEEK = DateTimeFormatter.ofPattern("e");
	private static final DateTimeFormatter NUMERIC_DAY_OF_WEEK_ZERO_PADDED = DateTimeFormatter.ofPattern("ee");
	private static final DateTimeFormatter SHORT_DAY_OF_WEEK = DateTimeFormatter.ofPattern("eee");
	private static final DateTimeFormatter FULL_DAY_OF_WEEK = DateTimeFormatter.ofPattern("eeee");
	private static final DateTimeFormatter NARROW_DAY_OF_WEEK = DateTimeFormatter.ofPattern("eeee");

	public static void main(String[] args) {
		new DayOfWeekFormatterPatternExam().printDay(LocalDate.now());
	}

	private void printDay(LocalDate date) {

		log.info("e     {}",NUMERIC_DAY_OF_WEEK.format(date));
		log.info("ee    {}",NUMERIC_DAY_OF_WEEK_ZERO_PADDED.format(date));
		log.info("eee   {}",SHORT_DAY_OF_WEEK.format(date));
		log.info("eeee  {}",FULL_DAY_OF_WEEK.format(date));
		log.info("eeeee {}",NARROW_DAY_OF_WEEK.format(date));
	}

}
