import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author Jamie Longbottom
 *
 * Class for creating the cut, copy and paste actions
 * registered to both the key binds (Ctrl X, C, V) and
 * the right click mouse key, displaying Cut, Copy and
 * Paste for user.
 */
interface AlterText {
	public void copyPaste();
	public void actionValue(TextAction act, int key, String string);
	public void setPopMenu(JTextComponent... component);
}

public class CopyPaste implements AlterText {

	private JPopupMenu pop = new JPopupMenu();

	/*
	 * Creating cut, copy, paste actions utilising 
	 * DefaultEditorKit Library.
	 */
	public void copyPaste() {

		// bind to Key Event Ctrl+X, set to String (“Cut”)
		actionValue(new DefaultEditorKit.CutAction(), KeyEvent.VK_X, "Cut");
		// bind to Key Event Ctrl+C, set to String (“Copy”)
		actionValue(new DefaultEditorKit.CopyAction(), KeyEvent.VK_C, "Copy");
		// bind to Key Event Ctrl+V, set to String (“Paste”)
		actionValue(new DefaultEditorKit.PasteAction(), KeyEvent.VK_V, "Paste");
	}

	/*
	 * Creating the KeyStrokes to bind to the actionValue Strings
	 *
	 *
	 * @param act     action implementation for key bindings
	 * @param key     get key value for user key events
	 * @param string  get String value to bind to key event
	 */
	public void actionValue(TextAction act, int key, String string) {

		// Create Abstract Action of Accelerator Key, get users KeyStroke, set key value & input to Ctrl
		act.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(key, InputEvent.CTRL_DOWN_MASK));
		// Create Abstract Action of Name, set to string (“Copy”, “Cut”, “Paste”);
		act.putValue(AbstractAction.NAME, string);

		// Add action to Popup Menu
		pop.add(new JMenuItem(act));
	}

	/*
	 * Register Caret Listener for selected text within existing text field
	 *
	 *
	 * @param component  provides command required for component manipulation
	 */
	public void setPopMenu(JTextComponent... component) {

		if(component == null) {
			return;
		}
		// Sets the text ("Cut"), ("Copy"), ("Paste") to Popup Menu
		for (JTextComponent text : component) {
			text.setComponentPopupMenu(pop);
		}
	}
  
}