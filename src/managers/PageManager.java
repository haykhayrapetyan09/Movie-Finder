package managers;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.GroupLayout.Alignment;

/**
 * The Class which manages results by pages in the list.
 */
public class PageManager extends Manager{
	
	private static final long serialVersionUID = 1L;
	
	/** Total pages for results. */
	private int pageNumber;
	
	/** The current page. By default is 1 */
	private int currentPage=1;
	
	/** Combo box that gives ability to show and select pages. */
	private JComboBox<Object> comboBox;
		
	/**
	 * Instantiates a new page manager.
	 *
	 * @param givenColor the manager's color to set
	 */
	public PageManager(Color givenColor) {
		super(givenColor);
	}

	/**
	 * Customizes the page manager panel layout.
	 */
	public void makeLayout() {
		gl_layout.setHorizontalGroup(
				gl_layout.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_layout.createSequentialGroup()
						.addGap(150)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					)
			);
			gl_layout.setVerticalGroup(
				gl_layout.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					)
			);
			this.setLayout(gl_layout);
	}
	
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public JComboBox<Object> getComboBox() {
		return comboBox;
	}
	public void setComboBox(JComboBox<Object> comboBox) {
		this.comboBox = comboBox;
	}
}
