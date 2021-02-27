package com.georgeisaev.ocp.l10n.resourcebundle.locale.setdefault;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Illustrates the use of {@link Locale#setDefault(Locale)}  and {@link ResourceBundle#getBundle(String, Locale)}
 *
 * @author Georgy Isaev
 */
@Slf4j
public class LocaleSetDefaultExam {

	public static void main(String[] args) {
		new LocaleSetDefaultExam().printMessage();
	}

	void printMessage() {
		Locale.setDefault(new Locale("fr", "CA")); // Set default to French Canada
		Locale l = new Locale("jp", "JP");
		try {
			ResourceBundle rb = ResourceBundle.getBundle("appMessages", l);
			String msg = rb.getString("greetings");
			log.info(msg);
		} catch (MissingResourceException e) {
			log.error("Cannot print message", e);
		}

	}

}
