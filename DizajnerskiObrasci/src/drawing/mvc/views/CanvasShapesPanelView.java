package drawing.mvc.views;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import drawing.geometry.Shape;

public class CanvasShapesPanelView extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -5676175567633015715L;
	private DefaultListModel<Shape> listModel = new DefaultListModel<>();
	private JList<Shape> list = new JList<>();

	/**
	 * Create the panel.
	 */
	public CanvasShapesPanelView() {
		super();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPanel = new JScrollPane(list);
		setLayout(new BorderLayout(0, 0));
		add(scrollPanel, BorderLayout.CENTER);
	}

	public void setDLM(DefaultListModel<Shape> dlm) {
		this.listModel = dlm;
		this.list.setModel(listModel);
	}
}
