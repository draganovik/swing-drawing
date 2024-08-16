package drawing.mvc.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import drawing.mvc.DrawingController;
import drawing.mvc.models.WorkspaceModel;
import drawing.types.ToolAction;

public class ToolbarView extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 8181676546869383767L;
	private ButtonGroup actionsButtonGroup = new ButtonGroup();
	private JButton btnToolbarBackground;
	private JToggleButton btnToolbarCircle;
	private JButton btnToolbarColor;
	private JButton btnToolbarDelete;
	private JToggleButton btnToolbarDonut;
	private JToggleButton btnToolbarHexagon;
	private JToggleButton btnToolbarLine;
	private JButton btnToolbarModify;
	private JToggleButton btnToolbarPoint;
	private JToggleButton btnToolbarRectangle;
	// Command Buttons
	private JToggleButton btnToolbarSelect;
	private DrawingController controller;

	/**
	 * Create the panel.
	 */
	public ToolbarView() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		btnToolbarSelect = new JToggleButton(new ImageIcon(classLoader.getResource("Select.png").getPath()));
		btnToolbarSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolAction(ToolAction.SELECT);
			}
		});
		btnToolbarSelect.setSelected(true);
		actionsButtonGroup.add(btnToolbarSelect);

		btnToolbarPoint = new JToggleButton(new ImageIcon(classLoader.getResource("Point.png").getPath()));
		btnToolbarPoint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolAction(ToolAction.POINT);
			}
		});
		actionsButtonGroup.add(btnToolbarPoint);

		btnToolbarLine = new JToggleButton(new ImageIcon(classLoader.getResource("Line.png").getPath()));
		btnToolbarLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolAction(ToolAction.LINE);
			}
		});
		actionsButtonGroup.add(btnToolbarLine);

		btnToolbarRectangle = new JToggleButton(new ImageIcon(classLoader.getResource("Rectangle.png").getPath()));
		btnToolbarRectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolAction(ToolAction.RECTANGLE);
			}
		});
		actionsButtonGroup.add(btnToolbarRectangle);

		btnToolbarCircle = new JToggleButton(new ImageIcon(classLoader.getResource("Circle.png").getPath()));
		btnToolbarCircle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolAction(ToolAction.CIRCLE);
			}
		});
		actionsButtonGroup.add(btnToolbarCircle);

		btnToolbarDonut = new JToggleButton(new ImageIcon(classLoader.getResource("Donut.png").getPath()));
		btnToolbarDonut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolAction(ToolAction.DONUT);
			}
		});
		actionsButtonGroup.add(btnToolbarDonut);

		btnToolbarHexagon = new JToggleButton(new ImageIcon(classLoader.getResource("Hexagon.png").getPath()));
		btnToolbarHexagon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setToolAction(ToolAction.HEXAGON);
			}
		});
		actionsButtonGroup.add(btnToolbarHexagon);

		JPanel panel = new JPanel();

		btnToolbarModify = new JButton("Edit");
		btnToolbarModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.modifySelected();
			}
		});
		btnToolbarModify.setForeground(new Color(100, 149, 237));
		btnToolbarModify.setBackground(new Color(224, 255, 255));
		btnToolbarModify.setEnabled(false);
		btnToolbarDelete = new JButton("Delete");
		btnToolbarDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteSelected();
			}
		});
		btnToolbarDelete.setForeground(new Color(128, 0, 0));
		btnToolbarDelete.setBackground(new Color(255, 182, 193));
		btnToolbarDelete.setEnabled(false);
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
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(2).addComponent(btnToolbarModify, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addGap(2).addComponent(btnToolbarDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(128, Short.MAX_VALUE)));

		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Center alignment

		btnToolbarBackground = new JButton("");
		btnToolbarBackground.setPreferredSize(new Dimension(32, 32)); // Set preferred size
		btnToolbarBackground.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.colorPickerPickBackgroundColor();
			}
		});
		panel.add(btnToolbarBackground);

		btnToolbarColor = new JButton("");
		btnToolbarColor.setPreferredSize(new Dimension(32, 32)); // Set preferred size
		btnToolbarColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.colorPickerPickOutlineColor();
			}
		});
		panel.add(btnToolbarColor);

		setLayout(groupLayout);

	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public void setModel(WorkspaceModel model) {
		btnToolbarColor.setOpaque(true);
		btnToolbarColor.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, model.getShapeColor()));

		btnToolbarBackground.setOpaque(true);
		btnToolbarBackground.setBackground(model.getShapeBackground());
		btnToolbarBackground.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
	}

	public void setEnabledModify(boolean isEnabled) {
		btnToolbarModify.setEnabled(isEnabled);
	}

	public void setEnabledDelete(boolean isEnabled) {
		btnToolbarDelete.setEnabled(isEnabled);
	}

	public void setPreviewShapeColor(Color color) {
		btnToolbarColor.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, color));
	}

	public void setPreviewShapeBackgroundColor(Color color) {
		btnToolbarBackground.setBackground(color);
	}

	public void setEnabledPreviewShapeBackgroundColor(boolean isEnabled) {
		btnToolbarBackground.setEnabled(isEnabled);
	}
}
