package drawing.modals;

import drawing.geometry.Point;
import drawing.geometry.Rectangle;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JButton btnApply = new JButton("Save");
    private final JPanel contentPanel = new JPanel();
    private Rectangle rectangle = null;
    private JTextField txtCoordinateX;
    private JTextField txtCoordinateY;
    private JTextField txtHeight;

    private JTextField txtWidth;

    private boolean IsSuccessful;

    public Boolean IsSuccessful() {
        return this.IsSuccessful;
    }

    /**
     * Create the dialog.
     */
    public DlgManageRectangle() {
        setTitle("Rectangle properties");
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

        JLabel lblPoint = new JLabel("Upper left point");
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
                                        .addComponent(lblWidth, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                        .addGap(12).addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPanel.createSequentialGroup()
                                        .addComponent(lblHeight, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                        .addGap(12).addComponent(txtHeight, GroupLayout.PREFERRED_SIZE,
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
                        .addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblWidth))
                        .addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup().addGap(5).addComponent(lblHeight))
                        .addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE)));
        gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[]{lblCoordinateX, txtCoordinateX});
        gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[]{txtCoordinateY, lblCoordinateY});
        gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[]{lblWidth, txtWidth});
        gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[]{lblHeight, txtHeight});
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

    public DlgManageRectangle(Point releasedPoint) {
        this();
        setBounds(100, 100, 300, 400);
        txtCoordinateX.setText(Integer.toString(releasedPoint.getX()));
        txtCoordinateY.setText(Integer.toString(releasedPoint.getY()));

    }

    public DlgManageRectangle(Rectangle rectangle) {
        this();
        this.rectangle = rectangle;
        txtCoordinateX.setText(Integer.toString(rectangle.getUpperLeftPoint().getX()));
        txtCoordinateY.setText(Integer.toString(rectangle.getUpperLeftPoint().getY()));
        txtWidth.setText(Integer.toString(rectangle.getWidth()));
        txtHeight.setText(Integer.toString(rectangle.getHeight()));
    }

    public void updateShape() {
        try {
            rectangle.getUpperLeftPoint().setX(Integer.parseInt(this.txtCoordinateX.getText()));
            rectangle.getUpperLeftPoint().setY(Integer.parseInt(this.txtCoordinateY.getText()));
            rectangle.setWidth(Integer.parseInt(this.txtWidth.getText()));
            rectangle.setHeight(Integer.parseInt(this.txtHeight.getText()));
            IsSuccessful = true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Value " + ex.getMessage());
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
}
