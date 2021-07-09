package panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;

import org.json.JSONException;

import main.LocalJsonWorker;
import main.PropertiesWorker;
import main.Translation;
import managers.SearchManager;

/**
 * The Class of the panel with contains information about user selected favorite movies.
 */
public class Favourites extends ListPanel{
	
	private static final long serialVersionUID = 1L;
	
	/** The instance of manager with helps to search in the list. Appears on the top side of the favorites panel. */
	private SearchManager searchManager = new SearchManager(Color.decode("#e0e0e0"));
	
	/**
	 * Instantiates the favorites panel.
	 *
	 * @param givenScrollPane is the the scroll pane from Frame class for adding elements to it
	 */
	public Favourites(JScrollPane givenScrollPane) {
		super(givenScrollPane);
		this.makeSearchManager();
	}
	
		
	
	
	/**
	 * Makes the layout of the whole panel with managers.
	 */
	public void makeLayout() {
		this.removeAll();
		try {
			if(json.get("totalResults").toString().equals("0")) {
				JLabel label = new JLabel("<html><h2>"+Translation.THELISTEMPTY.getTranslation(PropertiesWorker.language)+"</h2></html>");
				gl_layout = new GroupLayout(this);
				gl_layout.setHorizontalGroup(
						gl_layout.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layout.createSequentialGroup()
							.addGap(230)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						)
				);
				gl_layout.setVerticalGroup(
						gl_layout.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layout.createSequentialGroup()
							.addGap(100)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(100)
						)
				);
				this.setLayout(gl_layout);
			}
			else {
				
				gl_layout = new GroupLayout(this);
				gl_layout.setHorizontalGroup(
						gl_layout.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_layout.createSequentialGroup()
								.addComponent(searchManager, GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
							)
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
								.addComponent(searchManager, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(mainPanel, 538, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)	
								.addComponent(pageManager, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							)
					);
					this.setLayout(gl_layout);
					makeMainPanel();					
				} 
			} catch (JSONException e) {
				e.printStackTrace();
			} 
	}

	/* Customizes the main panel's layout and shows movie instances.
	 * @see panels.ListPanel#makeMainPanel()
	 */
	public void makeMainPanel() {
		try {
			if(json.get("totalResults").toString().equals("0")) {
				JLabel label = new JLabel("<html><h3>"+Translation.SEARCHLISTEMPTY.getTranslation(PropertiesWorker.language)+"</h3></html>");
				gl_mainPanelLayout = new GroupLayout(mainPanel);
				gl_mainPanelLayout.setHorizontalGroup(
						gl_mainPanelLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainPanelLayout.createSequentialGroup()
							.addGap(270)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						)
				);
				gl_mainPanelLayout.setVerticalGroup(
						gl_mainPanelLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainPanelLayout.createSequentialGroup()
							.addGap(100)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(100)
						)
				);
				mainPanel.setLayout(gl_mainPanelLayout);
			}
			else {
				makePanels();
				showPanels();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Shows results from other pages selected in page manager.
	 */
	public void pageManaging() {
		try {
			mainPanel.removeAll();
			gettingJson(pageManager.getComboBox().getSelectedIndex() + 1);
			pageManager.setPageNumber((int)Math.ceil((double)Integer.parseInt(json.get("totalResults").toString())/10));
			System.out.println(pageManager.getPageNumber());
			makeMainPanel();

		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Customizes the search manager panel. This method is used once from the constructor.
	 */
	public void makeSearchManager() {
		searchManager.getFavSearchTextField().addKeyListener(new KeyAdapter() {
			
			/* Shows search results in favorites panel. Calls searchManaging() method.
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					searchManaging();
				}
			}
		});
		
		
		searchManager.getButton().addActionListener(new ActionListener() {
			
			/* Shows search results in favorites panel. Calls searchManaging() method.
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {						
				searchManaging();
			}
		});
		
		searchManager.getRefresh().addActionListener(new ActionListener() {
			
			/* Refreshes the favorites list. Calls refreshing() method.
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("refresh button clicked");
				refreshing();
			}
		});
		
		searchManager.makeLayout();
	}
	
	/**
	 * Shows search results in main panel.
	 */
	public void searchManaging() {
		try {
			mainPanel.removeAll();
			gettingJson(searchManager.getFavSearchTextField().getText(), pageManager.getCurrentPage());
			
			pageManager.setPageNumber((int)Math.ceil((double)Integer.parseInt(this.json.get("totalResults").toString())/10));
			System.out.println(this.pageManager.getPageNumber());
			makePageManager();
			makeMainPanel();
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Gets the json from JsonFinder class.
	 *
	 * @param page for filtering the result's page
	 */
	public void gettingJson(int page) {
		json=LocalJsonWorker.readJson(page);
		System.out.println(json.toString());
	}
	
	
	/**
	 * Gets the json from JsonFinder class.
	 *
	 * @param givenTitle the search query to find in json
	 * @param page the method returns different results depending on the page
	 */
	public void gettingJson(String givenTitle, int page) {
		json=LocalJsonWorker.searchInJson(givenTitle, page);
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
	 * Refreshes the main panel. This method runs when clicked refresh button from search manager.
	 */
	public void refreshing() {
		try {
			System.out.println("refreshing");
			this.removeAll();
			mainPanel.removeAll();
			pageManager.removeAll();
			gettingJson(pageManager.getCurrentPage());
			pageManager.setPageNumber((int)Math.ceil((double)Integer.parseInt(this.json.get("totalResults").toString())/10));
			System.out.println(this.pageManager.getPageNumber());
			makePageManager();
			makeLayout();
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	}
	
	public SearchManager getSearchManager() {
		return searchManager;
	}
	public void setSearchManager(SearchManager searchManager) {
		this.searchManager = searchManager;
	}		
}

