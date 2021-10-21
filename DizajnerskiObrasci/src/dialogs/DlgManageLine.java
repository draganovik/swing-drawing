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

import geometry.Line;
import geometry.Point;

public class DlgManageLine extends JDialog {

	private static final long serialVersionUID = 7376104672018791412L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartCoordinateX;
	private JTextField txtStartCoordinateY;
	private JTextField txtEndCoordinateX;
	private JTextField txtEndCoordinateY;
	private JPanel pnlColor;
	public boolean IsSuccessful;
	public Line line = null;
	JButton btnApply = new JButton("Add");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgManageLine dialog = new DlgManageLine();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgManageLine() {
		setTitle("Add a line");
		setBounds(100, 100, 300, 300);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblStartCoordinateX = new JLabel("Start Coordinate X: ");
		lblStartCoordinateX.setForeground(Color.DARK_GRAY);

		txtStartCoordinateX = new JTextField();
		txtStartCoordinateX.setColumns(10);

		txtStartCoordinateY = new JTextField();
		txtStartCoordinateY.setColumns(10);

		JLabel lblStartCoordinateY = new JLabel("Start Coordinate Y: ");
		lblStartCoordinateY.setForeground(Color.DARK_GRAY);

		JLabel lblEndCoordinateX = new JLabel("End CoordinateX: ");
		lblEndCoordinateX.setForeground(Color.DARK_GRAY);

		txtEndCoordinateX = new JTextField();
		txtEndCoordinateX.setColumns(10);

		JLabel lblEndCoordinateY = new JLabel("End CoordinateY: ");
		lblEndCoordinateY.setForeground(Color.DARK_GRAY);

		txtEndCoordinateY = new JTextField();
		txtEndCoordinateY.setColumns(10);

		JLabel lblEndPoint = new JLabel("End point");
		lblEndPoint.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel lblStartPoint = new JLabel("Start point");
		lblStartPoint.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel lblColors = new JLabel("Style");
		lblColors.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color initialColor = pnlColor.getBackground();
				Color color = getColorFromPicker("Choose Line Color", initialColor);
				pnlColor.setBackground(color);
			}
		});

		pnlColor = new JPanel();
		pnlColor.setBackground(Color.DARK_GRAY);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStartPoint, GroupLayout.PREFERRED_SIZE, 150,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblStartCoordinateX)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(txtStartCoordinateX,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblStartCoordinateY, GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(txtStartCoordinateY, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblEndPoint)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblEndCoordinateX, GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(txtEndCoordinateX, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblColors, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblEndCoordinateY, GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(txtEndCoordinateY, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(12, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup().addComponent(btnColor)
								.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
								.addComponent(pnlColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addGap(12)))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap()
				.addComponent(lblStartPoint, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtStartCoordinateX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStartCoordinateX))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblStartCoordinateY))
						.addComponent(txtStartCoordinateY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(lblEndPoint).addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblEndCoordinateX))
						.addComponent(txtEndCoordinateX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblEndCoordinateY))
						.addComponent(txtEndCoordinateY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(24).addComponent(lblColors, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(btnColor)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(pnlColor,
								GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(111, Short.MAX_VALUE)));
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { lblEndCoordinateY, txtEndCoordinateY });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { lblEndCoordinateX, txtEndCoordinateX });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { txtStartCoordinateY, lblStartCoordinateY });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { lblStartCoordinateX, txtStartCoordinateX });
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnApply.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CreateLine();
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

	public DlgManageLine(Line line, boolean isEdit) {
		this();
		if (!isEdit) {
			setTitle("Remove line");
			txtStartCoordinateX.setEditable(false);
			txtStartCoordinateY.setEditable(false);
			txtEndCoordinateX.setEditable(false);
			txtEndCoordinateY.setEditable(false);
			btnApply.setText("Remove");
			IsSuccessful = true;
		} else {
			setTitle("Update line");
			btnApply.setText("Save");
			setBounds(100, 100, 300, 380);
		}
		pnlColor.setBackground(line.getColor());
		txtStartCoordinateX.setText(Integer.toString(line.getStartPoint().getX()));
		txtStartCoordinateY.setText(Integer.toString(line.getStartPoint().getY()));
		txtEndCoordinateX.setText(Integer.toString(line.getEndPoint().getX()));
		txtEndCoordinateY.setText(Integer.toString(line.getEndPoint().getY()));
	}

	public DlgManageLine(Point releasedPoint) {
		this();
		setBounds(100, 100, 300, 380);
		txtStartCoordinateX.setText(Integer.toString(releasedPoint.getX()));
		txtStartCoordinateY.setText(Integer.toString(releasedPoint.getY()));

	}

	public void CreateLine() {
		Point start;
		int endCoordinateX;
		int endCoordinateY;
		try {
			start = new Point(Integer.parseInt(this.txtStartCoordinateX.getText()),
					Integer.parseInt(this.txtStartCoordinateY.getText()));
			endCoordinateX = Integer.parseInt(this.txtEndCoordinateX.getText());
			endCoordinateY = Integer.parseInt(this.txtEndCoordinateY.getText());
			Color color = pnlColor.getBackground();
			line = new Line(start, new Point(endCoordinateX, endCoordinateY), false, color);
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

	public void FocusEndCoordinateX() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtEndCoordinateX.requestFocus();
			}
		});
	}
}
