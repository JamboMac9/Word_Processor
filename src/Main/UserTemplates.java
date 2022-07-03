import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * @author Jamie Longbottom
 * 
 */
interface Templates {
	
	public void templateOpened();
	public void templateSaved() throws Exception;
	
}
/*
 * Gives the user the option to save or open their
 * own template. Saves to a specific directory that
 * the user cannot access, so overwrites the file
 * that is already in the directory.
 */
public class UserTemplates implements Templates {
	// Find the file within the specified directory
	File file = new File(".\\src\\UserTemplates\\user.txt");

	public void templateSaved() throws Exception {

		// if file exists, overwrite current file with text within text area
		if (file.exists()) {
			
			FileWriter writer = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(writer);

			// get existing text from Text Area and save to specified file
			bw.write(Notepad.getTextArea().getText());
			JOptionPane.showMessageDialog(Notepad.getFrame(), "File Saved Successfully!");
			bw.close();
		}
	}

	public void templateOpened() {

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

