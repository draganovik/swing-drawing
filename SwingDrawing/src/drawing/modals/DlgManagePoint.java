package drawing.modals;

import drawing.geometry.Point;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JButton btnApply = new JButton("Save");
    private final JPanel contentPanel = new JPanel();
    private Point point = null;
    private JTextField txtCoordinateX;

    private JTextField txtCoordinateY;

    private boolean IsSuccessful;

    public Boolean IsSuccessful() {
        return this.IsSuccessful;
    }

    /**
     * Create the dialog.
     */
    public DlgManagePoint() {
        setTitle("Point properties");
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
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel
                .setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblCoordinateX)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(txtCoordinateX, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                .addComponent(lblCoordinateY, GroupLayout.PREFERRED_SIZE, 89,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(12).addComponent(txtCoordinateY, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(12, Short.MAX_VALUE)));
        gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
                .createSequentialGroup().addContainerGap()
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(txtCoordinateX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCoordinateX))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblCoordinateY))
                        .addComponent(txtCoordinateY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE)));
        gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[]{lblCoordinateX, txtCoordinateX});
        gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[]{txtCoordinateY, lblCoordinateY});
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

    public DlgManagePoint(Point point) {
        this();
        this.point = point;
        txtCoordinateX.setText(Integer.toString(point.getX()));
        txtCoordinateY.setText(Integer.toString(point.getY()));
    }

    public void updateShape() {
        try {
            point.setX(Integer.parseInt(txtCoordinateX.getText()));
            point.setY(Integer.parseInt(txtCoordinateY.getText()));
            IsSuccessful = true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Value " + ex.getMessage());
            IsSuccessful = false;
        }
    }
}
