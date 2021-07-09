package managers;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import main.LanguageChanger;
import main.PropertiesWorker;
import main.Translation;

/**
 * The Class which manages user searches in favorites list.
 */
public class SearchManager extends Manager implements LanguageChanger{
	
	private static final long serialVersionUID = 1L;

	/** The search text field to type a movie name in it. */
	private JTextField favSearchTextField = new JTextField();
	
	/** The button for searching. If clicked starts searching in the list*/
	private JButton button = new JButton("<html><p>"+Translation.SEARCH.getTranslation(PropertiesWorker.language)+"</p></html>");
	
	/** Refreshes the list if clicked. */
	private JButton refresh = new JButton("<html><p>"+Translation.REFRESH.getTranslation(PropertiesWorker.language)+"</p></html>");
	
	
	/**
	 * Instantiates a new search manager.
	 *
	 * @param givenColor the manager's color to set
	 */
	public SearchManager(Color givenColor) {
		super(givenColor);
	}
	
	/**
	 * Customizes the search manager panel layout.
	 */
	public void makeLayout() {
		gl_layout.setHorizontalGroup(
			gl_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layout.createSequentialGroup()
					.addGap(150)
					.addComponent(favSearchTextField, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(refresh, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
				)
		);
		gl_layout.setVerticalGroup(
			gl_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layout.createSequentialGroup()
					.addGap(10)						
					.addComponent(favSearchTextField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
				)
				.addGroup(gl_layout.createSequentialGroup()
					.addGap(10)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
				)
				.addGroup(gl_layout.createSequentialGroup()
					.addGap(10)
					.addComponent(refresh, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
				)
		);
		this.setLayout(gl_layout);		
	}
	
	/**
	 * Refreshes the language of the search and refresh buttons.
	 */
	public void refreshLanguage() {
		button.setText("<html><p>"+Translation.SEARCH.getTranslation(PropertiesWorker.language)+"</p></html>");
		refresh.setText("<html><p>"+Translation.REFRESH.getTranslation(PropertiesWorker.language)+"</p></html>");
	}
	
	
	
	public JTextField getFavSearchTextField() {
		return favSearchTextField;
	}

	public void setFavSearchTextField(JTextField favSearchTextField) {
		this.favSearchTextField = favSearchTextField;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public JButton getRefresh() {
		return refresh;
	}

	public void setRefresh(JButton refresh) {
		this.refresh = refresh;
	}
	
	
}
