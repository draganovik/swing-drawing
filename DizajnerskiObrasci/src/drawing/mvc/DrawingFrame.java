package drawing.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import drawing.mvc.models.CanvasModel;
import drawing.mvc.models.WorkspaceModel;
import drawing.mvc.views.CanvasShapesPanelView;
import drawing.mvc.views.CanvasView;
import drawing.mvc.views.CommandLogPanelView;
import drawing.mvc.views.MenubarView;
import drawing.mvc.views.ToolbarView;

public class DrawingFrame extends JFrame {

	private static final long serialVersionUID = -6456110324869685433L;
	// Frame View modules
	private CanvasView canvasView = new CanvasView();
	private ToolbarView toolbarView = new ToolbarView();
	private CanvasShapesPanelView canvasShapesPanelView = new CanvasShapesPanelView();
	private CommandLogPanelView commandLogPanelView = new CommandLogPanelView();
	private MenubarView menubarView = new MenubarView();

	// Local components
	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	public CanvasView getCanvasView() {
		return canvasView;
	}

	public ToolbarView getToolbarView() {
		return this.toolbarView;
	}

	public MenubarView getMenubarView() {
		return this.menubarView;
	}

	public CanvasShapesPanelView getCanvasShapesPanelView() {
		return this.canvasShapesPanelView;
	}

	public DrawingFrame() {
		super();

		setTitle("Draganović Mladen - IT68/2019");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setMinimumSize(new Dimension(500, 440));
		contentPane = new JPanel();
		toolbarView.setBorder(new EmptyBorder(0, 0, 0, 3));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		setJMenuBar(menubarView);

		tabbedPane = new JTabbedPane(SwingConstants.TOP);

		tabbedPane.addTab("Commands", null, commandLogPanelView, null);
		tabbedPane.addTab("Shapes", null, canvasShapesPanelView, null);

		tabbedPane.setPreferredSize(new Dimension(300, 0));

		contentPane.add(canvasView, BorderLayout.CENTER);
		contentPane.add(toolbarView, BorderLayout.WEST);
		contentPane.add(tabbedPane, BorderLayout.EAST);
	}

	public void setupViews(CanvasModel canvasModel, WorkspaceModel workspaceModel, DrawingController controller) {
		canvasView.setModel(canvasModel);
		canvasView.setController(controller);

		toolbarView.setModel(workspaceModel);
		toolbarView.setController(controller);

		menubarView.setController(controller);

		canvasShapesPanelView.setDLM(canvasModel.getDefaultListModel());
		commandLogPanelView.setDLM(workspaceModel.getCommandLogListModel());
	}

}
