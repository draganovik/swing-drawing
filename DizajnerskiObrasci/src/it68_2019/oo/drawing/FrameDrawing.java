package it68_2019.oo.drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import it68_2019.oo.geometry.Circle;
import it68_2019.oo.geometry.Donut;
import it68_2019.oo.geometry.Line;
import it68_2019.oo.geometry.Point;
import it68_2019.oo.geometry.Rectangle;
import it68_2019.oo.modals.DlgManageCircle;
import it68_2019.oo.modals.DlgManageDonut;
import it68_2019.oo.modals.DlgManageLine;
import it68_2019.oo.modals.DlgManagePoint;
import it68_2019.oo.modals.DlgManageRectangle;

public class FrameDrawing extends JFrame {

	private static final long serialVersionUID = 2346451530530650446L;
	private JPanel contentPane;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private Point pressedPoint = new Point();
	private Point releasedPoint = new Point();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameDrawing frame = new FrameDrawing();
					frame.setMinimumSize(new Dimension(880, 420));
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
	public FrameDrawing() {
		setTitle("Draganović Mladen - IT68/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelControls = new JPanel();
		contentPane.add(panelControls, BorderLayout.NORTH);

		PnlDrawing pnlDrawing = new PnlDrawing();
		pnlDrawing.setBackground(Color.WHITE);
		contentPane.add(pnlDrawing, BorderLayout.CENTER);

		JButton btnClear = new JButton("Clear board");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pnlDrawing.count() > 0) {
					int dialogResult = ShowWarning("Are you sure you want to clear the whole board?");
					if (dialogResult == JOptionPane.YES_OPTION) {
						pnlDrawing.clear();
					}
				}
			}
		});
		panelControls.add(btnClear);

		JToggleButton tglSelection = new JToggleButton("Selection");
		panelControls.add(tglSelection);
		buttonGroup.add(tglSelection);

		JToggleButton tglPoint = new JToggleButton("Point");
		panelControls.add(tglPoint);
		buttonGroup.add(tglPoint);

		JToggleButton tglLine = new JToggleButton("Line");
		panelControls.add(tglLine);
		buttonGroup.add(tglLine);

		JToggleButton tglRectangle = new JToggleButton("Rectangle");
		panelControls.add(tglRectangle);
		buttonGroup.add(tglRectangle);

		JToggleButton tglCircle = new JToggleButton("Circle");
		panelControls.add(tglCircle);
		buttonGroup.add(tglCircle);

		JToggleButton tlgDonut = new JToggleButton("Donut");
		panelControls.add(tlgDonut);
		buttonGroup.add(tlgDonut);

		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pnlDrawing.getSelectedShape() instanceof Point) {
					DlgManagePoint modal = new DlgManagePoint((Point) pnlDrawing.getSelectedShape(), true);
					ShowDialog(modal);
					if (modal.IsSuccessful) {
						pnlDrawing.updateShape(modal.point);
					}
				} else if (pnlDrawing.getSelectedShape() instanceof Line) {
					DlgManageLine modal = new DlgManageLine((Line) pnlDrawing.getSelectedShape(), true);
					ShowDialog(modal);
					if (modal.IsSuccessful) {
						pnlDrawing.updateShape(modal.line);
					}
				} else if (pnlDrawing.getSelectedShape() instanceof Rectangle) {
					DlgManageRectangle modal = new DlgManageRectangle((Rectangle) pnlDrawing.getSelectedShape(),
							true);
					ShowDialog(modal);
					if (modal.IsSuccessful) {
						pnlDrawing.updateShape(modal.rectangle);
					}
				} else if (pnlDrawing.getSelectedShape() instanceof Donut) {
					DlgManageDonut modal = new DlgManageDonut((Donut) pnlDrawing.getSelectedShape(), true);
					ShowDialog(modal);
					if (modal.IsSuccessful) {
						pnlDrawing.updateShape(modal.donut);
					}
				} else if (pnlDrawing.getSelectedShape() instanceof Circle) {
					DlgManageCircle modal = new DlgManageCircle((Circle) pnlDrawing.getSelectedShape(), true);
					ShowDialog(modal);
					if (modal.IsSuccessful) {
						pnlDrawing.updateShape(modal.circle);
					}
				}
			}
		});
		btnModify.setEnabled(false);
		panelControls.add(btnModify);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = ShowWarning("Are you sure you want to delete this Shape?");
				if (dialogResult == JOptionPane.YES_OPTION) {
					pnlDrawing.removeSelected();
					if (pnlDrawing.isItemSelected()) {
						btnModify.setEnabled(true);
						btnDelete.setEnabled(true);
					} else {
						btnModify.setEnabled(false);
						btnDelete.setEnabled(false);
					}
				}
			}
		});
		btnDelete.setEnabled(false);
		panelControls.add(btnDelete);

		pnlDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pressedPoint = new Point(e.getX(), e.getY());
				if (tglPoint.isSelected()) {
					pnlDrawing.add(pressedPoint);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				releasedPoint = new Point(e.getX(), e.getY());
				if (tglSelection.isSelected()) {
					pnlDrawing.select(releasedPoint);
					if (pnlDrawing.isItemSelected()) {
						btnModify.setEnabled(true);
						btnDelete.setEnabled(true);
					} else {
						btnModify.setEnabled(false);
						btnDelete.setEnabled(false);
					}

				} else if (tglLine.isSelected()) {
					if (Math.abs(pressedPoint.distanceOf(releasedPoint)) < 10) {
						DlgManageLine modal = new DlgManageLine(pressedPoint);
						modal.FocusEndCoordinateX();
						ShowDialog(modal);
						if (modal.IsSuccessful) {
							pnlDrawing.add(modal.line);
						}
					} else {
						pnlDrawing.add(new Line(pressedPoint, releasedPoint));
					}
				} else if (tglRectangle.isSelected()) {
					if (Math.abs(pressedPoint.distanceOf(releasedPoint)) < 10) {
						DlgManageRectangle modal = new DlgManageRectangle(pressedPoint);
						modal.FocusWidth();
						ShowDialog(modal);
						if (modal.IsSuccessful) {
							pnlDrawing.add(modal.rectangle);
						}
					} else {
						Point ulPoint = new Point(Math.min(pressedPoint.getX(), releasedPoint.getX()),
								Math.min(pressedPoint.getY(), releasedPoint.getY()));
						pnlDrawing.add(new Rectangle(ulPoint, pressedPoint.distanceByXOf(releasedPoint),
								pressedPoint.distanceByYOf(releasedPoint)));
					}

				} else if (tglCircle.isSelected()) {
					if (Math.abs(pressedPoint.distanceOf(releasedPoint)) < 10) {
						DlgManageCircle modal = new DlgManageCircle(pressedPoint);
						modal.FocusRadius();
						ShowDialog(modal);
						if (modal.IsSuccessful) {
							pnlDrawing.add(modal.circle);
						}
					} else {
						int radius = (int) Math.abs(pressedPoint.distanceOf(releasedPoint));
						pnlDrawing.add(new Circle(pressedPoint, radius));
					}
				} else if (tlgDonut.isSelected()) {
					if (Math.abs(pressedPoint.distanceOf(releasedPoint)) < 10) {
						DlgManageDonut modal = new DlgManageDonut(pressedPoint);
						modal.FocusRadius();
						ShowDialog(modal);
						if (modal.IsSuccessful) {
							pnlDrawing.add(modal.donut);
						}
					} else {
						int size = (int) Math.abs(pressedPoint.distanceOf(releasedPoint));
						pnlDrawing.add(new Donut(pressedPoint, size, size / 2));
					}
				}

			}
		});
	}

	private void ShowDialog(JDialog modal) {
		modal.pack();
		modal.setLocationRelativeTo(this);
		modal.setVisible(true);
	}

	private int ShowWarning(String message) {
		int dialogResult = JOptionPane.showConfirmDialog(this, message, "Warning", JOptionPane.YES_NO_OPTION);
		return dialogResult;
	}
}
