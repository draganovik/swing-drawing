package it68_2019.dp.drawing.components.toolbar;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ToolbarView extends JPanel {
	private ButtonGroup actionsButtonGroup = new ButtonGroup();
	/**
	 * 
	 */
	private static final long serialVersionUID = 8181676546869383767L;
	private ToolbarModel model;
	// Command Buttons
	public JToggleButton btnToolbarSelect;
	public JToggleButton btnToolbarPoint;
	public JToggleButton btnToolbarLine;
	public JToggleButton btnToolbarRectangle;
	public JToggleButton btnToolbarCircle;
	public JToggleButton btnToolbarDonut;
	public JToggleButton btnToolbarHexagon;
	public JButton btnToolbarModify;
	public JButton btnToolbarDelete;
	public JButton btnToolbarForeground;
	public JButton btnToolbarBackground;

	/**
	 * Create the panel.
	 */
	public ToolbarView() {

		btnToolbarSelect = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Select.png")));
		btnToolbarSelect.setSelected(true);
		actionsButtonGroup.add(btnToolbarSelect);

		btnToolbarPoint = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Point.png")));
		actionsButtonGroup.add(btnToolbarPoint);

		btnToolbarLine = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Line.png")));
		actionsButtonGroup.add(btnToolbarLine);

		btnToolbarRectangle = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Rectangle.png")));
		actionsButtonGroup.add(btnToolbarRectangle);

		btnToolbarCircle = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Circle.png")));
		actionsButtonGroup.add(btnToolbarCircle);

		btnToolbarDonut = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Donut.png")));
		actionsButtonGroup.add(btnToolbarDonut);

		btnToolbarHexagon = new JToggleButton(new ImageIcon(ToolbarView.class.getResource("/assets/Hexagon.png")));
		actionsButtonGroup.add(btnToolbarHexagon);

		JPanel panel = new JPanel();

		btnToolbarModify = new JButton("Modify");
		btnToolbarModify.setForeground(new Color(100, 149, 237));
		btnToolbarModify.setBackground(new Color(224, 255, 255));
		btnToolbarDelete = new JButton("Delete");
		btnToolbarDelete.setForeground(new Color(128, 0, 0));
		btnToolbarDelete.setBackground(new Color(255, 182, 193));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(1)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnToolbarPoint, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnToolbarSelect, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnToolbarLine, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnToolbarRectangle, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnToolbarCircle, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnToolbarDonut, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnToolbarHexagon, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnToolbarModify, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnToolbarDelete, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(2)
				.addComponent(btnToolbarSelect, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(2)
				.addComponent(btnToolbarPoint, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(2)
				.addComponent(btnToolbarLine, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(2)
				.addComponent(btnToolbarRectangle, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(2)
				.addComponent(btnToolbarCircle, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(2)
				.addComponent(btnToolbarDonut, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(2)
				.addComponent(btnToolbarHexagon, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(2)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE).addGap(2)
				.addComponent(btnToolbarModify, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(2)
				.addComponent(btnToolbarDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(167, Short.MAX_VALUE)));
		panel.setLayout(null);

		btnToolbarForeground = new JButton("");
		btnToolbarForeground.setBackground(new Color(169, 169, 169));
		btnToolbarForeground.setLocation(22, 10);
		btnToolbarForeground.setSize(32, 32);
		panel.add(btnToolbarForeground);

		btnToolbarBackground = new JButton("");
		btnToolbarBackground.setBackground(new Color(105, 105, 105));
		btnToolbarBackground.setLocation(38, 26);
		btnToolbarBackground.setSize(32, 32);
		panel.add(btnToolbarBackground);
		setLayout(groupLayout);

	}

	public void setModel(ToolbarModel model) {
		this.model = model;
	}
}
