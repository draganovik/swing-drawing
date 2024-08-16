package drawing.mvc.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class CommandLogPanelView extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -5676175567633015715L;
	private DefaultListModel<String> listModel;
	private JList<String> list;

	/**
	 * Create the panel.
	 */
	public CommandLogPanelView() {
		super();
		list = new JList<>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		listModel = new DefaultListModel<>();

		JScrollPane scrollPanel = new JScrollPane(list);
		scrollPanel.setPreferredSize(new Dimension(200, 100));
		setLayout(new BorderLayout(0, 0));
		add(scrollPanel, BorderLayout.CENTER);

	}

	public void setDLM(DefaultListModel<String> dlm) {
		this.listModel = dlm;
		list.setModel(listModel);
	}

}
