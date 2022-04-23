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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import oo.geometry.Point;

public class DlgManagePoint extends JDialog {

	private static final long serialVersionUID = 7376104672018791412L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgManagePoint dialog = new DlgManagePoint();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	JButton btnApply = new JButton("Add");
	private final JPanel contentPanel = new JPanel();
	public boolean IsSuccessful;
	private JPanel pnlColor;
	public Point point = null;
	private JTextField txtCoordinateX;

	private JTextField txtCoordinateY;

	/**
	 * Create the dialog.
	 */
	public DlgManagePoint() {
		setTitle("Add a point");
		setBounds(100, 100, 300, 180);
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

		JLabel lblStartPoint = new JLabel("Start point");
		lblStartPoint.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel lblColors = new JLabel("Style");
		lblColors.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color initialColor = pnlColor.getBackground();
				Color color = getColorFromPicker("Choose Point Color", initialColor);
				pnlColor.setBackground(color);
			}
		});

		pnlColor = new JPanel();
		pnlColor.setBackground(Color.DARK_GRAY);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStartPoint, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblCoordinateX)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(txtCoordinateX, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(lblCoordinateY, GroupLayout.PREFERRED_SIZE, 89,
														GroupLayout.PREFERRED_SIZE)
												.addGap(12).addComponent(txtCoordinateY, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(12, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING)
								.addComponent(lblColors, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(btnColor)
										.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE).addComponent(
												pnlColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
								.addGap(12)))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap()
				.addComponent(lblStartPoint, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
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
				.addGap(18).addComponent(lblColors, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(btnColor)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(pnlColor,
								GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(215, Short.MAX_VALUE)));
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { lblCoordinateX, txtCoordinateX });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { txtCoordinateY, lblCoordinateY });
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnApply.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CreatePoint();
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

	public DlgManagePoint(Point releasedPoint) {
		this();
		setBounds(100, 100, 300, 270);
		txtCoordinateX.setText(Integer.toString(releasedPoint.getX()));
		txtCoordinateY.setText(Integer.toString(releasedPoint.getY()));

	}

	public DlgManagePoint(Point point, boolean isEdit) {
		this();
		if (!isEdit) {
			setTitle("Remove point");
			txtCoordinateX.setEditable(false);
			txtCoordinateY.setEditable(false);
			btnApply.setText("Remove");
			IsSuccessful = true;
		} else {
			setTitle("Update point");
			btnApply.setText("Save");
			setBounds(100, 100, 300, 270);
		}
		pnlColor.setBackground(point.getColor());
		txtCoordinateX.setText(Integer.toString(point.getX()));
		txtCoordinateY.setText(Integer.toString(point.getY()));
	}

	public void CreatePoint() {
		try {
			Color color = pnlColor.getBackground();
			point = new Point(Integer.parseInt(this.txtCoordinateX.getText()),
					Integer.parseInt(this.txtCoordinateY.getText()), false, color);
			IsSuccessful = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Input values must be integers");
			IsSuccessful = false;

		}
	}

	private Color getColorFromPicker(String title, Color initialColor) {
		Color color = JColorChooser.showDialog(this, title, initialColor);
		return color;
	}
}
