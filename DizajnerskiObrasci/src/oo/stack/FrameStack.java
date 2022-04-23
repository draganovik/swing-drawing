package oo.stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import oo.geometry.Rectangle;
import oo.modals.DlgManageRectangle;

public class FrameStack extends JFrame {

	private static final long serialVersionUID = 4438924786414008025L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameStack frame = new FrameStack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel cnpMain;
	private DefaultListModel<String> dlm = new DefaultListModel<>();

	private Stack<Rectangle> rectangles = new Stack<>();

	/**
	 * Create the frame.
	 */
	public FrameStack() {
		setTitle("Draganović Mladen - IT68/2019");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		cnpMain = new JPanel();
		cnpMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cnpMain);
		cnpMain.setLayout(new BorderLayout(0, 0));

		JPanel pnlControls = new JPanel();
		cnpMain.add(pnlControls, BorderLayout.SOUTH);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rectangles.size() > 0) {
					DlgManageRectangle modal = new DlgManageRectangle(rectangles.peek(), false);
					modal.setVisible(true);
					if (modal.IsSuccessful) {
						removeLastRectangle();
					}
				} else {
					showMessage("There are no rectangles in stack");
				}
			}
		});
		pnlControls.add(btnRemove);
		JList<String> lstRectangles = new JList<>();
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
		rectangles.push(r);
		dlm.add(0, r.toString());
	}

	private void removeLastRectangle() {
		if (rectangles.size() > 0) {
			rectangles.pop();
			dlm.remove(0);
		}
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
