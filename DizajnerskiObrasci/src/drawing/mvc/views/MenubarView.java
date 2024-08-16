package drawing.mvc.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import drawing.mvc.DrawingController;

public class MenubarView extends JMenuBar {

	/**
	 *
	 */
	private static final long serialVersionUID = -4578066578067109451L;
	private DrawingController controller;
	private JMenu menuEdit;
	private JMenuItem mntmExportLog;
	private JMenuItem mntmImportLog;
	private JMenuItem mntmLoadDrawing;
	private JMenuItem mntmSaveDrawing;
	private JMenuItem mntmRedo;
	private JMenuItem mntmUndo;

	/**
	 * Create the panel.
	 */
	public MenubarView() {
		super();
		// Where the GUI is created:
		JMenu menuFile;
		JMenuItem mntmMoveForward;

		// Build the first menu.
		menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_A);
		menuFile.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		add(menuFile);

		mntmSaveDrawing = new JMenuItem("Save");
		mntmSaveDrawing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveAsRawFile();
			}
		});

		JMenuItem mntmNewWorkspace = new JMenuItem("New Workspace");
		mntmNewWorkspace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.startNewWorkspace();
			}
		});
		mntmNewWorkspace.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK));
		menuFile.add(mntmNewWorkspace);

		// a group of JMenuItems
		mntmLoadDrawing = new JMenuItem("Open", KeyEvent.VK_T);
		mntmLoadDrawing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadARawFile();
			}
		});
		mntmLoadDrawing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.META_DOWN_MASK));
		mntmLoadDrawing.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuFile.add(mntmLoadDrawing);
		mntmSaveDrawing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_DOWN_MASK));
		mntmSaveDrawing.setMnemonic(KeyEvent.VK_B);
		menuFile.add(mntmSaveDrawing);

		menuFile.addSeparator();

		mntmImportLog = new JMenuItem("Load a Log File");
		menuFile.add(mntmImportLog);
		mntmImportLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		mntmImportLog.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK));

		mntmExportLog = new JMenuItem("Save as Log File");
		menuFile.add(mntmExportLog);
		mntmExportLog.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK));
		mntmExportLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		// Build second menu in the menu bar.
		menuEdit = new JMenu("Edit");
		menuEdit.setMnemonic(KeyEvent.VK_N);
		menuEdit.getAccessibleContext().setAccessibleDescription("This menu does nothing");
		add(menuEdit);

		mntmUndo = new JMenuItem("Undo");
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.META_DOWN_MASK));
		mntmUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		mntmUndo.setEnabled(false);
		menuEdit.add(mntmUndo);

		mntmRedo = new JMenuItem("Redo");
		mntmRedo.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK));
		mntmRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		mntmRedo.setEnabled(false);
		menuEdit.add(mntmRedo);

		JMenu menuObject = new JMenu("Object");
		add(menuObject);

		mntmMoveForward = new JMenuItem("Move forward");
		mntmMoveForward.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0));
		mntmMoveForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveSelectedForward();
			}
		});

		JMenuItem mntmDuplicate = new JMenuItem("Duplicate");
		mntmDuplicate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.duplicateSelected();
			}
		});
		mntmDuplicate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK));
		menuObject.add(mntmDuplicate);

		JSeparator separator_1_1 = new JSeparator();
		menuObject.add(separator_1_1);
		menuObject.add(mntmMoveForward);

		JMenuItem mntmMoveBackward = new JMenuItem("Move backward");
		mntmMoveBackward.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0));
		mntmMoveBackward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveSelectedBackward();
			}
		});
		menuObject.add(mntmMoveBackward);

		JSeparator separator_1 = new JSeparator();
		menuObject.add(separator_1);

		JMenuItem mntmToFront = new JMenuItem("Bring to front");
		mntmToFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveSelectedToFront();
			}
		});
		mntmToFront.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.SHIFT_DOWN_MASK));
		menuObject.add(mntmToFront);

		JMenuItem mntmToBack = new JMenuItem("Move to back");
		mntmToBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveSelectedToBack();
			}
		});
		mntmToBack.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.SHIFT_DOWN_MASK));
		menuObject.add(mntmToBack);

	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public void setEnabledUndo(boolean inEnabled) {
		mntmUndo.setEnabled(inEnabled);
	}

	public void setEnabledRedo(boolean inEnabled) {
		mntmRedo.setEnabled(inEnabled);
	}

}
