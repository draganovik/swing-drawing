package drawing.mvc.views;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import drawing.mvc.DrawingController;
import drawing.mvc.models.WorkspaceModel;
import drawing.types.ToolAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class ToolbarView extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 8181676546869383767L;
    private ButtonGroup actionsButtonGroup = new ButtonGroup();
    private JButton btnToolbarBackground;
    private JToggleButton btnToolbarCircle;
    private JButton btnToolbarColor;
    private JButton btnToolbarDelete;
    private JToggleButton btnToolbarDonut;
    private JToggleButton btnToolbarHexagon;
    private JToggleButton btnToolbarLine;
    private JButton btnToolbarModify;
    private JToggleButton btnToolbarPoint;
    private JToggleButton btnToolbarRectangle;
    // Command Buttons
    private JToggleButton btnToolbarSelect;
    private DrawingController controller;
    private JButton btnToolbarLoadNext;

    /**
     * Create the panel.
     */
    public ToolbarView() {

        btnToolbarSelect = new JToggleButton(new ImageIcon(getClass().getResource("/images/Select.png")));
        btnToolbarSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setToolAction(ToolAction.SELECT);
            }
        });
        btnToolbarSelect.setSelected(true);
        actionsButtonGroup.add(btnToolbarSelect);

        btnToolbarPoint = new JToggleButton(new ImageIcon(getClass().getResource("/images/Point.png")));
        btnToolbarPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setToolAction(ToolAction.POINT);
            }
        });
        actionsButtonGroup.add(btnToolbarPoint);

        btnToolbarLine = new JToggleButton(new ImageIcon(getClass().getResource("/images/Line.png")));
        btnToolbarLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setToolAction(ToolAction.LINE);
            }
        });
        actionsButtonGroup.add(btnToolbarLine);

        btnToolbarRectangle = new JToggleButton(new ImageIcon(getClass().getResource("/images/Rectangle.png")));
        btnToolbarRectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setToolAction(ToolAction.RECTANGLE);
            }
        });
        actionsButtonGroup.add(btnToolbarRectangle);

        btnToolbarCircle = new JToggleButton(new ImageIcon(getClass().getResource("/images/Circle.png")));
        btnToolbarCircle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setToolAction(ToolAction.CIRCLE);
            }
        });
        actionsButtonGroup.add(btnToolbarCircle);

        btnToolbarDonut = new JToggleButton(new ImageIcon(getClass().getResource("/images/Donut.png")));
        btnToolbarDonut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setToolAction(ToolAction.DONUT);
            }
        });
        actionsButtonGroup.add(btnToolbarDonut);

        btnToolbarHexagon = new JToggleButton(new ImageIcon(getClass().getResource("/images/Hexagon.png")));
        btnToolbarHexagon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setToolAction(ToolAction.HEXAGON);
            }
        });
        actionsButtonGroup.add(btnToolbarHexagon);

        JPanel panel = new JPanel();

        btnToolbarModify = new JButton("Edit");
        btnToolbarModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.modifySelected();
            }
        });
        btnToolbarModify.setForeground(new Color(83, 194, 52));
        btnToolbarModify.setBackground(new Color(224, 255, 255));
        btnToolbarModify.setEnabled(false);
        btnToolbarDelete = new JButton("Delete");
        btnToolbarDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.deleteSelected();
            }
        });
        btnToolbarDelete.setForeground(new Color(167, 22, 0));
        btnToolbarDelete.setBackground(new Color(255, 182, 193));
        btnToolbarDelete.setEnabled(false);

        btnToolbarLoadNext = new JButton("Load next");
        btnToolbarLoadNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadNextCommand();
            }
        });
        btnToolbarLoadNext.setForeground(new Color(26, 18, 228));
        btnToolbarLoadNext.setBackground(new Color(224, 255, 255));
        btnToolbarLoadNext.setVisible(false);

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Center alignment

        btnToolbarBackground = new JButton("");
        btnToolbarBackground.setPreferredSize(new Dimension(32, 32)); // Set preferred size
        btnToolbarBackground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.colorPickerPickBackgroundColor();
            }
        });
        panel.add(btnToolbarBackground);

        btnToolbarColor = new JButton("");
        btnToolbarColor.setPreferredSize(new Dimension(32, 32)); // Set preferred size
        btnToolbarColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.colorPickerPickOutlineColor();
            }
        });
        panel.add(btnToolbarColor);
        setLayout(new FormLayout(new ColumnSpec[]{FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("90px"),},
                new RowSpec[]{FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("32px"),
                        FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("32px"),
                        FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("32px"),
                        FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("32px"),
                        FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("32px"),
                        FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("32px"),
                        FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("32px"),
                        FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("42px"),
                        FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("32px"),
                        FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("32px"),
                        FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("32px"),}));
        add(btnToolbarPoint, "2, 4, fill, fill");
        add(btnToolbarSelect, "2, 2, fill, fill");
        add(btnToolbarLine, "2, 6, fill, fill");
        add(btnToolbarRectangle, "2, 8, fill, fill");
        add(btnToolbarCircle, "2, 10, fill, fill");
        add(btnToolbarDonut, "2, 12, fill, fill");
        add(btnToolbarHexagon, "2, 14, fill, fill");
        add(panel, "2, 16, fill, top");
        add(btnToolbarModify, "2, 18, fill, fill");
        add(btnToolbarDelete, "2, 20, fill, fill");
        add(btnToolbarLoadNext, "2, 22, fill, fill");

    }

    public void setController(DrawingController controller) {
        this.controller = controller;
    }

    public void setModel(WorkspaceModel model) {
        btnToolbarColor.setOpaque(true);
        btnToolbarColor.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, model.getShapeColor()));

        btnToolbarBackground.setOpaque(true);
        btnToolbarBackground.setBackground(model.getShapeBackground());
        btnToolbarBackground.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
    }

    public void setToolToSelect() {
        btnToolbarSelect.setSelected(true);
    }

    public void setEnabledModify(boolean isEnabled) {
        btnToolbarModify.setEnabled(isEnabled);
    }

    public void setEnabledDelete(boolean isEnabled) {
        btnToolbarDelete.setEnabled(isEnabled);
    }

    public void setPreviewShapeColor(Color color) {
        btnToolbarColor.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, color));
    }

    public void setPreviewShapeBackgroundColor(Color color) {
        btnToolbarBackground.setBackground(color);
    }

    public void setEnabledPreviewShapeBackgroundColor(boolean isEnabled) {
        btnToolbarBackground.setEnabled(isEnabled);
    }

    public void setEnabledCommands(Boolean enabled) {
        Enumeration<AbstractButton> buttons = actionsButtonGroup.getElements();
        while (buttons.hasMoreElements()) {
            buttons.nextElement().setEnabled(enabled);
        }
        btnToolbarModify.setVisible(enabled);
        btnToolbarDelete.setVisible(enabled);

        btnToolbarBackground.setVisible(enabled);

        btnToolbarColor.setVisible(enabled);

        btnToolbarLoadNext.setVisible(!enabled);

    }
}
