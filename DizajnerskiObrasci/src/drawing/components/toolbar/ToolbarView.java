package drawing.components.toolbar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import drawing.types.ToolAction;

public class ToolbarView extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 8181676546869383767L;
	private ButtonGroup actionsButtonGroup = new ButtonGroup();
	public JButton btnToolbarBackground;
	public JToggleButton btnToolbarCircle;
	public JButton btnToolbarColor;
	public JButton btnToolbarDelete;
	public JToggleButton btnToolbarDonut;
	public JToggleButton btnToolbarHexagon;
	public JToggleButton btnToolbarLine;
	public JButton btnToolbarModify;
	public JToggleButton btnToolbarPoint;
	public JToggleButton btnToolbarRectangle;
	// Command Buttons
	public JToggleButton btnToolbarSelect;
	private ToolbarController controller;
	private ToolbarModel model;

	/**
	 * Create the panel.
	 */
	public ToolbarView() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		btnToolbarSelect = new JToggleButton(new ImageIcon(classLoader.getResource("Select.png").getPath()));
		btnToolbarSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolbarAction(ToolAction.SELECT);
			}
		});
		btnToolbarSelect.setSelected(true);
		actionsButtonGroup.add(btnToolbarSelect);

		btnToolbarPoint = new JToggleButton(new ImageIcon(classLoader.getResource("Point.png").getPath()));
		btnToolbarPoint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolbarAction(ToolAction.POINT);
			}
		});
		actionsButtonGroup.add(btnToolbarPoint);

		btnToolbarLine = new JToggleButton(new ImageIcon(classLoader.getResource("Line.png").getPath()));
		btnToolbarLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolbarAction(ToolAction.LINE);
			}
		});
		actionsButtonGroup.add(btnToolbarLine);

		btnToolbarRectangle = new JToggleButton(new ImageIcon(classLoader.getResource("Rectangle.png").getPath()));
		btnToolbarRectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolbarAction(ToolAction.RECTANGLE);
			}
		});
		actionsButtonGroup.add(btnToolbarRectangle);

		btnToolbarCircle = new JToggleButton(new ImageIcon(classLoader.getResource("Circle.png").getPath()));
		btnToolbarCircle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolbarAction(ToolAction.CIRCLE);
			}
		});
		actionsButtonGroup.add(btnToolbarCircle);

		btnToolbarDonut = new JToggleButton(new ImageIcon(classLoader.getResource("Donut.png").getPath()));
		btnToolbarDonut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolbarAction(ToolAction.DONUT);
			}
		});
		actionsButtonGroup.add(btnToolbarDonut);

		btnToolbarHexagon = new JToggleButton(new ImageIcon(classLoader.getResource("Hexagon.png").getPath()));
		btnToolbarHexagon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolbarAction(ToolAction.HEXAGON);
			}
		});
		actionsButtonGroup.add(btnToolbarHexagon);

		JPanel panel = new JPanel();

		btnToolbarModify = new JButton("Modify");
		btnToolbarModify.setForeground(new Color(100, 149, 237));
		btnToolbarModify.setBackground(new Color(224, 255, 255));
		btnToolbarDelete = new JButton("Delete");
		btnToolbarDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolbarAction_Delete();
			}
		});
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

		btnToolbarBackground = new JButton("");
		btnToolbarBackground.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolbarAction_BackgroundPicker();
			}
		});

		btnToolbarBackground.setLocation(22, 10);
		btnToolbarBackground.setSize(32, 32);
		panel.add(btnToolbarBackground);

		btnToolbarColor = new JButton("");
		btnToolbarColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolbarAction_ColorPicker();
			}
		});

		btnToolbarColor.setLocation(38, 26);
		btnToolbarColor.setSize(32, 32);
		panel.add(btnToolbarColor);

		setLayout(groupLayout);

	}

	public void setController(ToolbarController controller) {
		this.controller = controller;
	}

	public void setModel(ToolbarModel model) {
		this.model = model;
		btnToolbarColor.setBackground(model.getShapeColor());
		btnToolbarBackground.setBackground(model.getShapeBackground());
	}
}
