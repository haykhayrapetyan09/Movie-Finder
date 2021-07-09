package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * The Class which works with properties file for changing the language.
 */
public class PropertiesWorker {
	
	/** The current  language. */
	public static String language=readLanguage();
	
	/**
	 * Reading the language property from local file.
	 *
	 * @return the string with current language
	 */
	public static String readLanguage() {
		FileReader fileReader;
		try {
			fileReader = new FileReader("data/Languages.properties");
			Properties properties = new Properties();
			properties.load(fileReader);
			return properties.getProperty("language");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * changes the language in the local file language.
	 *
	 * @param givenlanguage the language to change in file
	 */
	public static void writeLanguage(String givenlanguage) {
		try {
			Properties properties = new Properties();
			properties.setProperty("language", givenlanguage);
			properties.store(new FileWriter("data/Languages.properties"), "Auto Generated");
			language=givenlanguage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	
}
