package drawing.mvc.views;

import javax.swing.*;
import java.awt.*;

public class CommandLogPanelView extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -5676175567633015715L;
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> list = new JList<>();

    /**
     * Create the panel.
     */
    public CommandLogPanelView() {
        super();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPanel = new JScrollPane(list);
        setLayout(new BorderLayout(0, 0));
        add(scrollPanel, BorderLayout.CENTER);
    }

    public void setDLM(DefaultListModel<String> dlm) {
        this.listModel = dlm;
        this.list.setModel(listModel);
    }

}
