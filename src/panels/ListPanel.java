package panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;

import org.json.JSONException;
import org.json.JSONObject;

import main.LocalJsonWorker;
import main.PropertiesWorker;
import main.Translation;
import managers.PageManager;
import result.Movie;

/**
 * The Class for showing results in list.
 */
public abstract class ListPanel extends ContentPanel{
	private static final long serialVersionUID = 1L;
	
	/** The panel with manages search results in page. */
	protected PageManager pageManager= new PageManager(Color.LIGHT_GRAY);
	
	/** An array which contains 10 panels. */
	protected JPanel[] panelsArray;
	
	/** An array which contains 10 movie instances. */
	protected Movie[] moviesArray;
	
	/** The result in json format. */
	protected JSONObject json;
	
	/** The panel with contains result list. */
	protected JPanel mainPanel = new JPanel();
	
	/** The layout of the main panel. */
	protected GroupLayout gl_mainPanelLayout;
	
	/** The scroll pane which contains everything and gives ability to scroll the page. */
	protected JScrollPane scrollPane;
	
	/**
	 * Instantiates a new list panel.
	 *
	 * @param scrollPane the scroll pane of the frame to set as instance.
	 */
	public ListPanel(JScrollPane scrollPane) {
		this.scrollPane=scrollPane;
	}

	/**
	 * Adds functionality to the movie's button.
	 *
	 * @param movie the movie which methods will run
	 */
	public void buttonFunction(Movie movie) {
		try {
			movie.gettingJson();
			movie.showPage(this, scrollPane);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No internet from bottompanel method. showing error image");
			this.removeAll();
			showInternetError();
		}
	}

	/**
	 * Sets the page manager's components and makes it's layout.
	 */
	public void makePageManager() {
		pageManager.removeAll();
		pageManager.setComboBox(new JComboBox<Object>());
		for(int i=1;i<=pageManager.getPageNumber();i++){
			pageManager.getComboBox().addItem(i);
		}
		
		pageManager.getComboBox().addActionListener(new ActionListener() {
				
				/* Shows results from selected pages. Runs pageManaging() method.
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					pageManaging();
				}
			});
		
		pageManager.makeLayout();
	}
	
	/**
	 * Makes 10 panels and adds to the main panel. The panels are not visible until they are used as movie's panel instance.
	 */
	public void makePanels() {
		panelsArray=new JPanel[10];
		for(int i=0;i<panelsArray.length;i++) {
			panelsArray[i]=new JPanel();
			panelsArray[i].setVisible(false);
		}
		
		gl_mainPanelLayout = new GroupLayout(mainPanel);
		gl_mainPanelLayout.setHorizontalGroup(
				gl_mainPanelLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_mainPanelLayout.createSequentialGroup()
						.addGap(150)
						.addGroup(gl_mainPanelLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_mainPanelLayout.createSequentialGroup()
								.addComponent(panelsArray[0], GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
								.addGap(180)
							)
							.addGroup(gl_mainPanelLayout.createSequentialGroup()
								.addComponent(panelsArray[1], GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
								.addGap(180)
							)
							.addGroup(gl_mainPanelLayout.createSequentialGroup()
								.addComponent(panelsArray[2], GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
								.addGap(180)
							)
							.addGroup(gl_mainPanelLayout.createSequentialGroup()
								.addComponent(panelsArray[3], GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
								.addGap(180)
							)
							.addGroup(gl_mainPanelLayout.createSequentialGroup()
								.addComponent(panelsArray[4], GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
								.addGap(180)
							)
							.addGroup(gl_mainPanelLayout.createSequentialGroup()
								.addComponent(panelsArray[5], GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
								.addGap(180)
							)
							.addGroup(gl_mainPanelLayout.createSequentialGroup()
								.addComponent(panelsArray[6], GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
								.addGap(180)
							)
							.addGroup(gl_mainPanelLayout.createSequentialGroup()
								.addComponent(panelsArray[7], GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
								.addGap(180)
							)
							.addGroup(gl_mainPanelLayout.createSequentialGroup()
								.addComponent(panelsArray[8], GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
								.addGap(180)
							)
							.addGroup(gl_mainPanelLayout.createSequentialGroup()
								.addComponent(panelsArray[9], GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
								.addGap(180)
							)
						)
							
					)	
			);
			gl_mainPanelLayout.setVerticalGroup(
				gl_mainPanelLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_mainPanelLayout.createSequentialGroup()
						.addGap(10)
						.addComponent(panelsArray[0], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(20)                                              
						.addComponent(panelsArray[1], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(20)                                               
						.addComponent(panelsArray[2], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(20)                                               
						.addComponent(panelsArray[3], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(20)                                               
						.addComponent(panelsArray[4], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(20)                                               
						.addComponent(panelsArray[5], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(20)                                               
						.addComponent(panelsArray[6], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(20)                                               
						.addComponent(panelsArray[7], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(20)                                               
						.addComponent(panelsArray[8], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(20)                                               
						.addComponent(panelsArray[9], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(5)
					)
			);
			mainPanel.setLayout(gl_mainPanelLayout);
	}
	
	/**
	 * Makes the movie's panel and adds functionality to buttons.
	 */
	public void showPanels() {
		try {
			moviesArray = new Movie[json.getJSONArray("Search").length()];
			for(int i=0;i<moviesArray.length;i++) {
				moviesArray[i]=new Movie();
				Movie movie = moviesArray[i];
				movie.setTitle(json.getJSONArray("Search").getJSONObject(i).getString("Title").toString());
				movie.setYear(json.getJSONArray("Search").getJSONObject(i).getString("Year").toString());
				movie.setImdbID(json.getJSONArray("Search").getJSONObject(i).getString("imdbID").toString());
				movie.setType(json.getJSONArray("Search").getJSONObject(i).getString("Type").toString());
				movie.setPoster(json.getJSONArray("Search").getJSONObject(i).getString("Poster").toString());
				
				movie.movieJson.put("Title", movie.getTitle());
				movie.movieJson.put("Year", movie.getYear());
				movie.movieJson.put("imdbID", movie.getImdbID());
				movie.movieJson.put("Type", movie.getType());
				movie.movieJson.put("Poster", movie.getPoster());
				
				movie.setIsFavourite(LocalJsonWorker.checkInJson(movie.movieJson));
				
				JSONObject json = movie.movieJson;
				
				movie.setPanel(panelsArray[i]);
				movie.getPanel().setVisible(true);
				Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);
				movie.getPanel().setBorder(border1);
				movie.getPanel().setBackground(Color.WHITE);
				movie.getPanel().addMouseListener(new MouseListener() {
					public void mouseReleased(MouseEvent e) {
					}
					public void mousePressed(MouseEvent e) {
					}
					
					/* Reset the panel's background if the mouse exited the panel.
					 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
					 */
					public void mouseExited(MouseEvent e) {
						movie.getPanel().setBackground(Color.WHITE);
					}
					
					/* Change the panel's background and cursor style when mouse entered the panel.
					 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
					 */
					public void mouseEntered(MouseEvent e) {
						movie.getPanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						movie.getPanel().setBackground(Color.decode("#e0e0e0"));
					}
					
					/* Runs buttonFunction method if clicked.
					 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
					 */
					public void mouseClicked(MouseEvent e) {
						buttonFunction(movie);
						movie.getPanel().setBackground(Color.WHITE);
					}
				});
				
				movie.setLabel(new JLabel("<html><p>"+movie.getTitle().replace("–", "-")+", "+Translation.YEAR.getTranslation(PropertiesWorker.language)+" "+movie.getYear().replace("–", "-")+"</p></html>"));
				movie.setButton(new JButton("<html><p>"+Translation.DETAILS.getTranslation(PropertiesWorker.language)+"</p></html>"));
				
				movie.getButton().addActionListener(new ActionListener() {
					
					/* Runs buttonFunction method if clicked
					 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
					 */
					public void actionPerformed(ActionEvent e) {
						buttonFunction(movie);
					}
				});
				
				movie.setAddFavButtonInList(new JLabel());
				BufferedImage image1 = ImageIO.read(new File("icons/addFavIcon.png"));
				Image newImg1= image1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				movie.getAddFavButtonInList().setIcon(new ImageIcon(newImg1));
				movie.getAddFavButtonInList().setHorizontalAlignment(SwingConstants.RIGHT);
				movie.getAddFavButtonInList().addMouseListener(new MouseAdapter() {
					
					/* Makes the movie favorite and changes the star color.
					 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
					 */
					public void mouseClicked(MouseEvent arg0) {
						System.out.println("Added to favourites");
						LocalJsonWorker.writeJson(json);
						movie.setIsFavourite(true);
						movie.refreshInList(movie.getRemoveFavButtonInList());
					}
					
					/* Change the cursor style.
					 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
					 */
					public void mouseEntered(MouseEvent arg0) {
						movie.getAddFavButtonInList().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
				});
				
				
				movie.setRemoveFavButtonInList(new JLabel());
				BufferedImage image2 = ImageIO.read(new File("icons/removeFavIcon.png"));
				Image newImg2= image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				movie.getRemoveFavButtonInList().setIcon(new ImageIcon(newImg2));
				movie.getRemoveFavButtonInList().setHorizontalAlignment(SwingConstants.RIGHT);
				movie.getRemoveFavButtonInList().addMouseListener(new MouseAdapter() {
					
					/* Makes the movie non-favorite
					 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
					 */
					public void mouseClicked(MouseEvent arg0) {
						System.out.println("Removed from favourites");
						LocalJsonWorker.removeJson(json);
						movie.setIsFavourite(false);
						movie.refreshInList(movie.getAddFavButtonInList());
					}
					
					/* Change the cursor style.
					 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
					 */
					public void mouseEntered(MouseEvent arg0) {
						movie.getRemoveFavButtonInList().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
				});
				
				
				if(movie.getIsFavourite()) {
					movie.refreshInList(movie.getRemoveFavButtonInList());
				}
				else {
					movie.refreshInList(movie.getAddFavButtonInList());
				}
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * Customizes the main panel layout.
	 */
	public abstract void makeMainPanel();
	
	/**
	 * Shows other page results in the main panel.
	 */
	public abstract void pageManaging();
	
	/**
	 * Shows Internet error picture.
	 */
	public abstract void showInternetError();

	/**
	 * Gets the result from LocalJsonWorker class in json format.
	 *
	 * @param page method returns different results depending on the page
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract void gettingJson(int page) throws IOException;
	
}
