package daw.mvc.i18n;

import java.util.*;

public class Mensajes {
	private static final String BUNDLE_NAME = "daw.mvc.i18n.mensajes"; 

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	
	private Mensajes() {}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
