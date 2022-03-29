package it68_2019.dp.drawing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import it68_2019.dp.drawing.components.canvas.CanvasController;
import it68_2019.dp.drawing.components.canvas.CanvasModel;
import it68_2019.dp.drawing.components.canvas.CanvasView;
import it68_2019.dp.drawing.components.layerspanel.LayersPanelView;
import it68_2019.dp.drawing.components.logpanel.LogPanelView;
import it68_2019.dp.drawing.components.menubar.MenubarView;
import it68_2019.dp.drawing.components.toolbar.ToolbarController;
import it68_2019.dp.drawing.components.toolbar.ToolbarModel;
import it68_2019.dp.drawing.components.toolbar.ToolbarView;
import it68_2019.dp.drawing.types.ToolbarAction;

public class ApplicationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6456110324869685433L;
	// Canvas Component
	private CanvasView canvasView;
	private CanvasModel canvasModel;
	private CanvasController canvasController;
	// Tool Bar Component
	private ToolbarView toolbarView;
	private ToolbarModel toolbarModel;
	private ToolbarController toolbarController;
	// Log Panel Component
	private LogPanelView logPanelView;
	// Helper Objects
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private LayersPanelView layersPanel;

	public ApplicationFrame() {
		super();
		setupFrame();
		setupCanvas();
		setupToolbar();
		setupTabPanels();
	}

	private void setupFrame() {
		setTitle("Draganović Mladen - IT68/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(6, 0));
		setJMenuBar(new MenubarView());
	}

	private void setupCanvas() {
		canvasView = new CanvasView();
		canvasModel = new CanvasModel();
		canvasController = new CanvasController(canvasModel, canvasView);
		canvasView.setModel(canvasModel);
		canvasView.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				canvasController.mouseDragged(e);
			}
		});
		canvasView.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				canvasController.createShape(toolbarModel.getToolbarAction());
				canvasController.mousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				canvasController.mouseReleased(e);
			}
		});
		contentPane.add(canvasView, BorderLayout.CENTER);
	}

	private void setupToolbar() {
		toolbarView = new ToolbarView();
		toolbarModel = new ToolbarModel();
		toolbarController = new ToolbarController(toolbarModel, toolbarView);
		toolbarView.setModel(toolbarModel);
		toolbarView.btnToolbarSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolbarController.setToolbarAction(ToolbarAction.SELECT);
			}
		});
		toolbarView.btnToolbarPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolbarController.setToolbarAction(ToolbarAction.POINT);
			}
		});
		toolbarView.btnToolbarLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolbarController.setToolbarAction(ToolbarAction.LINE);
			}
		});
		toolbarView.btnToolbarRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolbarController.setToolbarAction(ToolbarAction.RECTANGLE);
			}
		});
		toolbarView.btnToolbarCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolbarController.setToolbarAction(ToolbarAction.CIRCLE);
			}
		});
		toolbarView.btnToolbarDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolbarController.setToolbarAction(ToolbarAction.DONUT);
			}
		});
		toolbarView.btnToolbarHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolbarController.setToolbarAction(ToolbarAction.HEXAGON);
			}
		});
		contentPane.add(toolbarView, BorderLayout.WEST);
	}

	private void setupTabPanels() {

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.EAST);
		{
			layersPanel = new LayersPanelView();
			tabbedPane.addTab("Object Layers", null, layersPanel, null);
		}
		logPanelView = new LogPanelView();
		tabbedPane.addTab("Log History", null, logPanelView, null);

	}

}
