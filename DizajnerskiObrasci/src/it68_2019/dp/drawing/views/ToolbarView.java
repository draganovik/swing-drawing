package it68_2019.dp.drawing.views;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;

public class ToolbarView extends JPanel {
	private ButtonGroup actionsButtonGroup = new ButtonGroup();
	/**
	 * 
	 */
	private static final long serialVersionUID = 8181676546869383767L;

	/**
	 * Create the panel.
	 */
	public ToolbarView() {

		JToggleButton btnNewButton_0 = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Select.png")));
		btnNewButton_0.setSelected(true);
		actionsButtonGroup.add(btnNewButton_0);

		JToggleButton btnNewButton = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Point.png")));
		actionsButtonGroup.add(btnNewButton);

		JToggleButton btnNewButton_1 = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Line.png")));
		actionsButtonGroup.add(btnNewButton_1);

		JToggleButton btnNewButton_2 =new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Rectangle.png")));
		actionsButtonGroup.add(btnNewButton_2);

		JToggleButton btnNewButton_3 = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Circle.png")));
		actionsButtonGroup.add(btnNewButton_3);

		JToggleButton btnNewButton_4 = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Donut.png")));
		actionsButtonGroup.add(btnNewButton_4);

		JToggleButton btnNewButton_5 = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Hexagon.png")));
		actionsButtonGroup.add(btnNewButton_5);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_0, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(2)
					.addComponent(btnNewButton_0, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					)
		);
		setLayout(groupLayout);

	}

}
