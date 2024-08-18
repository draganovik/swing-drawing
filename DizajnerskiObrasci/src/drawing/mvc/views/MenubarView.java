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
	private JMenu menuObject;
	private JMenuItem mntmExportLog;
	private JMenuItem mntmImportLog;
	private JMenuItem mntmLoadDrawing;
	private JMenuItem mntmSaveDrawing;
	private JMenuItem mntmRedo;
	private JMenuItem mntmUndo;
	private JMenuItem mntmLoadNextCommand;
	private Integer MetaKay = System.getProperty("os.name").toLowerCase().contains("mac") ? InputEvent.META_DOWN_MASK
			: InputEvent.CTRL_DOWN_MASK;

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
		menuFile.getAccessibleContext().setAccessibleDescription("The main menu of the program");
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
		mntmNewWorkspace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_DOWN_MASK | MetaKay));
		menuFile.add(mntmNewWorkspace);

		// a group of JMenuItems
		mntmLoadDrawing = new JMenuItem("Open", KeyEvent.VK_T);
		mntmLoadDrawing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadARawFile();
			}
		});
		mntmLoadDrawing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, MetaKay));
		menuFile.add(mntmLoadDrawing);
		mntmSaveDrawing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, MetaKay));
		mntmSaveDrawing.setMnemonic(KeyEvent.VK_B);
		menuFile.add(mntmSaveDrawing);

		menuFile.addSeparator();

		mntmImportLog = new JMenuItem("Load a Log File");
		menuFile.add(mntmImportLog);
		mntmImportLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadALogFile();
			}
		});
		mntmImportLog.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.SHIFT_DOWN_MASK | MetaKay));

		mntmExportLog = new JMenuItem("Save as Log File");
		menuFile.add(mntmExportLog);
		mntmExportLog.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_DOWN_MASK | MetaKay));
		mntmExportLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveAsLogFile();
			}
		});

		// Build second menu in the menu bar.
		menuEdit = new JMenu("Edit");
		menuEdit.setMnemonic(KeyEvent.VK_N);
		add(menuEdit);

		mntmUndo = new JMenuItem("Undo");
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, MetaKay));
		mntmUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		mntmUndo.setEnabled(false);
		menuEdit.add(mntmUndo);

		mntmRedo = new JMenuItem("Redo");
		mntmRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.SHIFT_DOWN_MASK | MetaKay));
		mntmRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		mntmRedo.setEnabled(false);
		menuEdit.add(mntmRedo);

		JSeparator separator = new JSeparator();
		menuEdit.add(separator);

		mntmLoadNextCommand = new JMenuItem("Load next command");
		mntmLoadNextCommand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, MetaKay));
		mntmLoadNextCommand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadNextCommand();
			}
		});
		mntmLoadNextCommand.setEnabled(false);
		menuEdit.add(mntmLoadNextCommand);

		menuObject = new JMenu("Object");
		menuObject.setVisible(false);
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
		mntmDuplicate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, MetaKay));
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
		mntmToFront.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, MetaKay));
		menuObject.add(mntmToFront);

		JMenuItem mntmToBack = new JMenuItem("Move to back");
		mntmToBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveSelectedToBack();
			}
		});
		mntmToBack.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, MetaKay));
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

	public void isInLoaderMode(boolean inEnabled) {
		mntmLoadNextCommand.setEnabled(inEnabled);
	}

	public void setVisibleObjectOptions(boolean isEnabled) {
		menuObject.setVisible(mntmLoadNextCommand.isEnabled() ? false : isEnabled);
	}

}
