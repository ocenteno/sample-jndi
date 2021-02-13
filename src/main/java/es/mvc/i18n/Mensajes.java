package es.mvc.i18n;

import java.util.*;

public class Mensajes {
	private static final String BUNDLE_NAME = "es.mvc.i18n.mensajes"; 

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	
	private Mensajes() {}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static String getString(String key, Object param) {
		try {
			return RESOURCE_BUNDLE.getString(key) + param;
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
