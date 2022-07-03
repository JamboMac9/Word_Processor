import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/** 
 * @author Jamie Longbottom
 * 
 * Abstract class used to prevent duplication
 * of code, and to call on the abstract methods
 * in order to instantiate them.
 */
public abstract class SaveFileMethods {
	
	public abstract void fileSaved();
	public abstract void saveOnExit();
	
	/**
	 * @param file  interface to retrieve file from path
	 */
	public void writeFile(File file) {

		try {
			FileWriter writer = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(writer);

			
			// get existing text from Text Area and save to specified file
			bw.write(Notepad.getTextArea().getText());
			JOptionPane.showMessageDialog(Notepad.getFrame(), "File Saved Successfully!");

			bw.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Notepad.getFrame(), "No File Saved");
		}
	}
}

/**
 * 
 * Class for different save options, extending
 * the abstract class, utilising the body of code
 * from SaveFileMethods.
 *
 */
class SaveFile extends SaveFileMethods {
	/**
	 * For saving files. Gives user the option to
	 * save files within the Documents folder,
	 * checking to make sure they are not overwriting
	 * an existing file, and giving user option to 
	 * overwrite.
	 * @return 
	 */
	public void fileSaved() {

		// Create File Chooser
		JFileChooser fcSave = new JFileChooser();
		// Open File Chooser within selected path
		fcSave.setCurrentDirectory(new File(".\\src\\Documents"));
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES ONLY", "txt", "text");
		fcSave.setFileFilter(filter);

		// Display Save button in place of standard
		int saveFile = fcSave.showSaveDialog(null);

		// If user opens specified file
		if (saveFile == JFileChooser.APPROVE_OPTION) {
			// Create new file object of selected file
			File file = new File(fcSave.getSelectedFile().getAbsolutePath());
			
			// If user selects a file that already exists
			if (file.exists()) {

				// Ask if user would like to overwrite existing file
				int result = JOptionPane.showConfirmDialog(fcSave, "Overwrite existing file?",
						"File Exists", JOptionPane.YES_NO_CANCEL_OPTION);
				// If user opts to overwrite existing file
				if (result == JOptionPane.YES_OPTION) {
					writeFile(file);
				}
				// If user opts to create new save file
			} else {
				writeFile(file);
			}
		} else
			JOptionPane.showMessageDialog(Notepad.getFrame(), "No File Saved");
	}


	/**
	 * To warn user of any unsaved changes
	 * if they attempt to exit the application.
	 * Extends SaveFile to save duplication
	 * of code.
	 */
	public void saveOnExit() {

		// If text area is not empty
		if(! Notepad.getTextArea().equals(null)) {

			// Warn user changes will be lost if not saved
			int clickExit = JOptionPane.showConfirmDialog
					(null, "Unsaved changes will be lost! "
							+ "\nSave before exit..?");
			// If user opts to save, instantiate SaveFile
			if(clickExit == JOptionPane.OK_OPTION) {
				fileSaved();
				System.exit(0);
			}
			// If user opts to not save, exit system
			else if(clickExit == JOptionPane.NO_OPTION)
				System.exit(0);
			// If user selects cancel, return to text area
			else
				return;
		}
		else
			System.exit(0);
	}
}
