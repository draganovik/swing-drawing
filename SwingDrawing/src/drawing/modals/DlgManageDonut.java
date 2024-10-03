package drawing.modals;

import drawing.geometry.Donut;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgManageDonut extends JDialog {

    private static final long serialVersionUID = 7376104672018791412L;

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

    private JButton btnApply = new JButton("Save");
    private final JPanel contentPanel = new JPanel();
    private Donut donut = null;
    private JTextField txtCoordinateX;
    private JTextField txtCoordinateY;
    private JTextField txtInnerRadius;

    private JTextField txtRadius;

    private boolean IsSuccessful;

    public Boolean IsSuccessful() {
        return this.IsSuccessful;
    }

    /**
     * Create the dialog.
     */
    public DlgManageDonut() {
        setTitle("Donut properties");
        setBounds(100, 100, 300, 300);
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

        JLabel lblPoint = new JLabel("Center point");
        lblPoint.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
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
                                .addGroup(gl_contentPanel.createSequentialGroup()
                                        .addComponent(lblInnerRadius, GroupLayout.PREFERRED_SIZE, 89,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGap(12).addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(43, Short.MAX_VALUE)));
        gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[]{lblCoordinateX, txtCoordinateX});
        gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[]{txtCoordinateY, lblCoordinateY});
        gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[]{lblRadius, txtRadius});
        gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[]{lblInnerRadius, txtInnerRadius});
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

    public DlgManageDonut(Donut donut) {
        this();
        this.donut = donut;
        txtCoordinateX.setText(Integer.toString(donut.getCenter().getX()));
        txtCoordinateY.setText(Integer.toString(donut.getCenter().getY()));
        txtRadius.setText(Integer.toString(donut.getRadius()));
        txtInnerRadius.setText(Integer.toString(donut.getInnerRadius()));
    }

    public void updateShape() {
        try {
            donut.getCenter().setX(Integer.parseInt(this.txtCoordinateX.getText()));
            donut.getCenter().setY(Integer.parseInt(this.txtCoordinateY.getText()));
            donut.setRadius(Integer.parseInt(this.txtRadius.getText()));
            donut.setInnerRadius(Integer.parseInt(this.txtInnerRadius.getText()));
            IsSuccessful = true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Value " + ex.getMessage());
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
