package main;

/**
 * The Interface LanguageChanger which has a method for changing it's component's language. Used for implementing.
 */
public interface LanguageChanger {
	
	/**
	 * Refreshes the language of the components. Used by top panel, search manager and frame.
	 */
	void refreshLanguage();
}
