package panels;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;

import main.LanguageChanger;
import main.PropertiesWorker;
import main.Translation;

/**
 * The Class of the panel with information.
 */
public class Home extends ContentPanel implements LanguageChanger{
	
	private static final long serialVersionUID = 1L;
	
	/** The label with text describing the program. */
	private JLabel homeLabel = new JLabel(Translation.ABOUT.getTranslation(PropertiesWorker.language));
	
	/** The API website. */
	private String site="www.omdbapi.com";
	
	/** The site describing text. */
	private JLabel siteTextLabel =new JLabel(Translation.SITETEXTLABEL.getTranslation(PropertiesWorker.language));
	
	/** The API website address label. If clicked goes to the API site */
	private JLabel siteLabel = new JLabel("<html><h3>"+site+"</h3></html>");
	
	/** The imdb picture. */
	private JLabel imdb = new JLabel();
	
	/** The film picture. */
	private JLabel movie = new JLabel();
	
	/** The button which changes the language to English. */
	private JLabel engButton = new JLabel("<html><p>English</p></html>");
	
	/** The button which changes the language to Armenian. */
	private JLabel armButton = new JLabel("<html><p>Հայերեն</p></html>");
	
	/** Just slash. */
	private JLabel slash = new JLabel("/");	
	
	
	/**
	 * Instantiates a new home panel.
	 */
	public Home() {
		homeLabel.setVerticalAlignment(SwingConstants.TOP);
		movie.setVerticalAlignment(SwingConstants.TOP);
		try {
			BufferedImage image1 = ImageIO.read(new File("icons/imdb.jpg"));
			Image newImg1= image1.getScaledInstance(470, 208, Image.SCALE_SMOOTH);
			imdb.setIcon(new ImageIcon(newImg1));
			
			BufferedImage image2 = ImageIO.read(new File("icons/movie.png"));
			Image newImg2= image2.getScaledInstance(256, 256, Image.SCALE_SMOOTH);
			movie.setIcon(new ImageIcon(newImg2));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		siteLabel.addMouseListener(new MouseAdapter() {
			
			/* Opens the API website.
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			public void mouseClicked(MouseEvent arg0) {
				 Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				        try {
				        	URI uri = new URI(site);
				            desktop.browse(uri);
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
				    }
			}
			
			/* Change the cursor style and underline the text
			 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
			 */
			public void mouseEntered(MouseEvent e) {
				siteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				siteLabel.setText("<html><h3><u>"+site+"</u></h3></html>");
            }
			/* Change the text to non-underlined.
			 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
			 */
			public void mouseExited(MouseEvent e) {
				siteLabel.setText("<html><h3>"+site+"</h3></html>");
			}
			
		});
		
		imdb.addMouseListener(new MouseAdapter() {
			
			/* Change the cursor style.
			 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
			 */
			public void mouseEntered(MouseEvent e) {
				imdb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
			/* Open the IMDb website.
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			public void mouseClicked(MouseEvent arg0) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			        try {
			        	URI uri = new URI("www.imdb.com");
			            desktop.browse(uri);
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			    }
			}
		});
		
		makeLayout();
	}
	
	/* Customizes the home panel layout.
	 * @see panels.ContentPanel#makeLayout()
	 */
	public void makeLayout() {
		gl_layout.setHorizontalGroup(
				gl_layout.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_layout.createSequentialGroup()
						.addGap(120)
						.addGroup(gl_layout.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_layout.createSequentialGroup()
									.addComponent(imdb, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)
									.addGap(340)
									.addComponent(engButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(slash, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(armButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							)
							.addGroup(gl_layout.createSequentialGroup()
								.addComponent(homeLabel, GroupLayout.PREFERRED_SIZE, 620, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addGroup(gl_layout.createSequentialGroup()
									.addComponent(movie, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
								)	
							)
							.addGroup(gl_layout.createSequentialGroup()
								.addComponent(siteTextLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(siteLabel, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
							)
							
						)
					)	
			);
			gl_layout.setVerticalGroup(
				gl_layout.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_layout.createSequentialGroup()
						.addGap(20)
						.addGroup(gl_layout.createParallelGroup(Alignment.LEADING)
								.addComponent(imdb, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
								.addComponent(engButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(slash, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(armButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						)
						.addGap(5)
						.addGroup(gl_layout.createParallelGroup(Alignment.LEADING)
							.addComponent(homeLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_layout.createParallelGroup(Alignment.LEADING)
								.addComponent(movie, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
							)
						)
						.addGroup(gl_layout.createParallelGroup(Alignment.LEADING)
							.addComponent(siteTextLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addComponent(siteLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						)
						
					)
			);
			this.setLayout(gl_layout);
	}
	
	/**
	 * Refresh the elements for changing the language.
	 */
	public void refreshLanguage() {
		siteTextLabel.setText(Translation.SITETEXTLABEL.getTranslation(PropertiesWorker.language));
		homeLabel.setText(Translation.ABOUT.getTranslation(PropertiesWorker.language));
	}
	
	public JLabel getEngButton() {
		return engButton;
	}

	public void setEngButton(JLabel engButton) {
		this.engButton = engButton;
	}

	public JLabel getArmButton() {
		return armButton;
	}

	public void setArmButton(JLabel armButton) {
		this.armButton = armButton;
	}
}
