package panels;

import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;

import org.json.JSONException;

import main.JsonFinder;

/**
 * The Class of the panel which appears in the bottom side of the frame and shows search results.
 */
public class Bottom extends ListPanel{
	
	private static final long serialVersionUID = 1L;
	
	/** The query from the top panel's text field. */
	private String text;
	
	/** The year from  the top panel's year field. */
	private String year;
	
	/**
	 * Instantiates the bottom panel.
	 *
	 * @param givenScrollPane is the the scroll pane from Frame class for adding elements to it
	 */
	public Bottom(JScrollPane givenScrollPane) {
		super(givenScrollPane);
		this.scrollPane=givenScrollPane;
	}
	
	/* Customizes the layout of the panel
	 * @see panels.ContentPanel#makeLayout()
	 */
	public void makeLayout() {
		this.removeAll();
		gl_layout = new GroupLayout(this);
		gl_layout.setHorizontalGroup(
				gl_layout.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_layout.createParallelGroup(Alignment.LEADING)
						.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
					)
					.addGroup(gl_layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pageManager, GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
					)
			);
			gl_layout.setVerticalGroup(
				gl_layout.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_layout.createSequentialGroup()
						.addComponent(mainPanel, 578, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)	
						.addComponent(pageManager, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					)
			);
			this.setLayout(gl_layout);
			makeMainPanel();			
	}

	/* Adds movie panels to the main panel
	 * @see panels.ListPanel#makeMainPanel()
	 */
	public void makeMainPanel() {
		makePanels();
		showPanels();
	}
	
	
	/**
	 * Changes the layout of the page after selecting comboBox item of the page manager.
	 */
	public void pageManaging(){
		try {
			mainPanel.removeAll();
			gettingJson(pageManager.getComboBox().getSelectedIndex() + 1);
			if(json.get("Response").toString().equals("False")) {
				System.out.println(json.get("Error").toString());
				showErrorImage();
			}
			else {
				pageManager.setPageNumber((int)Math.ceil((double)Integer.parseInt(json.get("totalResults").toString())/10));
				System.out.println(pageManager.getPageNumber());
				makeMainPanel();
			}
		
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			showInternetError();
		} 
	}
	
	/**
	 * Gets the json from JsonFinder class.
	 *
	 * @param page for filtering the result's page
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void gettingJson(int page) throws IOException {
		json=JsonFinder.readJson(text,1,page, year);
		System.out.println(json.toString());
	}
	
	
	/**
	 * Indicates that there is no Internet connection and shows the Internet error image if catches I/O Exception. 
	 */
	public void showInternetError() {
		JLabel pictureLab = new JLabel();
		pictureLab.setIcon(new ImageIcon("icons/noInternetConnection.jpg"));
		
		gl_layout = new GroupLayout(this);
		gl_layout.setHorizontalGroup(
			gl_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layout.createSequentialGroup()
					.addGap(150)
					.addComponent(pictureLab, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE)
				)
		);
		gl_layout.setVerticalGroup(
			gl_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layout.createSequentialGroup()
					.addGap(50)
					.addComponent(pictureLab, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				)
		);
		this.setLayout(gl_layout);
	}
	
	/**
	 * Shows the error image if doesn't finds any result. 
	 */
	public void showErrorImage() {
		JLabel pictureLab = new JLabel();
		pictureLab.setIcon(new ImageIcon("icons/oops.jpg"));
		
		gl_layout = new GroupLayout(this);
		gl_layout.setHorizontalGroup(
			gl_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layout.createSequentialGroup()
					.addGap(80)
					.addComponent(pictureLab, GroupLayout.PREFERRED_SIZE, 834, GroupLayout.PREFERRED_SIZE)
				)
		);
		gl_layout.setVerticalGroup(
			gl_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layout.createSequentialGroup()
					.addGap(100)
					.addComponent(pictureLab, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(100)
				)
		);
		this.setLayout(gl_layout);
	}
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
