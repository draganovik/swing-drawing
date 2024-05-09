package drawing.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import drawing.mvc.views.CanvasView;
import drawing.mvc.views.LayersPanelView;
import drawing.mvc.views.LogPanelView;
import drawing.mvc.views.MenubarView;
import drawing.mvc.views.ToolbarView;

public class FrameView extends JFrame {

	private static final long serialVersionUID = -6456110324869685433L;
	// Frame View modules
	private CanvasView canvasView = new CanvasView();
	private ToolbarView toolbarView = new ToolbarView();
	private LayersPanelView layersPanelView = new LayersPanelView();
	private LogPanelView logPanelView = new LogPanelView();
	private MenubarView menubarView = new MenubarView();

	// Local components
	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	public CanvasView getCanvasView() {
		return canvasView;
	}

	public FrameView() {
		super();

		setTitle("Draganović Mladen - IT68/2019");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(6, 0));
	}

	public void setupCanvas(CanvasModel canvasModel, CanvasController canvasController) {
		canvasController.setCanvasView(canvasView);
		canvasView.setModel(canvasModel);
		canvasView.setController(canvasController);

		contentPane.add(canvasView, BorderLayout.CENTER);
	}

	public void setupToolbar(ToolbarModel toolbarModel, ToolbarController toolbarController) {
		toolbarController.setToolbarView(toolbarView);
		toolbarView.setModel(toolbarModel);
		toolbarView.setController(toolbarController);

		contentPane.add(toolbarView, BorderLayout.WEST);
	}

	public void setupTabPanels(CanvasModel canvasModel) {
		layersPanelView.setDLM(canvasModel.getAllShapes());
		tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setPreferredSize(new Dimension(250, 400));
		contentPane.add(tabbedPane, BorderLayout.EAST);
		tabbedPane.addTab("Layers", null, layersPanelView, null);
		tabbedPane.addTab("History", null, logPanelView, null);

	}

	public void setupManubar(MenubarController menubarController) {
		menubarView.setController(menubarController);
		setJMenuBar(menubarView);

	}

}
