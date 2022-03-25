package it68_2019.dp.drawing.components.menubar;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class MenubarView extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4578066578067109451L;
	private JMenuItem mntmLoad;
	private JMenuItem mntmSave;
	private JMenu menu_1;
	private JMenuItem mntmImport;
	private JMenuItem mntmExport;

	/**
	 * Create the panel.
	 */
	public MenubarView() {
		super();
		// Where the GUI is created:
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;

		// Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		add(menu);

		// a group of JMenuItems
		mntmLoad = new JMenuItem("Open", KeyEvent.VK_T);
		mntmLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		mntmLoad.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(mntmLoad);

		mntmSave = new JMenuItem("Save");
		mntmSave.setMnemonic(KeyEvent.VK_B);
		menu.add(mntmSave);

		// a group of radio button menu items
		menu.addSeparator();
		ButtonGroup group = new ButtonGroup();
		submenu = new JMenu("Log file");
		submenu.setMnemonic(KeyEvent.VK_S);
		
				mntmImport = new JMenuItem("Import");
				mntmImport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
				submenu.add(mntmImport);
				
						mntmExport = new JMenuItem("Export");
						submenu.add(mntmExport);
						menu.add(submenu);
		
				// a submenu
				menu.addSeparator();
		rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Another one");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		// a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		menu.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Another one");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		menu.add(cbMenuItem);

		// Build second menu in the menu bar.
		menu_1 = new JMenu("Edit");
		menu_1.setMnemonic(KeyEvent.VK_N);
		menu_1.getAccessibleContext().setAccessibleDescription("This menu does nothing");
		add(menu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Undo");
		menu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Redo");
		menu_1.add(mntmNewMenuItem_1);
		
		JSeparator separator = new JSeparator();
		menu_1.add(separator);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Clear canvas");
		menu_1.add(mntmNewMenuItem_2);
		
		menu = new JMenu("Window");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
		
		JMenu mnNewMenu = new JMenu("Object");
		add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Move up");
		mnNewMenu.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Move down");
		mnNewMenu.add(mntmNewMenuItem_7);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Bring to front");
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Move to back");
		mnNewMenu.add(mntmNewMenuItem_5);
		add(menu);

	}

}
