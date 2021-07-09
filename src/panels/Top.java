package panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import org.json.JSONException;

import main.LanguageChanger;
import main.PropertiesWorker;
import main.Translation;

/**
 * The Class of the top panel in the frame.
 */
public class Top extends ContentPanel implements LanguageChanger{
	private static final long serialVersionUID = 1L;
	
	/** The home button instance. If clicked shows home page */
	private JButton homeButton = new JButton("<html><p>"+Translation.HOME.getTranslation(PropertiesWorker.language)+"</p></html>");
	
	/** The favorites button instance. If clicked shows favorites panel. */
	private JButton favouritesButton = new JButton("<html><p>"+Translation.FAVOURITES.getTranslation(PropertiesWorker.language)+"</p></html>");
	
	/** The text field in the top panel for typing a movie name. */
	private JTextField textField = new JTextField();
	
	/** The text field near the name field for typing the movie year for filtering results. */
	private JTextField yearField = new JTextField();
	
	/** The search button. If clicked shows result. */
	private JButton button = new JButton("<html><p>"+Translation.SEARCH.getTranslation(PropertiesWorker.language)+"</p></html>");
	
	/**
	 * Instantiates a new top panel.
	 *
	 * @param home the home panel instance
	 * @param bottom the bottom panel instance
	 * @param favourites the favorites panel instance
	 * @param scrollPane the scroll pane to set view port to one of the panels.
	 */
	public Top(Home home, Bottom bottom, Favourites favourites, JScrollPane scrollPane) {
		this.setBackground(Color.LIGHT_GRAY);
		addButtonFunctions(home, bottom, favourites, scrollPane);
		makeLayout();
	}
	
	/* Customizes the top panel's layout.
	 * @see panels.ContentPanel#makeLayout()
	 */
	public void makeLayout() {
		gl_layout = new GroupLayout(this);
		gl_layout.setHorizontalGroup(
			gl_layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_layout.createSequentialGroup()
					.addGap(30)
					.addComponent(homeButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(yearField, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(favouritesButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
				)
		);
		gl_layout.setVerticalGroup(
			gl_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_layout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(homeButton)
						.addComponent(favouritesButton)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addComponent(yearField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					)
					.addContainerGap()
				)
		);
		this.setLayout(gl_layout);
	}
	
	/**
	 * Adds functionality to the menu buttons.
	 *
	 * @param home the home panel to show when clicked
	 * @param bottom the bottom panel to show when clicked
	 * @param favourites the favorites panel to show when clicked
	 * @param scrollPane the scroll pane which shows the panel
	 */
	public void addButtonFunctions(Home home, Bottom bottom, Favourites favourites, JScrollPane scrollPane) {
		homeButton.addActionListener(new ActionListener() {
			
			/* Goes to the home page.
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				bottom.removeAll();
				scrollPane.setViewportView(home);		
			}
		});
		
		favouritesButton.addActionListener(new ActionListener() {
			
			/* Goes to the favorites page.
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					favourites.pageManager.removeAll();
					favourites.mainPanel.removeAll();
					favourites.removeAll();
					scrollPane.setViewportView(favourites);
					favourites.gettingJson(favourites.pageManager.getCurrentPage());
					
					favourites.pageManager.setPageNumber((int)Math.ceil((double)Integer.parseInt(favourites.json.get("totalResults").toString())/10));
					System.out.println(favourites.pageManager.getPageNumber());
					favourites.makePageManager();
					favourites.makeLayout();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		textField.setToolTipText(Translation.SEARCHBYMOVIENAME.getTranslation(PropertiesWorker.language));
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			
			/* Shows results.
			 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					try {
						bottom.pageManager.removeAll();
						bottom.mainPanel.removeAll();
						bottom.removeAll();
						scrollPane.setViewportView(bottom);
						bottom.setText(textField.getText().replace(" ", "%20"));
						bottom.setYear(yearField.getText());
						bottom.gettingJson(bottom.pageManager.getCurrentPage());
						
						if(bottom.json.get("Response").toString().equals("False")) {
							System.out.println(bottom.json.get("Error").toString());
							bottom.showErrorImage();
						}
						else {
							bottom.pageManager.setPageNumber((int)Math.ceil((double)Integer.parseInt(bottom.json.get("totalResults").toString())/10));
							System.out.println(bottom.pageManager.getPageNumber());
							
							bottom.makePageManager();
							bottom.makeLayout();
						}
					} catch (JSONException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						bottom.showInternetError();
					} 
				}
			}
		});
		
		yearField.setToolTipText(Translation.FILTERBYYEAR.getTranslation(PropertiesWorker.language));
		yearField.addKeyListener(new KeyAdapter() {
			
			/* Shows results.
			 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					try {
						bottom.pageManager.removeAll();
						bottom.mainPanel.removeAll();
						bottom.removeAll();
						scrollPane.setViewportView(bottom);
						bottom.setText(textField.getText().replace(" ", "%20"));
						bottom.setYear(yearField.getText());
						bottom.gettingJson(bottom.pageManager.getCurrentPage());
						
						if(bottom.json.get("Response").toString().equals("False")) {
							System.out.println(bottom.json.get("Error").toString());
							bottom.showErrorImage();
						}
						else {
							bottom.pageManager.setPageNumber((int)Math.ceil((double)Integer.parseInt(bottom.json.get("totalResults").toString())/10));
							System.out.println(bottom.pageManager.getPageNumber());
							bottom.makePageManager();
							bottom.makeLayout();
						}
					} catch (JSONException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						bottom.showInternetError();
					} 
				}
			}
		});
		
		button.addActionListener(new ActionListener() {
			
			/* Shows results.
			 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {						
				try {
					bottom.pageManager.removeAll();
					bottom.mainPanel.removeAll();
					bottom.removeAll();
					scrollPane.setViewportView(bottom);
					bottom.setText(textField.getText().replace(" ", "%20"));
					bottom.setYear(yearField.getText());
					bottom.gettingJson(bottom.pageManager.getCurrentPage());
					
					
					if(bottom.json.get("Response").toString().equals("False")) {
						System.out.println(bottom.json.get("Error").toString());
						bottom.showErrorImage();
					}
					else {
						bottom.pageManager.setPageNumber((int)Math.ceil((double)Integer.parseInt(bottom.json.get("totalResults").toString())/10));
						System.out.println(bottom.pageManager.getPageNumber());
						bottom.makePageManager();
						bottom.makeLayout();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e1) {
					bottom.showInternetError();
				} 
			}
		});
		
	}
	
	/* Refreshes the components to change the language.
	 * @see main.LanguageChanger#refreshLanguage()
	 */
	public void refreshLanguage() {
		textField.setToolTipText(Translation.SEARCHBYMOVIENAME.getTranslation(PropertiesWorker.language));
		yearField.setToolTipText(Translation.FILTERBYYEAR.getTranslation(PropertiesWorker.language));
		homeButton.setText("<html><p>"+Translation.HOME.getTranslation(PropertiesWorker.language)+"</p></html>");
		button.setText("<html><p>"+Translation.SEARCH.getTranslation(PropertiesWorker.language)+"</p></html>");
		favouritesButton.setText("<html><p>"+Translation.FAVOURITES.getTranslation(PropertiesWorker.language)+"</p></html>");
	}
}
