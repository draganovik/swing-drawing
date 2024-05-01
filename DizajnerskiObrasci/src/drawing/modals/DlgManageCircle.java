package drawing.modals;

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

import drawing.geometry.Circle;

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

	JButton btnApply = new JButton("Save");
	public Circle circle = null;
	private final JPanel contentPanel = new JPanel();
	public boolean IsSuccessful;
	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;

	private JTextField txtRadius;

	/**
	 * Create the dialog.
	 */
	public DlgManageCircle() {
		setTitle("Circle properties");
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

		JLabel lblPoint = new JLabel("Center point");
		lblPoint.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPoint, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblCoordinateX)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtCoordinateX, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblCoordinateY, GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(txtCoordinateY, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblDimensions)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblWidth, GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(txtRadius, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(12)));
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
				.addContainerGap(78, Short.MAX_VALUE)));
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { lblWidth, txtRadius });
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
						updateShape();
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

	public DlgManageCircle(Circle circle) {
		this();
		this.circle = circle;
		txtCoordinateX.setText(Integer.toString(circle.getCenter().getX()));
		txtCoordinateY.setText(Integer.toString(circle.getCenter().getY()));
		txtRadius.setText(Integer.toString(circle.getRadius()));
	}

	public void updateShape() {
		try {
			circle.getCenter().setX(Integer.parseInt(this.txtCoordinateX.getText()));
			circle.getCenter().setY(Integer.parseInt(this.txtCoordinateY.getText()));
			circle.setRadius(Integer.parseInt(this.txtRadius.getText()));
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
}
