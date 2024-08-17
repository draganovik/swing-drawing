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

import drawing.geometry.Line;

public class DlgManageLine extends JDialog {

	private static final long serialVersionUID = 7376104672018791412L;

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

	private JButton btnApply = new JButton("Save");
	private final JPanel contentPanel = new JPanel();
	private Line line = null;
	private JTextField txtEndCoordinateX;
	private JTextField txtEndCoordinateY;
	private JTextField txtStartCoordinateX;

	private JTextField txtStartCoordinateY;

	private boolean IsSuccessful;

	public Boolean IsSuccessful() {
		return this.IsSuccessful;
	}

	/**
	 * Create the dialog.
	 */
	public DlgManageLine() {
		setTitle("Line properties");
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
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStartPoint, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
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
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblEndCoordinateY, GroupLayout.PREFERRED_SIZE, 89,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12).addComponent(txtEndCoordinateY, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(12, Short.MAX_VALUE)));
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
				.addContainerGap(43, Short.MAX_VALUE)));
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

	public DlgManageLine(Line line) {
		this();
		this.line = line;
		txtStartCoordinateX.setText(Integer.toString(line.getStartPoint().getX()));
		txtStartCoordinateY.setText(Integer.toString(line.getStartPoint().getY()));
		txtEndCoordinateX.setText(Integer.toString(line.getEndPoint().getX()));
		txtEndCoordinateY.setText(Integer.toString(line.getEndPoint().getY()));

	}

	public void updateShape() {
		try {
			line.getStartPoint().setX(Integer.parseInt(this.txtStartCoordinateX.getText()));
			line.getStartPoint().setY(Integer.parseInt(this.txtStartCoordinateY.getText()));
			line.getEndPoint().setX(Integer.parseInt(this.txtEndCoordinateX.getText()));
			line.getEndPoint().setY(Integer.parseInt(this.txtEndCoordinateY.getText()));
			IsSuccessful = true;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Invalid Value " + ex.getMessage());
			IsSuccessful = false;
		}
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
