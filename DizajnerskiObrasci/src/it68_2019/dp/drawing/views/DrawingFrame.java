package it68_2019.dp.drawing.views;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it68_2019.dp.drawing.controllers.DrawingController;
import javax.swing.JTabbedPane;
import java.awt.event.MouseMotionAdapter;

public class DrawingFrame extends JFrame {
	private JPanel contentPane;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6456110324869685433L;
	private DrawingView view;
	private ToolbarView toolbar;
	private LogPanelView logPanel;
	private DrawingController controller;
	private JTabbedPane tabbedPane;
	private LayersPanelView layersPanel;

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

	private void setupDrawingView() {
		view = new DrawingView();
		view.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				controller.mouseDragged(e);
			}
		});
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				controller.mousePressed(e);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				controller.mouseReleased(e);
			}
		});
		contentPane.add(view, BorderLayout.CENTER);
	}

	private void setupToolbarView() {
		toolbar = new ToolbarView();
		contentPane.add(toolbar, BorderLayout.WEST);
	}

	private void setupTabPanelsView() {
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPane.add(tabbedPane, BorderLayout.EAST);
			{
				layersPanel = new LayersPanelView();
				tabbedPane.addTab("Object Layers", null, layersPanel, null);
			}
			logPanel = new LogPanelView();
			tabbedPane.addTab("Log History", null, logPanel, null);
		}
	}

	public DrawingFrame() {
		super();
		setupFrame();
		setupDrawingView();
		setupToolbarView();
		setupTabPanelsView();
	}

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

}
