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

import oo.geometry.Point;
import oo.geometry.Rectangle;

public class DlgManageRectangle extends JDialog {

	private static final long serialVersionUID = 7376104672018791412L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgManageRectangle dialog = new DlgManageRectangle();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	JButton btnApply = new JButton("Add");
	private final JPanel contentPanel = new JPanel();
	public boolean IsSuccessful;
	private JPanel pnlBackground;
	private JPanel pnlBorder;
	public Rectangle rectangle = null;
	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;
	private JTextField txtHeight;

	private JTextField txtWidth;

	/**
	 * Create the dialog.
	 */
	public DlgManageRectangle() {
		setTitle("Add a rectangle");
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

		JLabel lblWidth = new JLabel("Width: ");
		lblWidth.setForeground(Color.DARK_GRAY);

		txtWidth = new JTextField();
		txtWidth.setColumns(10);

		JLabel lblHeight = new JLabel("Height: ");
		lblHeight.setForeground(Color.DARK_GRAY);

		txtHeight = new JTextField();
		txtHeight.setColumns(10);

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
				Color background = getColorFromPicker("Choose Rectangle Background", initialBackground);
				pnlBackground.setBackground(background);
			}
		});

		JButton btnBorder = new JButton("Border");
		btnBorder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color initialBorder = pnlBorder.getBackground();
				Color border = getColorFromPicker("Choose Rectangle Border", initialBorder);
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
								.addComponent(lblWidth, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(12).addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(lblColors, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblHeight, GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnBackground).addComponent(btnBorder))
								.addGap(12)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(pnlBorder, GroupLayout.PREFERRED_SIZE, 130,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE,
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
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblWidth))
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblHeight))
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { lblHeight, txtHeight });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { lblWidth, txtWidth });
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
						CreateRectangle();
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

	public DlgManageRectangle(Point releasedPoint) {
		this();
		setBounds(100, 100, 300, 400);
		txtCoordinateX.setText(Integer.toString(releasedPoint.getX()));
		txtCoordinateY.setText(Integer.toString(releasedPoint.getY()));

	}

	public DlgManageRectangle(Rectangle rectangle, boolean isEdit) {
		this();
		if (!isEdit) {
			setTitle("Remove rectangle");
			txtCoordinateX.setEditable(false);
			txtCoordinateY.setEditable(false);
			txtWidth.setEditable(false);
			txtHeight.setEditable(false);
			btnApply.setText("Remove");
			IsSuccessful = true;
		} else {
			setTitle("Update rectangle");
			btnApply.setText("Save");
			setBounds(100, 100, 300, 400);
		}
		pnlBorder.setBackground(rectangle.getColor());
		pnlBackground.setBackground(rectangle.getBackgroundColor());
		txtCoordinateX.setText(Integer.toString(rectangle.getUpperLeftPoint().getX()));
		txtCoordinateY.setText(Integer.toString(rectangle.getUpperLeftPoint().getY()));
		txtWidth.setText(Integer.toString(rectangle.getWidth()));
		txtHeight.setText(Integer.toString(rectangle.getHeight()));
	}

	public void CreateRectangle() {
		Point ulp;
		int width;
		int height;
		try {
			ulp = new Point(Integer.parseInt(this.txtCoordinateX.getText()),
					Integer.parseInt(this.txtCoordinateY.getText()));
			width = Integer.parseInt(this.txtWidth.getText());
			height = Integer.parseInt(this.txtHeight.getText());
			Color background = pnlBackground.getBackground();
			Color border = pnlBorder.getBackground();
			rectangle = new Rectangle(ulp, width, height, false, border, background);
			IsSuccessful = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Input values must be integers");
			IsSuccessful = false;

		}
	}

	public void FocusWidth() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtWidth.requestFocus();
			}
		});
	}

	private Color getColorFromPicker(String title, Color initialColor) {
		Color color = JColorChooser.showDialog(this, title, initialColor);
		return color;
	}
}
