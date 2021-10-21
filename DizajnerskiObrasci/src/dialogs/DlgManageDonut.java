package dialogs;

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

import geometry.Donut;
import geometry.Point;

public class DlgManageDonut extends JDialog {

	private static final long serialVersionUID = 7376104672018791412L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private JPanel pnlBackground;
	private JPanel pnlBorder;
	public boolean IsSuccessful;
	public Donut donut = null;
	JButton btnApply = new JButton("Add");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgManageDonut dialog = new DlgManageDonut();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgManageDonut() {
		setTitle("Add a donut");
		setBounds(100, 100, 300, 290);
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

		JLabel lblRadius = new JLabel("Radius: ");
		lblRadius.setForeground(Color.DARK_GRAY);

		txtRadius = new JTextField();
		txtRadius.setColumns(10);

		JLabel lblInnerRadius = new JLabel("Inner radius: ");
		lblInnerRadius.setForeground(Color.DARK_GRAY);

		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);

		JLabel lblDimensions = new JLabel("Dimensions");
		lblDimensions.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel lblPoint = new JLabel("Upper left corner");
		lblPoint.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel lblColors = new JLabel("Colors");
		lblColors.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnBackground = new JButton("Background");
		btnBackground.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color initialBackground = pnlBackground.getBackground();
				Color background = getColorFromPicker("Choose Donut Background", initialBackground);
				pnlBackground.setBackground(background);
			}
		});

		JButton btnBorder = new JButton("Border");
		btnBorder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color initialBorder = pnlBorder.getBackground();
				Color border = getColorFromPicker("Choose Donut Border", initialBorder);
				pnlBorder.setBackground(border);
			}
		});

		pnlBackground = new JPanel();
		pnlBackground.setBackground(Color.LIGHT_GRAY);

		pnlBorder = new JPanel();
		pnlBorder.setBackground(Color.DARK_GRAY);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPoint, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblCoordinateX)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(txtCoordinateX,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblCoordinateY, GroupLayout.PREFERRED_SIZE, 89,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12).addComponent(txtCoordinateY, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblDimensions)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(12).addComponent(txtRadius, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblColors, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblInnerRadius, GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnBackground).addComponent(btnBorder))
								.addGap(12)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(pnlBorder, GroupLayout.PREFERRED_SIZE, 130,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(pnlBackground, GroupLayout.DEFAULT_SIZE, 130,
														Short.MAX_VALUE)))))
						.addContainerGap(12, Short.MAX_VALUE)));
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
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblRadius))
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblInnerRadius))
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(18)
								.addComponent(lblColors, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnBackground)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBorder)
								.addContainerGap(8, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(pnlBackground, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(pnlBorder, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addGap(21)))));
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { lblInnerRadius, txtInnerRadius });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { lblRadius, txtRadius });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { txtCoordinateY, lblCoordinateY });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { lblCoordinateX, txtCoordinateX });
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnApply.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CreateDonut();
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

	public DlgManageDonut(Donut donut, boolean isEdit) {
		this();
		if (!isEdit) {
			setTitle("Remove donut");
			txtCoordinateX.setEditable(false);
			txtCoordinateY.setEditable(false);
			txtRadius.setEditable(false);
			txtInnerRadius.setEditable(false);
			btnApply.setText("Remove");
			IsSuccessful = true;
		} else {
			setTitle("Update donut");
			btnApply.setText("Save");
			setBounds(100, 100, 300, 400);
		}
		pnlBorder.setBackground(donut.getColor());
		pnlBackground.setBackground(donut.getBackgroundColor());
		txtCoordinateX.setText(Integer.toString(donut.getCenter().getX()));
		txtCoordinateY.setText(Integer.toString(donut.getCenter().getY()));
		txtRadius.setText(Integer.toString(donut.getRadius()));
		txtInnerRadius.setText(Integer.toString(donut.getInnerRadius()));
	}

	public DlgManageDonut(Point releasedPoint) {
		this();
		setBounds(100, 100, 300, 400);
		txtCoordinateX.setText(Integer.toString(releasedPoint.getX()));
		txtCoordinateY.setText(Integer.toString(releasedPoint.getY()));

	}

	public void CreateDonut() {
		Point ulp;
		int radius;
		int innerRadius;
		try {
			ulp = new Point(Integer.parseInt(this.txtCoordinateX.getText()),
					Integer.parseInt(this.txtCoordinateY.getText()));
			radius = Integer.parseInt(this.txtRadius.getText());
			innerRadius = Integer.parseInt(this.txtInnerRadius.getText());
			Color background = pnlBackground.getBackground();
			Color border = pnlBorder.getBackground();
			donut = new Donut(ulp, radius, innerRadius, false, border, background);
			IsSuccessful = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"Input values must be integers,\nradius must be larger than inner radius by 3");
			IsSuccessful = false;

		}
	}

	private Color getColorFromPicker(String title, Color initialColor) {
		Color color = JColorChooser.showDialog(this, title, initialColor);
		return color;
	}

	public void FocusRadius() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtRadius.requestFocus();
			}
		});
	}
}
