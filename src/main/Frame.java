package main;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import panels.Bottom;
import panels.Favourites;
import panels.Home;
import panels.Top;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The Class of the main Frame.
 */
public class Frame extends JFrame implements LanguageChanger{
	
	private static final long serialVersionUID = 1L;
	
	/** The content pane with contains all elements in it in the Frame. */
	public JPanel contentPane = new JPanel();
	
	/** The scroll pane which contains everything and gives ability to scroll the page. */
	public JScrollPane scrollPane = new JScrollPane();
	
	/** The instance of the home panel. */
	public Home home = new Home();
	
	/** The bottom panel for showing results in it. */
	public Bottom bottom = new Bottom(scrollPane);
	
	/** The favorites panel for showing user's selected favorite movies in the list. */
	public Favourites favourites = new Favourites(scrollPane);
	
	/** The instance of the top panel. */
	public Top top = new Top(home, bottom, favourites, scrollPane);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Instantiates a new frame and adds language changing functionality.
	 */
	public Frame() {
		setTitle("Movie Finder");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("icons/image.png"));
		setBounds(200, 100, 1080, 720);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane)
		);
		contentPane.setLayout(gl_contentPane);
		scrollPane.setColumnHeaderView(top);
		
		home.getEngButton().addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			
			/* Change the text to non-underlined.
			 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
			 */
			public void mouseExited(MouseEvent e) {
				home.getEngButton().setText("<html><p>English</p></html>");
			}
			
			/* Change the cursor style and underline the text in the label.
			 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
			 */
			public void mouseEntered(MouseEvent e) {
				home.getEngButton().setText("<html><u>English</u></html>");
				home.getEngButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			/* Change the language to English and refresh.
			 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
			 */
			public void mouseClicked(MouseEvent e) {
				PropertiesWorker.writeLanguage("English");
				refreshLanguage();
			}
		});
		
		home.getArmButton().addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			/* Change the text to non-underlined.
			 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
			 */
			public void mouseExited(MouseEvent e) {
				home.getArmButton().setText("<html><p>Հայերեն</p></html>");
			}
			/* Change the cursor style and underline the text in the label.
			 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
			 */
			public void mouseEntered(MouseEvent e) {
				home.getArmButton().setText("<html><u>Հայերեն</u></html>");
				home.getArmButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			/* Change the language to Armenian and refresh.
			 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
			 */
			public void mouseClicked(MouseEvent e) {
				PropertiesWorker.writeLanguage("Armenian");
				refreshLanguage();
			}
		});
					
		scrollPane.setViewportView(home);
	}		
	
	/**
	 * Refresh all elements for changing language.
	 */
	public void refreshLanguage() {
		home.refreshLanguage();
		top.refreshLanguage();
		favourites.getSearchManager().refreshLanguage();
		
	}
}

/*for(int i=0;i<favouriteMoviesArray.length;i++) {
favouriteMoviesArray[i]=new Movie();
favouriteMoviesArray[i].setTitle(favouritesJson.getJSONArray("Search").getJSONObject(i).getString("Title").toString());
favouriteMoviesArray[i].setYear(favouritesJson.getJSONArray("Search").getJSONObject(i).getString("Year").toString());
favouriteMoviesArray[i].setPoster(favouritesJson.getJSONArray("Search").getJSONObject(i).getString("Poster").toString());

favouriteMoviesArray[i].setPanel(panelsArray[i]);
favouriteMoviesArray[i].getPanel().setVisible(true);
Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);
favouriteMoviesArray[i].getPanel().setBorder(border1);
favouriteMoviesArray[i].getPanel().setBackground(Color.WHITE);

favouriteMoviesArray[i].setLabel(new JLabel(favouriteMoviesArray[i].getTitle().replace("–", "-")+", Year "+favouriteMoviesArray[i].getYear().replace("–", "-")));

favouriteMoviesArray[i].setButton(new JButton("Details"));
favouriteMoviesArray[i].addButtonFunction(favouritesPanel, scrollPane, yearField.getText());

favouriteMoviesArray[i].setIconLab(new JLabel());
if(favouriteMoviesArray[i].getPoster().equals("N/A")) {
	favouriteMoviesArray[i].getIconLab().setIcon(new ImageIcon("icons/404PosterNotFound.jpg"));
}
else{
	
    try {
    	URL url = new URL(favouriteMoviesArray[i].getPoster());
        BufferedImage image = ImageIO.read(url);
    	java.awt.Image newImg= image.getScaledInstance(100, 200, java.awt.Image.SCALE_SMOOTH);
    	favouriteMoviesArray[i].getIconLab().setIcon(new ImageIcon(newImg));   // new ImageIcon(newImg)
    } catch (Exception e1) {
    	favouriteMoviesArray[i].getIconLab().setIcon(new ImageIcon("icons/404PosterNotFound.jpg"));
	}
}

GroupLayout gl_panel = new GroupLayout(favouriteMoviesArray[i].getPanel());
gl_panel.setHorizontalGroup(
	gl_panel.createParallelGroup(Alignment.LEADING)
		.addGap(0, 500, Short.MAX_VALUE)
		.addGroup(gl_panel.createSequentialGroup()
			.addGap(15)
			.addComponent(favouriteMoviesArray[i].getIconLab(), GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
			.addGap(10)
			.addComponent(favouriteMoviesArray[i].getLabel(), GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
			.addGap(10)
			.addComponent(favouriteMoviesArray[i].getButton(), GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
			.addGap(25))
);
gl_panel.setVerticalGroup(
	gl_panel.createParallelGroup(Alignment.LEADING)
		.addGap(0, 50, Short.MAX_VALUE)
		.addGroup(gl_panel.createSequentialGroup()
			.addContainerGap()
			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
				.addComponent(favouriteMoviesArray[i].getIconLab(), GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
				.addComponent(favouriteMoviesArray[i].getLabel(), GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				.addComponent(favouriteMoviesArray[i].getButton(), GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
			)
		)
);
favouriteMoviesArray[i].getPanel().setLayout(gl_panel);
}*/