package sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import dialogs.DlgManageRectangle;
import geometry.Rectangle;

public class FrameSort extends JFrame {

	private static final long serialVersionUID = 4438924786414008025L;
	private JPanel cnpMain;
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	private DefaultListModel<String> dlm = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameSort frame = new FrameSort();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameSort() {
		setTitle("Draganović Mladen - IT68/2019");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		cnpMain = new JPanel();
		cnpMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cnpMain);
		cnpMain.setLayout(new BorderLayout(0, 0));

		JPanel pnlControls = new JPanel();
		cnpMain.add(pnlControls, BorderLayout.SOUTH);

		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rectangles.size() > 1) {
					Collections.sort(rectangles);
					dlm.clear();
					for (Rectangle rect : rectangles) {
						dlm.addElement(rect.toString());
					}

				} else {
					showMessage("There needs to be at least two rectangles for sorting to work");
				}
			}
		});
		pnlControls.add(btnSort);
		JList<String> lstRectangles = new JList<String>();
		lstRectangles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JButton btnAdd = new JButton("Add rectangle");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgManageRectangle modal = new DlgManageRectangle();
				modal.setVisible(true);
				if (modal.IsSuccessful) {
					addRectangle(modal.rectangle);
				}
			}
		});
		pnlControls.add(btnAdd);

		JScrollPane scpRectangles = new JScrollPane();
		cnpMain.add(scpRectangles, BorderLayout.CENTER);
		scpRectangles.setViewportView(lstRectangles);
		lstRectangles.setModel(dlm);
	}

	private void addRectangle(Rectangle r) {
		rectangles.add(0, r);
		dlm.add(0, r.toString());
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
