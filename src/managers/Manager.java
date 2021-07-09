package managers;

import java.awt.Color;

import panels.ContentPanel;

/**
 * The Class Manager for polymorphism which contains instances and methods for more concrete managers.
 */
public abstract class Manager extends ContentPanel{
	
	private static final long serialVersionUID = 1L;
		
	/**
	 * Instantiates a new manager.
	 *
	 * @param givenColor the color to set as background color
	 */
	public Manager(Color givenColor) {
		this.setBackground(givenColor);
	}
	
}
