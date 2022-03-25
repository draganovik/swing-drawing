package it68_2019.dp.drawing.components.layerspanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class LayersPanelView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5676175567633015715L;

	/**
	 * Create the panel.
	 */
	public LayersPanelView() {
		super();
		JList<String> list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		list.setModel(listModel);
		JScrollPane scrollPanel = new JScrollPane(list);
		scrollPanel.setPreferredSize(new Dimension(200,100));
		setLayout(new BorderLayout(0, 0));
		add(scrollPanel, BorderLayout.CENTER);
		mockList(listModel);

	}

	public void mockList(DefaultListModel<String> dlm) {
		for (int i = 0; i < 100; i++) {
			dlm.addElement("Object_" + i);
		}
	}

}
