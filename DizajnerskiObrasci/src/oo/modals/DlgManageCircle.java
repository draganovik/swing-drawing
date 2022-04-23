package oo.modals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import oo.geometry.Circle;
import oo.geometry.Point;

public class DlgManageCircle extends JDialog {

	private static final long serialVersionUID = -3721597314881851409L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgManageCircle dialog = new DlgManageCircle();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	JButton btnApply = new JButton("Add");
	public Circle circle = null;
	private final JPanel contentPanel = new JPanel();
	public boolean IsSuccessful;
	private JPanel pnlBackground;
	private JPanel pnlBorder;
	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;

	private JTextField txtRadius;

	/**
	 * Create the dialog.
	 */
	public DlgManageCircle() {
		setTitle("Add a circle");
		setBounds(100, 100, 300, 260);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblCoordinateX = new JLabel("Coordinate X: ");
		lblCoordinateX.setForeground(Color.DARK_GRAY);

		txtCoordinateX = new JTextField();
		txtCoordinateX.setColumns(10);

		txtCoordinateY = new JTextField();
		txtCoordinateY.setColumns(10);

		JLabel lblCoordinateY = new JLabel("Coordinate Y: ");
		lblCoordinateY.setForeground(Color.DARK_GRAY);

		JLabel lblWidth = new JLabel("Radius");
		lblWidth.setForeground(Color.DARK_GRAY);

		txtRadius = new JTextField();
		txtRadius.setColumns(10);

		JLabel lblDimensions = new JLabel("Dimensions");
		lblDimensions.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel lblPoint = new JLabel("Center of the circle");
		lblPoint.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel lblColors = new JLabel("Colors");
		lblColors.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnBackground = new JButton("Background");
		btnBackground.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color initialBackground = pnlBackground.getBackground();
				Color background = getColorFromPicker("Choose Circle Background", initialBackground);
				pnlBackground.setBackground(background);
			}
		});

		JButton btnBorder = new JButton("Border");
		btnBorder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color initialBorder = pnlBorder.getBackground();
				Color border = getColorFromPicker("Choose Circle Border", initialBorder);
				pnlBorder.setBackground(border);
			}
		});

		pnlBackground = new JPanel();
		pnlBackground.setBackground(Color.LIGHT_GRAY);

		pnlBorder = new JPanel();
		pnlBorder.setBackground(Color.DARK_GRAY);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(
										lblColors, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(200, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblPoint, Alignment.LEADING, GroupLayout.PREFERRED_SIZE,
														150, GroupLayout.PREFERRED_SIZE)
												.addGroup(Alignment.LEADING, gl_contentPanel
														.createSequentialGroup().addComponent(lblCoordinateX)
														.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(
																txtCoordinateX, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(Alignment.LEADING, gl_contentPanel
														.createSequentialGroup()
														.addComponent(lblCoordinateY, GroupLayout.PREFERRED_SIZE, 89,
																GroupLayout.PREFERRED_SIZE)
														.addGap(12).addComponent(
																txtCoordinateY, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(lblDimensions, Alignment.LEADING)
												.addGroup(Alignment.LEADING, gl_contentPanel
														.createSequentialGroup()
														.addGroup(gl_contentPanel
																.createParallelGroup(Alignment.LEADING)
																.addComponent(lblWidth, GroupLayout.PREFERRED_SIZE, 89,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(btnBackground))
														.addGap(12)
														.addGroup(gl_contentPanel
																.createParallelGroup(Alignment.LEADING, false)
																.addGroup(gl_contentPanel.createSequentialGroup()
																		.addComponent(pnlBackground,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addPreferredGap(ComponentPlacement.RELATED))
																.addComponent(txtRadius))))
										.addGap(12))
								.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
										.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING,
												gl_contentPanel.createSequentialGroup().addGap(142).addComponent(
														pnlBorder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
										.addComponent(btnBorder)).addGap(12)))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap()
				.addComponent(lblPoint, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCoordinateX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCoordinateX))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblCoordinateY))
						.addComponent(txtCoordinateY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(lblDimensions).addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblWidth))
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(lblColors, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
				.addGap(12)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(pnlBackground,
								GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnBackground))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBorder, Alignment.TRAILING).addGroup(Alignment.TRAILING,
								gl_contentPanel.createSequentialGroup()
										.addComponent(pnlBorder, GroupLayout.PREFERRED_SIZE, 19,
												GroupLayout.PREFERRED_SIZE)
										.addGap(5)))
				.addContainerGap(67, Short.MAX_VALUE)));
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { lblCoordinateX, txtCoordinateX });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { txtCoordinateY, lblCoordinateY });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { lblWidth, txtRadius });
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnApply.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CreateCircle();
						if (IsSuccessful) {
							dispose();
						}
					}
				});
				btnApply.setActionCommand("OK");
				buttonPane.add(btnApply);
				getRootPane().setDefaultButton(btnApply);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						IsSuccessful = false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public DlgManageCircle(Circle circle, boolean isEdit) {
		this();
		if (!isEdit) {
			setTitle("Remove circle");
			txtCoordinateX.setEditable(false);
			txtCoordinateY.setEditable(false);
			txtRadius.setEditable(false);
			btnApply.setText("Remove");
			IsSuccessful = true;
		} else {
			setTitle("Update circle");
			btnApply.setText("Save");
			setBounds(100, 100, 300, 380);
		}
		pnlBorder.setBackground(circle.getColor());
		pnlBackground.setBackground(circle.getBackgroundColor());
		txtCoordinateX.setText(Integer.toString(circle.getCenter().getX()));
		txtCoordinateY.setText(Integer.toString(circle.getCenter().getY()));
		txtRadius.setText(Integer.toString(circle.getRadius()));
	}

	public DlgManageCircle(Point releasedPoint) {
		this();
		setBounds(100, 100, 300, 380);
		txtCoordinateX.setText(Integer.toString(releasedPoint.getX()));
		txtCoordinateY.setText(Integer.toString(releasedPoint.getY()));

	}

	public void CreateCircle() {
		Point center;
		int radius;
		try {
			center = new Point(Integer.parseInt(this.txtCoordinateX.getText()),
					Integer.parseInt(this.txtCoordinateY.getText()));
			Color background = pnlBackground.getBackground();
			Color border = pnlBorder.getBackground();
			radius = Integer.parseInt(txtRadius.getText());
			circle = new Circle(center, radius, false, border, background);
			IsSuccessful = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Input values must be integers");
			IsSuccessful = false;

		}
	}

	public void FocusRadius() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtRadius.requestFocus();
			}
		});
	}

	private Color getColorFromPicker(String title, Color initialColor) {
		Color color = JColorChooser.showDialog(this, title, initialColor);
		return color;
	}
}
