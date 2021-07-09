package result;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.json.JSONObject;

import main.*;

/**
 * The Class which shows information about the searching movie.
 */
public class Movie extends Result{
	
	private String DVD;
	private String BoxOffice;
	private String Production;
	private String Website;

	/** The small panel of the movie in the list. */
	private JPanel panel;
	
	/** The label which shows only title and year of the movie in the small panel. */
	private JLabel label;
	
	/** The details button. If clicked goes to the description page.*/
	private JButton button;
	
	/** The movie poster label in the small panel. */
	private JLabel iconLab;
	
	/** The star button for adding the movie to the favorites list without opening description page. */
	public JLabel addFavButtonInList;
	
	/** The star button for removing the movie from the favorites list without opening description page.  */
	public JLabel removeFavButtonInList;
	
	/** The details page. Show all information about the movie. It has left and right sides. */
	private JPanel page;
	
	/** The description page layout. */
	private GroupLayout gl_page;
	
	/** The left panel. Holds the movie picture and buttons. */
	private JPanel left;
	
	/** The layout of the left panel. */
	private GroupLayout gl_left;
	
	/** The movie poster label. */
	private JLabel pictureLab;
	
	/** The movie title label. */
	private JLabel titleLab;
	
	/** The button for going back. If clicked shows the list. */
	private JButton backButton;
	
	/** The button for opening the movie's information in imdb website. */
	private JButton webButton;
	
	/** The panel which holds star buttons. */
	private JPanel favPanel;
	
	/** The star button for adding the movie to the favorites list. */
	private JLabel addFavButton;
	
	/** The star button for removing the movie from the favorites list. */
	private JLabel removeFavButton;
	
	/** The right panel. Holds a table and labels. */
	private JPanel right;
	
	/** The layout of the right panel. */
	private GroupLayout gl_right;
	
	/** Shows plot of the movie. */
	private JLabel plotLab;
	
	/** Shows actors' names of the movie. */
	private JLabel actorsLab;
	
	/** Shows writer's name of the movie. */
	private JLabel writerLab;
	
	/** The word website */
	private JLabel webLab;
	
	/** Shows the movie's website */
	private JLabel websiteLab;
	
	/** The table with all information. */
	private JTable table;
	
	/** The json object of the movie. Holds info about it. */
	public JSONObject movieJson = new JSONObject();
	
	/** The movie's favority. For changing the star buttons.*/
	private boolean isFavourite;
	
	/** The query got from json finder class. */
	private String query2;
	
	/** The movie's result in json format. Holds only one movie's information. */
	private JSONObject secondJson;
	
	
	/** The scroll pane of the table. */
	private JScrollPane scrollPane_table;
	
	
	/**
	 * Opening the description page and showing all information about the movie.
	 *
	 * @param givenPanel the panel for going back to it
	 * @param scrollPane the scroll pane to set view port to the given panel
	 */
	public void showPage(JPanel givenPanel, JScrollPane scrollPane) {
		try {
			Rated=secondJson.get("Rated").toString();
			Released=secondJson.get("Released").toString();
			Runtime=secondJson.get("Runtime").toString();
			Genre=secondJson.get("Genre").toString();
			Director=secondJson.get("Director").toString();
			Writer=secondJson.get("Writer").toString();
			Actors=secondJson.get("Actors").toString();
			Plot=secondJson.get("Plot").toString();
			Language=secondJson.get("Language").toString();
			Country=secondJson.get("Country").toString();
			Awards=secondJson.get("Awards").toString();
			Metascore=secondJson.get("Metascore").toString();
			imdbRating=secondJson.get("imdbRating").toString();
			imdbVotes=secondJson.get("imdbVotes").toString();
			DVD=secondJson.get("DVD").toString();
			BoxOffice=secondJson.get("BoxOffice").toString();
			Production=secondJson.get("Production").toString();
			Website=secondJson.get("Website").toString();
			
			actorsLab = new JLabel("<html><h3>"+Translation.ACTORS.getTranslation(PropertiesWorker.language)+"</h3><p>"+Actors+"</p></html>");
			actorsLab.setVerticalAlignment(SwingConstants.TOP);
			
			plotLab=new JLabel("<html><h3>"+Translation.PLOT.getTranslation(PropertiesWorker.language)+"</h3><p>"+Plot+"</p></html>");
			plotLab.setVerticalAlignment(SwingConstants.TOP);
			
			writerLab=new JLabel("<html><h3>"+Translation.WRITER.getTranslation(PropertiesWorker.language)+"</h3><p>"+Writer+"</p></html>");
			writerLab.setVerticalAlignment(SwingConstants.TOP);
			
			
			webLab = new JLabel("<html><h3>"+Translation.WEBSITE.getTranslation(PropertiesWorker.language)+"</h3></html>");
			
			websiteLab = new JLabel(Website);
			
			pictureLab = new JLabel();
			pictureLab.setVerticalAlignment(SwingConstants.TOP);
			pictureLab.addMouseListener(new MouseListener() {
				
				/* Changes the cursor style when mouse entered the label.
				 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
				 */
				public void mouseEntered(MouseEvent e) {
					pictureLab.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				
				/* Opens the movie description web page when clicked the poster picture.
				 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked(MouseEvent e) {
					try {
						openWebpage(new URI("https://www.imdb.com/title/"+imdbID));
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
				public void mouseExited(MouseEvent e) {
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent arg0) {
				}
			});
			String pictureURL = Poster;
			if(pictureURL.equals("N/A")) {
				pictureLab.setIcon(new ImageIcon("icons/404PosterNotFound.jpg"));
			}else {
                try {
                	URL url = new URL(pictureURL);
                    BufferedImage image = ImageIO.read(url);
                	//java.awt.Image newImg= image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                    pictureLab.setIcon(new ImageIcon(image));   // new ImageIcon(newImg)
                } catch (Exception e1) {
                	pictureLab.setIcon(new ImageIcon("icons/404PosterNotFound.jpg"));
				}
                
			}	
			
			titleLab=new JLabel("<html><h3>"+Title+"</h3></html>");
			titleLab.setHorizontalAlignment(SwingConstants.CENTER);
			
			backButton=new JButton(Translation.BACK.getTranslation(PropertiesWorker.language));
			backButton.addActionListener(new ActionListener() {
				
				/* Moves back.
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent arg0) {
					page.removeAll();
					scrollPane.setViewportView(givenPanel);
					
				}
			});
			
			webButton=new JButton(Translation.WEBBUTTON.getTranslation(PropertiesWorker.language));
			webButton.addActionListener(new ActionListener() {
				
				/* Opens the movie description web page when clicked the button.
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent arg0) {
					try {
						openWebpage(new URI("https://www.imdb.com/title/"+imdbID));
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			});
		
			addFavButton=new JLabel();
			BufferedImage image1 = ImageIO.read(new File("icons/addFavIcon.png"));
			Image newImg1= image1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			addFavButton.setIcon(new ImageIcon(newImg1));
			addFavButton.addMouseListener(new MouseAdapter() {
				
				/* Changes the star color and adds movie to favorites list. 
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked(MouseEvent arg0) {
					System.out.println("Added to favourites");
					LocalJsonWorker.writeJson(movieJson);
					isFavourite=true;
					refresh(removeFavButton,removeFavButtonInList);
				}
				
				/* Change the cursor style.
				 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
				 */
				public void mouseEntered(MouseEvent arg0) {
					addFavButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			});
			
			
			removeFavButton=new JLabel();
			BufferedImage image2 = ImageIO.read(new File("icons/removeFavIcon.png"));
			Image newImg2= image2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			removeFavButton.setIcon(new ImageIcon(newImg2));
			removeFavButton.addMouseListener(new MouseAdapter() {
				
				/* Changes the star color and removes the movie from favorites list.
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked(MouseEvent arg0) {
					System.out.println("Removed from favourites");
					LocalJsonWorker.removeJson(movieJson);
					isFavourite=false;
					refresh(addFavButton, addFavButtonInList);
				}
				
				/* Change the cursor style.
				 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
				 */
				public void mouseEntered(MouseEvent arg0) {
					removeFavButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			});
			
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{Translation.TITLE.getTranslation(PropertiesWorker.language), Title},
					{Translation.YEAR.getTranslation(PropertiesWorker.language), Year},
					{Translation.RATED.getTranslation(PropertiesWorker.language), Rated},
					{Translation.RELEASED.getTranslation(PropertiesWorker.language), Released},
					{Translation.RUNTIME.getTranslation(PropertiesWorker.language), Runtime},
					{Translation.GENRE.getTranslation(PropertiesWorker.language), Genre},
					{Translation.DIRECTOR.getTranslation(PropertiesWorker.language), Director},
					{Translation.LANGUAGE.getTranslation(PropertiesWorker.language), Language},
					{Translation.COUNTRY.getTranslation(PropertiesWorker.language), Country},
					{Translation.AWARDS.getTranslation(PropertiesWorker.language), Awards},
					{Translation.METASCORE.getTranslation(PropertiesWorker.language), Metascore},
					{Translation.IMDBRATING.getTranslation(PropertiesWorker.language), imdbRating},
					{Translation.IMDBVOTES.getTranslation(PropertiesWorker.language), imdbVotes},
					{"imdbID", imdbID},
					{Translation.TYPE.getTranslation(PropertiesWorker.language), Type},
					{"DVD", DVD},
					{Translation.BOXOFFICE.getTranslation(PropertiesWorker.language), BoxOffice},
					{Translation.PRODUCTION.getTranslation(PropertiesWorker.language), Production},
				},
				new String[] {
						" ", " "
				}
			));
			
			table.setBorder(new LineBorder(new Color(0, 0, 0)));
			table.setBackground(Color.WHITE);
			table.getColumnModel().getColumn(0).setPreferredWidth(120);
			table.getColumnModel().getColumn(1).setPreferredWidth(400);
			table.setEnabled(false);
			table.setAutoCreateRowSorter(true);
			
			if(!Website.equals("N/A")) {
				websiteLab.addMouseListener(new MouseAdapter() {
					
					/* Open the movie website.
					 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
					 */
					public void mouseClicked(MouseEvent e) {
		            	try {
							openWebpage(new URI(Website));
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
			            
					}
					
					/* Change the text format.
					 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
					 */
					public void mouseEntered(MouseEvent e) {
						websiteLab.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			            websiteLab.setText("<html><u>"+Website+"</u></html>");
					}
					
					/* Change the text format.
					 * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
					 */
					public void mouseExited(MouseEvent e) {
		            	websiteLab.setText("<html><p>"+Website+"</p></html>");
		            }
					
					
				});
			}
			scrollPane_table = new JScrollPane();
			scrollPane_table.setViewportView(table);
			
			favPanel=new JPanel();
			
			if(isFavourite) {						
				refresh(removeFavButton,removeFavButtonInList);
			}
			else{
				refresh(addFavButton, addFavButtonInList);
			}
				
			
			left = new JPanel();
			
			right = new JPanel();
			right.setBackground(Color.WHITE);
			
			page=new JPanel();
			
			makeLayout();
			scrollPane.setViewportView(page);
			
		
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Getting the movie specific information in json format from json finder class.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void gettingJson() throws IOException {
		try {
			query2=imdbID;
			secondJson=JsonFinder.readJson(query2,2,0,Year);
			System.out.println(secondJson.toString());
		} catch (IOException e) {
			System.out.println("No Internet conection from Movie class");
			throw new IOException();
		}
	}
	
	/**
	 * Customizes the movie description page layout.
	 */
	public void makeLayout() {
		gl_left = new GroupLayout(left);
		gl_left.setHorizontalGroup(
				gl_left.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_left.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_left.createParallelGroup()
						.addComponent(pictureLab, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_left.createSequentialGroup()
							.addComponent(titleLab, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)													
						)
						.addGroup(gl_left.createSequentialGroup()
							.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(webButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(favPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						)
					)
					.addGap(20)
				)
		);
		gl_left.setVerticalGroup(
			gl_left.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_left.createSequentialGroup()
				.addGap(15)
				.addComponent(pictureLab, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)
				.addGap(5)
				.addComponent(titleLab, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
				.addGap(35)
				.addGroup(gl_left.createParallelGroup(Alignment.BASELINE)
					.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(webButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(favPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
				)
			)
		);
		left.setLayout(gl_left);
		
		gl_right = new GroupLayout(right);
		gl_right.setHorizontalGroup(
			gl_right.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_right.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_right.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_table, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addComponent(plotLab, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addComponent(actorsLab, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addComponent(writerLab, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_right.createSequentialGroup()
							.addComponent(webLab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(websiteLab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						)
					).addGap(20)
				)
				
		);
		gl_right.setVerticalGroup(
			gl_right.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_right.createSequentialGroup()
					.addGap(15)
					.addComponent(scrollPane_table, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(plotLab, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(actorsLab, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(writerLab, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					
					.addGroup(gl_right.createParallelGroup(Alignment.TRAILING)
						.addComponent(webLab, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(websiteLab, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					)
					.addGap(5)
				)
		);
		right.setLayout(gl_right);
		
		gl_page = new GroupLayout(page);
		gl_page.setHorizontalGroup(
			gl_page.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_page.createSequentialGroup()
					.addComponent(left, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(right, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
		);
		gl_page.setVerticalGroup(
			gl_page.createParallelGroup(Alignment.TRAILING)
				.addComponent(left, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
				.addComponent(right, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
		);
		page.setLayout(gl_page);
	}
	
	/**
	 * Changes the favorite button in the movie description page.
	 *
	 * @param givenButton the button for changing to it
	 * @param givenButtonInList the button for changing to it in list
	 */
	public void refresh(JLabel givenButton, JLabel givenButtonInList) {
		favPanel.removeAll();
		GroupLayout gl_favPanel=new GroupLayout(favPanel);
		gl_favPanel.setHorizontalGroup(
				gl_favPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(givenButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
		);
		gl_favPanel.setVerticalGroup(
				gl_favPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(givenButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
		);
		favPanel.setLayout(gl_favPanel);
		refreshInList(givenButtonInList);
	}
	
	/**
	 * Changes the favorite button in list.
	 *
	 * @param givenButton the button for changing to it
	 */
	public void refreshInList(JLabel givenButton) {
		panel.removeAll();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(givenButton, GroupLayout.DEFAULT_SIZE, 30,  GroupLayout.DEFAULT_SIZE)
					.addGap(10)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(25)
				)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 50, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(givenButton, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					).addContainerGap()
				)
		);
		panel.setLayout(gl_panel);
	}
	
	/**
	 * Open the given website.
	 *
	 * @param uri the given URL to open
	 * @return true, if successful
	 */
	public static boolean openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	
	
	
	
	
	public JPanel getPage() {
		return page;
	}
	public void setPage(JPanel page) {
		this.page = page;
	}
	public JPanel getLeft() {
		return left;
	}
	public void setLeft(JPanel left) {
		this.left = left;
	}
	public JButton getBackButton() {
		return backButton;
	}
	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}
	public JPanel getRight() {
		return right;
	}
	public void setRight(JPanel right) {
		this.right = right;
	}
	public JLabel getPictureLab() {
		return pictureLab;
	}
	public void setPictureLab(JLabel pictureLab) {
		this.pictureLab = pictureLab;
	}
	public JLabel getplotLab() {
		return plotLab;
	}
	public void setplotLab(JLabel plotLab) {
		this.plotLab = plotLab;
	}
	public JButton getbackButton() {
		return backButton;
	}
	public void setbackButton(JButton backButton) {
		this.backButton = backButton;
	}
	public String getQuery2() {
		return query2;
	}
	public void setQuery2(String query2) {
		this.query2 = query2;
	}
	public JSONObject getSecondJson() {
		return secondJson;
	}
	public void setSecondJson(JSONObject secondJson) {
		this.secondJson = secondJson;
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	public JLabel getIconLab() {
		return iconLab;
	}
	public void setIconLab(JLabel iconLab) {
		this.iconLab = iconLab;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public JButton getButton() {
		return button;
	}
	public void setButton(JButton button) {
		this.button = button;
	}
	public JLabel getPlotLab() {
		return plotLab;
	}
	public void setPlotLab(JLabel plotLab) {
		this.plotLab = plotLab;
	}
	public JLabel getActorsLab() {
		return actorsLab;
	}
	public void setActorsLab(JLabel actorsLab) {
		this.actorsLab = actorsLab;
	}
	public JLabel getWriterLab() {
		return writerLab;
	}
	public void setWriterLab(JLabel writerLab) {
		this.writerLab = writerLab;
	}
	public JLabel getTitleLab() {
		return titleLab;
	}
	public void setTitleLab(JLabel titleLab) {
		this.titleLab = titleLab;
	}
	public JLabel getAddFavButton() {
		return addFavButton;
	}
	public void setAddFavButton(JLabel addFavButton) {
		this.addFavButton = addFavButton;
	}
	public JLabel getRemoveFavButton() {
		return removeFavButton;
	}
	public void setRemoveFavButton(JLabel removeFavButton) {
		this.removeFavButton = removeFavButton;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public boolean getIsFavourite() {
		return isFavourite;
	}
	public void setIsFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	public JLabel getAddFavButtonInList() {
		return addFavButtonInList;
	}
	public void setAddFavButtonInList(JLabel addFavButtonInList) {
		this.addFavButtonInList = addFavButtonInList;
	}
	public JLabel getRemoveFavButtonInList() {
		return removeFavButtonInList;
	}
	public void setRemoveFavButtonInList(JLabel removeFavButtonInList) {
		this.removeFavButtonInList = removeFavButtonInList;
	}
	
	
	public String getDVD() {
		return DVD;
	}
	public void setDVD(String dVD) {
		DVD = dVD;
	}
	public String getBoxOffice() {
		return BoxOffice;
	}
	public void setBoxOffice(String boxOffice) {
		BoxOffice = boxOffice;
	}
	public String getProduction() {
		return Production;
	}
	public void setProduction(String production) {
		Production = production;
	}
	public String getWebsite() {
		return Website;
	}
	public void setWebsite(String website) {
		Website = website;
	}
	
}
