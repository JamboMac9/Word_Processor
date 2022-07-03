import javax.swing.JOptionPane;

public class HelpButton {
	
	public void help() {
	JOptionPane.showMessageDialog(Notepad.getFrame(), "Welcome to The Text Generation. Need Help?" + "\n" + "\n"
			+ "To open a new file: Select 'File' > 'New' - This will create a new document." + "\n"
			+ "To open an existing file: Select 'File' > 'Open' > Choose existing file." + "\n"
			+ "To save current document: Select 'File' > 'Save' > Type name of document, "
			+ "be sure to append .txt, so the file will save as a text file > click 'Save.'" + "\n"
			+ "To open predefined templates: Select 'Template' > Open template of choice" + "\n"
			+ "To save your own template: Select 'Template' > 'Save User Template' > this "
			+ "will save automatically" + "\n"
			+ "To open last saved template: Select 'Template' > Open User Template.");
	}
}
