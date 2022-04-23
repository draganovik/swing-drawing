package drawing;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import drawing.components.canvas.CanvasController;
import drawing.components.canvas.CanvasModel;
import drawing.components.canvas.CanvasView;
import drawing.components.layerspanel.LayersPanelView;
import drawing.components.logpanel.LogPanelView;
import drawing.components.menubar.MenubarView;
import drawing.components.toolbar.ToolbarController;
import drawing.components.toolbar.ToolbarModel;
import drawing.components.toolbar.ToolbarView;

public class ApplicationFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -6456110324869685433L;
	private CanvasController canvasController;
	private CanvasModel canvasModel;
	// Canvas Component
	private CanvasView canvasView;
	// Helper Objects
	private JPanel contentPane;
	private LayersPanelView layersPanel;
	// Log Panel Component
	private LogPanelView logPanelView;
	private JTabbedPane tabbedPane;
	private ToolbarController toolbarController;
	private ToolbarModel toolbarModel;
	// Tool Bar Component
	private ToolbarView toolbarView;

	public ApplicationFrame() {
		super();
		setupFrame();
		initializeViews();
		initializeModels();
		setupToolbar();
		setupTabPanels();
		setupCanvas();
	}

	private void initializeModels() {
		canvasModel = new CanvasModel();
		toolbarModel = new ToolbarModel();
	}

	private void initializeViews() {
		canvasView = new CanvasView();
		toolbarView = new ToolbarView();
		layersPanel = new LayersPanelView();
		logPanelView = new LogPanelView();
	}

	private void setupCanvas() {
		canvasController = new CanvasController(canvasModel, toolbarModel, canvasView);
		canvasView.setModel(canvasModel);
		canvasView.setController(canvasController);
		contentPane.add(canvasView, BorderLayout.CENTER);
	}

	private void setupFrame() {
		setTitle("Draganović Mladen - IT68/2019");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(6, 0));
		setJMenuBar(new MenubarView());
	}

	private void setupTabPanels() {
		tabbedPane = new JTabbedPane(SwingConstants.TOP);
		contentPane.add(tabbedPane, BorderLayout.EAST);
		{
			tabbedPane.addTab("Object Layers", null, layersPanel, null);
		}
		tabbedPane.addTab("Log History", null, logPanelView, null);

	}

	private void setupToolbar() {
		toolbarController = new ToolbarController(toolbarModel, canvasModel, toolbarView, canvasView);
		toolbarView.setModel(toolbarModel);
		toolbarView.setController(toolbarController);
		contentPane.add(toolbarView, BorderLayout.WEST);
	}

}
