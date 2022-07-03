import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.text.StyledEditorKit;

/**
 * @author Jamie Longbottom
 * 
 * Main Class for the Word Processor application.
 * Only contains code directly related to the GUI.
 *
 */
public class Notepad extends JFrame implements Runnable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/***********************************************
	 * Creating Text Area and Frame for Application
	 ***********************************************/
	private static JTextPane textArea;
	private static JFrame frame;
	/***********************************************
	 * Creating Panel and Label
	 ***********************************************/
	private static JPanel panel = new JPanel();
	private static JLabel charCount = new JLabel();
	private static JComboBox<Object> combo;
	/***********************************************
	 * Creating Image Icons for visual effect
	 ***********************************************/
	private final Image logoIMG = Toolkit.getDefaultToolkit().getImage(".\\src\\Images\\font-size.png");
	private final ImageIcon buttonFile = new ImageIcon(".\\src\\Images\\button_file.png");
	private final ImageIcon newFile = new ImageIcon(".\\src\\Images\\new_file.png");
	private final ImageIcon saveFile = new ImageIcon(".\\src\\Images\\save_file.png");
	private final ImageIcon templates = new ImageIcon(".\\src\\Images\\templates.png");
	private final ImageIcon userTemplates = new ImageIcon(".\\src\\Images\\usertemplates.png");
	private final ImageIcon underlineIMG = new ImageIcon(".\\src\\Images\\underline.png");
	private final ImageIcon boldIMG = new ImageIcon(".\\src\\Images\\bold.png");
	private final ImageIcon italicIMG = new ImageIcon(".\\src\\Images\\italic.png");
	private final ImageIcon undoIMG = new ImageIcon(".\\src\\Images\\undo.png");
	private final ImageIcon redoIMG = new ImageIcon(".\\src\\Images\\redo.png");
	/***********************************************
	 * Creating new objects of other classes
	 ***********************************************/
	private static ActionListenerClass alc = new ActionListenerClass();
	private static CopyPaste cp = new CopyPaste();
	private static SaveFile sf = new SaveFile();
	/***********************************************
	 * Constructor containing all GUI related code
	 ***********************************************/
	Notepad() {
		// Set the name for the Application
		super("The Text Generation");
		// Set the text area to the Text Pane "textArea"
		setTextArea(new JTextPane());

		/***********************************************
		 * Creating Menu Bar and Tool Bar Options
		 ***********************************************/
		JMenuBar menuBar = new JMenuBar();
		JMenuBar toolBar = new JMenuBar();

		JMenu menuFile = new JMenu("File");

		JMenu templateFile = new JMenu("Template");

		JButton helpButton = new JButton("Help?");
		helpButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		helpButton.setFocusable(false);
		helpButton.addMouseListener(new MouseAdapter() 
		{
			public void mouseEntered(MouseEvent evt) 
			{
				helpButton.setBackground(Color.lightGray);
			}
			public void mouseExited(MouseEvent evt) 
			{
				helpButton.setBackground(null);
			}
		});

		JMenuItem fileNew = new JMenuItem("New", newFile);
		JMenuItem fileOpen = new JMenuItem("Open", buttonFile);
		JMenuItem fileSave = new JMenuItem("Save", saveFile);

		JMenuItem openTemplate = new JMenuItem("Open Template", templates);
		JMenuItem saveTemplate = new JMenuItem("Save User Template", saveFile);
		JMenuItem openUserTemplate = new JMenuItem("Open User Template", userTemplates);

		// Add Action Listeners from ActionListenerClass
		fileNew.addActionListener(alc);
		fileOpen.addActionListener(alc);
		fileSave.addActionListener(alc);

		openTemplate.addActionListener(alc);
		saveTemplate.addActionListener(alc);
		openUserTemplate.addActionListener(alc);
		helpButton.addActionListener(alc);

		menuFile.add(fileNew);
		menuFile.add(fileOpen);
		menuFile.add(fileSave);

		templateFile.add(openTemplate);
		templateFile.add(saveTemplate);
		templateFile.add(openUserTemplate);

		menuBar.add(menuFile);
		menuBar.add(templateFile);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(helpButton);

		/***********************************************
		 * Creating JToggleButtons for the Tool Bar
		 ***********************************************/
		JToggleButton bold = new JToggleButton(boldIMG);
		bold.setToolTipText("Bold");
		bold.setMargin(new Insets(1,1,1,1));
		bold.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		bold.setFocusable(false);

		JToggleButton italic = new JToggleButton(italicIMG);
		italic.setToolTipText("Italic");
		italic.setMargin(new Insets(1,1,1,1));
		italic.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		italic.setFocusable(false);

		JToggleButton underline = new JToggleButton(underlineIMG);
		underline.setToolTipText("Underline");
		underline.setMargin(new Insets(1,1,1,1));
		underline.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		underline.setFocusable(false);

		// Create Action Listeners for Bold, Italic & Underline buttons
		bold.addActionListener(new StyledEditorKit.BoldAction());
		italic.addActionListener(new StyledEditorKit.ItalicAction());
		underline.addActionListener(new StyledEditorKit.UnderlineAction());

		/***********************************************
		 * Create and add buttons for Undo & Redo
		 ***********************************************/
		JButton undoButton = new JButton("Undo", undoIMG);
		JButton redoButton = new JButton("Redo", redoIMG);

		// add a colour for when the mouse hovers over undo & redo button
		undoButton.addMouseListener(new MouseAdapter() 
		{
			public void mouseEntered(MouseEvent evt) 
			{
				undoButton.setBackground(Color.lightGray);
			}
			public void mouseExited(MouseEvent evt) 
			{
				undoButton.setBackground(null);
			}
		});

		redoButton.setHorizontalTextPosition(SwingConstants.LEFT);
		redoButton.addMouseListener(new MouseAdapter() 
		{
			public void mouseEntered(MouseEvent evt) 
			{
				redoButton.setBackground(Color.lightGray);
			}
			public void mouseExited(MouseEvent evt) 
			{
				redoButton.setBackground(null);
			}
		});

		undoButton.addActionListener(alc);
		undoButton.setFocusable(false);
		undoButton.setMargin(new Insets(5,5,5,5));
		undoButton.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		redoButton.addActionListener(alc);
		redoButton.setFocusable(false);
		redoButton.setMargin(new Insets(5,5,5,5));
		redoButton.setBorder(BorderFactory.createEmptyBorder(5,5,5,15));

		// Get fonts on the current system
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();

		setCombo(new JComboBox<Object>(fonts));
		getCombo().addActionListener(alc);
		// Set initial font to Arial
		getCombo().setSelectedItem("Arial");

		toolBar.add(bold);
		toolBar.add(italic);
		toolBar.add(underline);
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(getCombo());
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(undoButton);
		toolBar.add(redoButton);

		// Register when undoable edits are made to the current document
		textArea.getDocument().addUndoableEditListener(alc.getManager());

		// Create popup menu for Cut, Copy and Paste
		cp.setPopMenu(textArea);

		// Character Count to the JPanel
		panel.add(charCount);

		// Add Window Listener to prompt user to save before exit
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				sf.saveOnExit();
			}
		});

		/***********************************************
		 * Add all above ^^ to the JFrame
		 ***********************************************/
		add(new JScrollPane(textArea));
		add(panel, BorderLayout.SOUTH);
		add(toolBar, BorderLayout.NORTH);
		setJMenuBar(menuBar);
		// Add logo
		setIconImage(logoIMG);
		// Set size of JFrame
		setSize(600, 600);
		// Open application in centre of users screen by default
		setLocationRelativeTo(null);
		// Set created theme for application
		setLookAndFeel();
		// When user clicks exit, do nothing - essential for save on exit
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	/*
	 * Create Thread for character count.
	 */
	public void run() {
		// check Thread is running
		while(true) {
			if(textArea.isFocusOwner()) {
				// Create array for text to store current text
				char[] text = textArea.getText().toCharArray();
				// Get the length of current text within Text Area
				int count = text.length;

				// For characters in Text Area
				for(char c : text) {
					// If character is a white space, do not add to count
					if(c == ' ' || c == '\n') {
						count--;
					}
				}
				// Set character for display
				charCount.setText("Characters: " + count);
			}
		}
	}

	/***********************************************
	 * Creating the theme for the GUI
	 ***********************************************/
	public static void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}
	}

	public static JTextPane getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextPane jextArea) {
		Notepad.textArea = jextArea;
	}

	public static JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		Notepad.frame = frame;
	}

	public static JComboBox<Object> getCombo() {
		return combo;
	}

	public static void setCombo(JComboBox<Object> combo) {
		Notepad.combo = combo;
	}
	/**************************************************
	 * Run the application utilising Char Count thread
	 **************************************************/
	public static void main(String[] args) {

		Thread thread = new Thread(new Notepad());
		thread.start();
	}

}
