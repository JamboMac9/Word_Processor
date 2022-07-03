import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 * @author Jamie Longbottom
 * 
 * Class containing all Action Listeners.
 * 
 * extends SaveFile for ease of access to that class.
 * 
 * Gets all Action Listeners that == Strings within
 * the program. To reduce code when creating
 * Action Listeners for each requirement.
 *
 */
interface KeyListeners {
	public void actionPerformed(ActionEvent e);
	void setManager(UndoManager manager);
	public UndoManager getManager();
}

public class ActionListenerClass implements ActionListener, KeyListeners {
	
	private static UserTemplates ut = new UserTemplates();
	private static OpenFile of = new OpenFile();
	private static SaveFile sf = new SaveFile();
	private static HelpButton hb = new HelpButton();
	
	private static UndoManager manager = new UndoManager();
	public JMenu combo;

	// Method for instantiating Action Event for Action Listeners
	@Override
	public void actionPerformed(ActionEvent e) {

		// Set Action Command to onClick
		String onClick = e.getActionCommand();
		
		// If Action Event = String "Save" in the application, run fileSaved
		if (onClick.equals("Save")) {
			sf.fileSaved();
		} 
		// If Action Event = String "Open" in the application, run fileOpened
		else if (onClick.equals("Open")) {
			of.fileOpened();
		} 
		// If Action Event = String "New" in the application, create new empty Text Area
		else if (onClick.equals("New")) {
			Notepad.getTextArea().setText("");
		} 
		// If Action Event = String "Open Template" in the application, run openTemplate
		else if (onClick.equals("Open Template")) {
			of.openTemplates();
		}
		
		else if (onClick.equals("Save User Template")) {
			try {
				ut.templateSaved();
			} catch (Exception ev) {}
		}
		
		else if (onClick.equals("Open User Template")) {
			ut.templateOpened();
		}
		
		else if (onClick.equals("Help?")) {
			hb.help();
		}
		// Undoes appropriate edits within the UndoManager object
		else if (onClick.equals("Undo")) {
			try {
    			manager.undo();
    		} catch (CannotUndoException ev) {}
		
		} 
		// If Action Event = String "Redo" in the application
		else if (onClick.equals("Redo")) {
			try {
				// Re-does appropriate edits within the UndoManager object
    			manager.redo();
    		} catch (CannotRedoException ev) {}
		}
		// Set font to text area based on selected item within JComboBox
		else if (e.getSource() == Notepad.getCombo()) {
			Notepad.getTextArea().setFont(new Font((String) 
					Notepad.getCombo().getSelectedItem(), Font.PLAIN, 14));
		}
		
	}

	public UndoManager getManager() {
		return manager;
	}
	public void setManager(UndoManager manager) {
		ActionListenerClass.manager = manager;
	}
}
