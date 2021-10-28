package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Calculator {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frmIt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JButton btnNumber_0;
	private JButton btnNumber_1;
	private JButton btnNumber_2;
	private JButton btnNumber_3;
	private JButton btnNumber_4;
	private JButton btnNumber_5;
	private JButton btnNumber_6;
	private JButton btnNumber_7;
	private JButton btnNumber_8;
	private JButton btnNumber_9;
	private JButton btnNumber_Comma;
	private JButton btnOperation_Addition;
	private JButton btnOperation_Devision;
	private JButton btnOperation_Equals;
	private JButton btnOperation_Multiplication;
	private JButton btnOperation_Subtraction;
	private JButton btnSpecial_Clear;
	private JButton btnSpecial_Delete;
	private CalcLogic Calculator;
	private JFrame frmIt;
	private JPanel panel;
	private JTextField txtInterface;

	private JTextField txtOperationLog;

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIt = new JFrame();
		frmIt.setResizable(false);
		frmIt.setAlwaysOnTop(true);
		frmIt.setTitle("Calculator IT68/2019");
		frmIt.setBounds(100, 100, 320, 420);
		frmIt.setMinimumSize(new Dimension(300, 300));
		frmIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelPreview = new JPanel();
		panelPreview.setBackground(Color.WHITE);
		frmIt.getContentPane().add(panelPreview, BorderLayout.NORTH);

		txtOperationLog = new JTextField();
		txtOperationLog.setForeground(Color.GRAY);
		txtOperationLog.setHorizontalAlignment(SwingConstants.RIGHT);
		txtOperationLog.setEditable(false);
		txtOperationLog.setColumns(10);

		txtInterface = new JTextField();
		txtInterface.setHorizontalAlignment(SwingConstants.RIGHT);
		txtInterface.setText("0");
		txtInterface.setEditable(false);
		txtInterface.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		txtInterface.setColumns(10);
		panelPreview.setLayout(new BorderLayout(0, 0));
		panelPreview.add(txtOperationLog, BorderLayout.NORTH);
		panelPreview.add(txtInterface, BorderLayout.SOUTH);

		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		frmIt.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		btnNumber_0 = new JButton("0");
		btnNumber_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.TypeIn("0");
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnNumber_0 = new GridBagConstraints();
		gbc_btnNumber_0.gridwidth = 2;
		gbc_btnNumber_0.fill = GridBagConstraints.BOTH;
		gbc_btnNumber_0.gridx = 0;
		gbc_btnNumber_0.gridy = 4;
		panel.add(btnNumber_0, gbc_btnNumber_0);

		btnNumber_1 = new JButton("1");
		btnNumber_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.TypeIn("1");
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnNumber_1 = new GridBagConstraints();
		gbc_btnNumber_1.fill = GridBagConstraints.BOTH;
		gbc_btnNumber_1.gridx = 0;
		gbc_btnNumber_1.gridy = 3;
		panel.add(btnNumber_1, gbc_btnNumber_1);

		btnNumber_2 = new JButton("2");
		btnNumber_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.TypeIn("2");
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnNumber_2 = new GridBagConstraints();
		gbc_btnNumber_2.fill = GridBagConstraints.BOTH;
		gbc_btnNumber_2.gridx = 1;
		gbc_btnNumber_2.gridy = 3;
		panel.add(btnNumber_2, gbc_btnNumber_2);

		btnNumber_3 = new JButton("3");
		btnNumber_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.TypeIn("3");
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnNumber_3 = new GridBagConstraints();
		gbc_btnNumber_3.fill = GridBagConstraints.BOTH;
		gbc_btnNumber_3.gridx = 2;
		gbc_btnNumber_3.gridy = 3;
		panel.add(btnNumber_3, gbc_btnNumber_3);

		btnNumber_4 = new JButton("4");
		btnNumber_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.TypeIn("4");
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnNumber_4 = new GridBagConstraints();
		gbc_btnNumber_4.fill = GridBagConstraints.BOTH;
		gbc_btnNumber_4.gridx = 0;
		gbc_btnNumber_4.gridy = 2;
		panel.add(btnNumber_4, gbc_btnNumber_4);

		btnNumber_5 = new JButton("5");
		btnNumber_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.TypeIn("5");
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnNumber_5 = new GridBagConstraints();
		gbc_btnNumber_5.fill = GridBagConstraints.BOTH;
		gbc_btnNumber_5.gridx = 1;
		gbc_btnNumber_5.gridy = 2;
		panel.add(btnNumber_5, gbc_btnNumber_5);

		btnNumber_6 = new JButton("6");
		btnNumber_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.TypeIn("6");
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnNumber_6 = new GridBagConstraints();
		gbc_btnNumber_6.fill = GridBagConstraints.BOTH;
		gbc_btnNumber_6.gridx = 2;
		gbc_btnNumber_6.gridy = 2;
		panel.add(btnNumber_6, gbc_btnNumber_6);

		btnNumber_7 = new JButton("7");
		btnNumber_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.TypeIn("7");
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnNumber_7 = new GridBagConstraints();
		gbc_btnNumber_7.fill = GridBagConstraints.BOTH;
		gbc_btnNumber_7.gridx = 0;
		gbc_btnNumber_7.gridy = 1;
		panel.add(btnNumber_7, gbc_btnNumber_7);

		btnNumber_8 = new JButton("8");
		btnNumber_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.TypeIn("8");
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnNumber_8 = new GridBagConstraints();
		gbc_btnNumber_8.fill = GridBagConstraints.BOTH;
		gbc_btnNumber_8.gridx = 1;
		gbc_btnNumber_8.gridy = 1;
		panel.add(btnNumber_8, gbc_btnNumber_8);

		btnNumber_9 = new JButton("9");
		btnNumber_9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.TypeIn("9");
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnNumber_9 = new GridBagConstraints();
		gbc_btnNumber_9.fill = GridBagConstraints.BOTH;
		gbc_btnNumber_9.gridx = 2;
		gbc_btnNumber_9.gridy = 1;
		panel.add(btnNumber_9, gbc_btnNumber_9);

		btnSpecial_Clear = new JButton("AC");
		btnSpecial_Clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.Clear();
				txtOperationLog.setText(Calculator.getOperationLog());
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnSpecial_Clear = new GridBagConstraints();
		gbc_btnSpecial_Clear.fill = GridBagConstraints.BOTH;
		gbc_btnSpecial_Clear.gridx = 0;
		gbc_btnSpecial_Clear.gridy = 0;
		panel.add(btnSpecial_Clear, gbc_btnSpecial_Clear);

		btnSpecial_Delete = new JButton("C");
		btnSpecial_Delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.Delete();
				txtInterface.setText(Calculator.getCurrentInput());
			}
		});
		GridBagConstraints gbc_btnSpecial_Delete = new GridBagConstraints();
		gbc_btnSpecial_Delete.fill = GridBagConstraints.BOTH;
		gbc_btnSpecial_Delete.gridx = 1;
		gbc_btnSpecial_Delete.gridy = 0;
		panel.add(btnSpecial_Delete, gbc_btnSpecial_Delete);

		btnOperation_Devision = new JButton("/");
		btnOperation_Devision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calculator.Devide();
				txtOperationLog.setText(Calculator.getOperationLog());
				txtInterface.setText(Calculator.getOperationResult());
			}
		});
		GridBagConstraints gbc_btnOperation_Devision = new GridBagConstraints();
		gbc_btnOperation_Devision.fill = GridBagConstraints.BOTH;
		gbc_btnOperation_Devision.gridx = 2;
		gbc_btnOperation_Devision.gridy = 0;
		panel.add(btnOperation_Devision, gbc_btnOperation_Devision);

		btnOperation_Multiplication = new JButton("X");
		btnOperation_Multiplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calculator.Multiply();;
				txtOperationLog.setText(Calculator.getOperationLog());
				txtInterface.setText(Calculator.getOperationResult());
			}
		});
		GridBagConstraints gbc_btnOperation_Multiplication = new GridBagConstraints();
		gbc_btnOperation_Multiplication.fill = GridBagConstraints.BOTH;
		gbc_btnOperation_Multiplication.gridx = 3;
		gbc_btnOperation_Multiplication.gridy = 0;
		panel.add(btnOperation_Multiplication, gbc_btnOperation_Multiplication);

		btnOperation_Subtraction = new JButton("-");
		btnOperation_Subtraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calculator.Subtract();
				txtOperationLog.setText(Calculator.getOperationLog());
				txtInterface.setText(Calculator.getOperationResult());
			}
		});
		GridBagConstraints gbc_btnOperation_Subtraction = new GridBagConstraints();
		gbc_btnOperation_Subtraction.fill = GridBagConstraints.BOTH;
		gbc_btnOperation_Subtraction.gridx = 3;
		gbc_btnOperation_Subtraction.gridy = 1;
		panel.add(btnOperation_Subtraction, gbc_btnOperation_Subtraction);

		btnOperation_Addition = new JButton("+");
		btnOperation_Addition.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.Add();
				txtOperationLog.setText(Calculator.getOperationLog());
				txtInterface.setText(Calculator.getOperationResult());

			}
		});
		GridBagConstraints gbc_btnOperation_Addition = new GridBagConstraints();
		gbc_btnOperation_Addition.fill = GridBagConstraints.BOTH;
		gbc_btnOperation_Addition.gridx = 3;
		gbc_btnOperation_Addition.gridy = 2;
		panel.add(btnOperation_Addition, gbc_btnOperation_Addition);

		btnNumber_Comma = new JButton(".");
		btnNumber_Comma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.TypeIn(".");
			}
		});
		GridBagConstraints gbc_btnNumber_Comma = new GridBagConstraints();
		gbc_btnNumber_Comma.fill = GridBagConstraints.BOTH;
		gbc_btnNumber_Comma.gridx = 2;
		gbc_btnNumber_Comma.gridy = 4;
		panel.add(btnNumber_Comma, gbc_btnNumber_Comma);

		btnOperation_Equals = new JButton("=");
		btnOperation_Equals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.Equalize();
				txtOperationLog.setText(Calculator.getOperationLog());
				txtInterface.setText(Calculator.getOperationResult());
			}
		});
		GridBagConstraints gbc_btnOperation_Equals = new GridBagConstraints();
		gbc_btnOperation_Equals.gridheight = 2;
		gbc_btnOperation_Equals.fill = GridBagConstraints.BOTH;
		gbc_btnOperation_Equals.gridx = 3;
		gbc_btnOperation_Equals.gridy = 3;
		panel.add(btnOperation_Equals, gbc_btnOperation_Equals);

		Calculator = new CalcLogic();
	}

}
