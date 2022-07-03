import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Jamie Longbottom
 * 
 * An abstract class created to prevent duplication 
 * of code, being utilised within the OpenFile and 
 * Templates Classes.
 *
 */
public abstract class OpenFileMethods {

	public abstract void fileOpened();
	public abstract void openTemplates();

	/**
	 * @param file  interface to retrieve file from path
	 */
	public void readFile(File file) {

		try {
			// Create space for existing text within Text Area
			String openString1 = "";
			// Create space for text within selected file.
			String openString2 = "";

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			// Read each line within the selected file
			openString2 = br.readLine();

			// Match new line characters in selected file when overwriting current text
			while ((openString1 = br.readLine()) != null) {
				openString2 = openString2 + "\n" + openString1;
			}

			// Display selected file within Text Area
			Notepad.getTextArea().setText(openString2);

			br.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(Notepad.getFrame(), "No File Opened");
		}
	}
}

/*
 * Open class containing methods for opening
 * files from specified source folders.
 */
class OpenFile extends OpenFileMethods {
	/*
	 * Open the text file within the Documents
	 * file path, for user to open existing
	 * files within both the Documents path and
	 * the Templates path.
	 */
	public void fileOpened() {

		// Create File Chooser
		JFileChooser fcOpen = new JFileChooser();
		// Open File Chooser within selected path
		fcOpen.setCurrentDirectory(new File(".\\src\\Documents"));
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES ONLY", "txt", "text");
		fcOpen.setFileFilter(filter);

		// Display Open button in place of standard
		int openFile = fcOpen.showOpenDialog(null);

		// If user opens specified file
		if (openFile == JFileChooser.APPROVE_OPTION) {
			// Create new file object of selected file
			readFile(new File(fcOpen.getSelectedFile().getAbsolutePath()));

		} else
			JOptionPane.showMessageDialog(Notepad.getFrame(), "No File Opened");
	}
	/*
	 * Open predefined templates that the user specifies,
	 */
	public void openTemplates() {
		// Create File Chooser
		JFileChooser fcTemplate = new JFileChooser();
		// Open File Chooser within selected path
		fcTemplate.setCurrentDirectory(new File(".\\src\\Templates"));

		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES ONLY", "txt", "text");
		fcTemplate.setFileFilter(filter);

		// Display Open button in place of standard
		int fileChooser = fcTemplate.showOpenDialog(null);

		// If user opens specified file
		if (fileChooser == JFileChooser.APPROVE_OPTION) {

			// Create new file object of selected file
			readFile(new File(fcTemplate.getSelectedFile().getAbsolutePath()));

		} else
			JOptionPane.showMessageDialog(Notepad.getFrame(), "No File Opened");

	}
}
