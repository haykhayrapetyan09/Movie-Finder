package panels;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 * The Class for holding mainly used panels' instances and methods.
 */
public abstract class ContentPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	/** The layout of the panel in frame. */
	protected GroupLayout gl_layout = new GroupLayout(this);
	
	/**
	 * Customizes the panel's layout. Used for overriding.
	 */
	public abstract void makeLayout();
	
}
