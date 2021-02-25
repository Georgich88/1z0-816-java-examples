package com.georgeisaev.ocp.l10n.resourcebundle.getValue;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleExam {

	public static void main(String[] args) {
		Locale locale = new Locale("en", "US");
		ResourceBundle bundle = ResourceBundle.getBundle("test.MyBundle", locale);
		Object object = bundle.getObject("key1");
		String[] values = bundle.getStringArray("key2");
		System.out.println(object);
		System.out.println(Arrays.toString(values));
	}

}
