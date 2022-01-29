package it68_2019.dp.drawing.views;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

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
		
		JPanel panel = new JPanel();
		
		JButton btnNewButton_8 = new JButton("Modify");
		btnNewButton_8.setForeground(new Color(100, 149, 237));
		btnNewButton_8.setBackground(new Color(224, 255, 255));
		JButton btnNewButton_9 = new JButton("Delete");
		btnNewButton_9.setForeground(new Color(128, 0, 0));
		btnNewButton_9.setBackground(new Color(255, 182, 193));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_0, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_8, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_9, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
					.addGap(2)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(btnNewButton_8, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(btnNewButton_9, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(167, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.setBackground(new Color(169, 169, 169));
		btnNewButton_6.setLocation(22, 10);
		btnNewButton_6.setSize(32, 32);
		panel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.setBackground(new Color(105, 105, 105));
		btnNewButton_7.setLocation(38, 26);
		btnNewButton_7.setSize(32, 32);
		panel.add(btnNewButton_7);
		setLayout(groupLayout);

	}
}
