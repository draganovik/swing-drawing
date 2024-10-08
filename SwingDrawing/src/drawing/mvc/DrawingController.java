package drawing.mvc;

import drawing.adapters.HexagonAdapter;
import drawing.command.*;
import drawing.geometry.Point;
import drawing.geometry.Rectangle;
import drawing.geometry.Shape;
import drawing.geometry.*;
import drawing.modals.*;
import drawing.mvc.models.CanvasModel;
import drawing.mvc.models.WorkspaceModel;
import drawing.mvc.views.CanvasShapesPanelView;
import drawing.mvc.views.CanvasView;
import drawing.mvc.views.MenubarView;
import drawing.mvc.views.ToolbarView;
import drawing.observer.CanvasModelPropertyObserver;
import drawing.strategy.FileManager;
import drawing.strategy.LogFileOperator;
import drawing.strategy.RawFileOperator;
import drawing.types.ToolAction;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class DrawingController {

    private CanvasModel model;
    private WorkspaceModel workspaceModel;

    private CanvasView view;
    private ToolbarView toolbarView;
    private MenubarView menubarView;
    private CanvasShapesPanelView shapesPanelView;

    CanvasModelPropertyObserver canvasModelPropertyObserver;

    ICommand tempCommand;

    public DrawingController(CanvasModel model, WorkspaceModel workspaceModel) {
        this.model = model;
        this.workspaceModel = workspaceModel;

        this.canvasModelPropertyObserver = new CanvasModelPropertyObserver(this);
        model.addPropertyObserver(this.canvasModelPropertyObserver);
        workspaceModel.addPropertyObserver(this.canvasModelPropertyObserver);
    }

    public void setViews(CanvasView view, ToolbarView toolbarView, MenubarView menubarView,
                         CanvasShapesPanelView shapesPanelView) {
        this.view = view;
        this.toolbarView = toolbarView;
        this.menubarView = menubarView;
        this.shapesPanelView = shapesPanelView;
    }

    private void clearWorkspace() {
        model.removeAllShapes();

        menubarView.setVisibleObjectOptions(false);

        toolbarView.setEnabledCommands(true);
        toolbarView.setToolToSelect();

        workspaceModel.clearWorkspace();
        workspaceModel.setToolAction(ToolAction.SELECT);

        view.repaint();
    }

    private void executeCommand(ICommand command) {
        try {
            workspaceModel.executeCommand(command, true);
            view.repaint();
            shapesPanelView.repaint();
        } catch (Exception ex) {
            this.showAlert(ex.getMessage());
        }

        this.menubarView.setEnabledUndo(workspaceModel.canUndo());
        this.menubarView.setEnabledRedo(workspaceModel.canRedo());
    }

    private void executeCommandNoLog(ICommand command) {
        try {
            workspaceModel.executeCommand(command, false);
            view.repaint();
        } catch (Exception ex) {
            this.showAlert(ex.getMessage());
        }
        tempCommand = command;

        this.menubarView.setEnabledUndo(workspaceModel.canUndo());
        this.menubarView.setEnabledRedo(workspaceModel.canRedo());
    }

    private void printTempCommandToLog() {
        workspaceModel.printCommandToLog(tempCommand);
        tempCommand = null;
    }

    private void showDialog(JDialog modal) {
        modal.pack();
        modal.setLocationRelativeTo(view);
        modal.setVisible(true);
    }

    private void showAlert(String message) {
        JOptionPane.showMessageDialog(view, message, "Operation Alert", JOptionPane.INFORMATION_MESSAGE);
    }

    private int showConfirm(String message) {
        return JOptionPane.showConfirmDialog(view, message, "Confirm Operation", JOptionPane.YES_NO_OPTION);
    }

    private JFileChooser getJFileChooser(String title) {
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jFileChooser.setDialogTitle(title);
        jFileChooser.setMultiSelectionEnabled(false);
        jFileChooser.enableInputMethods(false);
        Action details = jFileChooser.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        return jFileChooser;
    }

    /*
     * Canvas commands
     */

    public void mousePressed(MouseEvent e) {
        Point mousePoint = new Point(e.getX(), e.getY());
        this.workspaceModel.setStartPoint(mousePoint);
        this.workspaceModel.setDragPoint(mousePoint);
        this.workspaceModel.setEndPoint(mousePoint);

        model.setIsShiftDown(e.isShiftDown());

        if (!model.getAllSelectedShapes().isEmpty() && workspaceModel.getToolAction() != ToolAction.SELECT) {
            ICommand command = new DeselectAllShapes(model);
            executeCommand(command);
        }

        switch (workspaceModel.getToolAction()) {
            case POINT:
                workspaceModel.initCreatedShape(mousePoint);
                ICommand addShape = new AddShape(model, workspaceModel.getCreatedShape());
                executeCommandNoLog(addShape);
                break;
            case LINE:
                workspaceModel.initCreatedShape(new Line(mousePoint));
                break;
            case RECTANGLE:
                workspaceModel.initCreatedShape(new Rectangle());
                workspaceModel.getCreatedShape().setStartPoint(mousePoint);
                break;
            case CIRCLE:
                workspaceModel.initCreatedShape(new Circle());
                workspaceModel.getCreatedShape().setStartPoint(mousePoint);
                break;
            case DONUT:
                workspaceModel.initCreatedShape(new Donut());
                workspaceModel.getCreatedShape().setStartPoint(mousePoint);
                break;
            case HEXAGON:
                workspaceModel.initCreatedShape(new HexagonAdapter());
                workspaceModel.getCreatedShape().setStartPoint(mousePoint);
                break;
            default:
            case SELECT:
                Optional<Shape> optionalShape = model.getShapeAt(mousePoint);
                if (optionalShape.isEmpty()) {
                    if (!model.getAllSelectedShapes().isEmpty()) {
                        ICommand deselectAll = new DeselectAllShapes(model);
                        executeCommand(deselectAll);
                    }
                } else {

                    if (optionalShape.get().isSelected()) {
                        if (model.getIsShiftDown()) {
                            ICommand deselectAll = new DeselectShape(model, model.getShapeIndex(optionalShape.get()));
                            executeCommand(deselectAll);
                        }
                    } else {
                        if (!model.getIsShiftDown() && !model.getAllSelectedShapes().isEmpty()) {
                            ICommand deselectAll = new DeselectAllShapes(model);
                            executeCommand(deselectAll);
                        }

                        ICommand selectShape = new SelectShape(model, model.getShapeIndex(optionalShape.get()));
                        executeCommand(selectShape);
                    }
                }

                if (model.getAllSelectedShapes().size() != 0) {
                    Shape lastShape = model.getAllSelectedShapes().getLast();

                    this.setColorPickerShapeColor(lastShape.getColor());
                    if (lastShape instanceof SurfaceShape) {
                        this.setColorPickerShapeBackground(((SurfaceShape) lastShape).getBackgroundColor());
                    }

                    boolean hasSurfaceShape = false;
                    for (Shape shape : model.getAllSelectedShapes()) {
                        if (shape instanceof SurfaceShape) {
                            hasSurfaceShape = true;
                            break;
                        }
                    }
                    if (!hasSurfaceShape) {
                        this.setColorPickerShapeBackground(null);
                    }
                    view.repaint();
                }
                break;
        }
    }

    public void mouseDragged(MouseEvent e) {
        Point mousePoint = new Point(e.getX(), e.getY());

        switch (workspaceModel.getToolAction()) {
            case SELECT:
                if (model.getAllSelectedShapes().isEmpty()) {
                    break;
                }
                int distanceX = mousePoint.getX() - workspaceModel.getDragPoint().getX();
                int distanceY = mousePoint.getY() - workspaceModel.getDragPoint().getY();
                model.moveSelectedShapesBy(distanceX, distanceY);
                workspaceModel.setDragPoint(mousePoint);
                view.repaint();
                break;
            case POINT:
            case LINE:
            case CIRCLE:
            case DONUT:
            case HEXAGON:
                if (this.workspaceModel.canDragCreateToPoint(mousePoint)) {
                    if (!model.contains(workspaceModel.getCreatedShape())) {
                        ICommand addShape = new AddShape(model, workspaceModel.getCreatedShape());
                        executeCommandNoLog(addShape);
                        break;
                    }
                    workspaceModel.getCreatedShape().setEndPoint(mousePoint);
                    view.repaint();
                }
                break;
            case RECTANGLE:
                int limit = workspaceModel.getMinDragDistanceForCreatingShape();
                Boolean canUpdateX = this.workspaceModel.canDragCreateXToPoint(mousePoint);
                Boolean canUpdateY = this.workspaceModel.canDragCreateYToPoint(mousePoint);
                Point update = mousePoint.clone();
                if (!canUpdateX) {
                    update.setX(workspaceModel.getStartPoint().getX()
                            + (update.getX() > workspaceModel.getStartPoint().getX() ? limit : -limit));
                }
                if (!canUpdateY) {
                    update.setY(workspaceModel.getStartPoint().getY()
                            + (update.getY() > workspaceModel.getStartPoint().getY() ? limit : -limit));
                }
                if (!canUpdateX && !canUpdateY) {
                    break;
                }
                if (!model.contains(workspaceModel.getCreatedShape())) {
                    ICommand addShape = new AddShape(model, workspaceModel.getCreatedShape());
                    executeCommandNoLog(addShape);
                    break;
                }
                workspaceModel.getCreatedShape().setEndPoint(update);
                view.repaint();
                break;
            default:
                break;
        }
    }

    public void mouseReleased(MouseEvent e) {
        Point mousePoint = new Point(e.getX(), e.getY());
        this.workspaceModel.setEndPoint(mousePoint);

        switch (workspaceModel.getToolAction()) {
            case SELECT:
                try {
                    if (model.getAllSelectedShapes().size() == 0) {
                        break;
                    }
                    int distanceX = workspaceModel.getStartPoint().getX() - mousePoint.getX();
                    int distanceY = workspaceModel.getStartPoint().getY() - mousePoint.getY();
                    if (distanceX + distanceY == 0) {
                        break;
                    }
                    model.moveSelectedShapesBy(distanceX, distanceY);
                    ICommand moveSelected = new MoveSelected(model, this.workspaceModel.getStartPoint(), mousePoint);
                    executeCommand(moveSelected);
                } catch (Exception ex) {
                    this.showAlert(ex.getMessage());
                }
                break;
            case POINT:
                workspaceModel.getCreatedShape().setEndPoint(mousePoint);
                break;
            case LINE:
                if (!this.workspaceModel.canDragCreateToPoint(mousePoint)) {
                    DlgManageLine modal = new DlgManageLine((Line) workspaceModel.getCreatedShape());
                    showDialog(modal);
                    if (modal.IsSuccessful() && !model.contains(workspaceModel.getCreatedShape())) {
                        ICommand addLine = new AddShape(model, workspaceModel.getCreatedShape());
                        executeCommand(addLine);
                    }
                }
                break;
            case RECTANGLE:
                if (!this.workspaceModel.canDragCreateToPoint(mousePoint)) {
                    DlgManageRectangle modal = new DlgManageRectangle((Rectangle) workspaceModel.getCreatedShape());
                    showDialog(modal);
                    if (modal.IsSuccessful() && !model.contains(workspaceModel.getCreatedShape())) {
                        ICommand addRectangle = new AddShape(model, workspaceModel.getCreatedShape());
                        executeCommand(addRectangle);
                    }
                }
                break;
            case CIRCLE:
                if (!this.workspaceModel.canDragCreateToPoint(mousePoint)) {
                    DlgManageCircle modal = new DlgManageCircle((Circle) workspaceModel.getCreatedShape());
                    showDialog(modal);
                    if (modal.IsSuccessful() && !model.contains(workspaceModel.getCreatedShape())) {
                        ICommand addCircle = new AddShape(model, workspaceModel.getCreatedShape());
                        executeCommand(addCircle);
                    }
                }
                break;
            case DONUT:
                if (!this.workspaceModel.canDragCreateToPoint(mousePoint)) {
                    DlgManageDonut modal = new DlgManageDonut((Donut) workspaceModel.getCreatedShape());
                    showDialog(modal);
                    if (modal.IsSuccessful() && !model.contains(workspaceModel.getCreatedShape())) {
                        ICommand addDonut = new AddShape(model, workspaceModel.getCreatedShape());
                        executeCommand(addDonut);
                    }
                }
                break;
            case HEXAGON:
                if (!this.workspaceModel.canDragCreateToPoint(mousePoint)) {
                    DlgManageHexagon modal = new DlgManageHexagon((HexagonAdapter) workspaceModel.getCreatedShape());
                    showDialog(modal);
                    if (modal.IsSuccessful() && !model.contains(workspaceModel.getCreatedShape())) {
                        ICommand addHexagon = new AddShape(model, workspaceModel.getCreatedShape());
                        executeCommand(addHexagon);
                    }
                }
                break;
            default:
                workspaceModel.clearCreatedShape();
                break;
        }
        if (tempCommand != null) {
            printTempCommandToLog();
            view.repaint();
        }

    }

    /*
     * Menubar commands
     */

    public void undo() {
        try {
            workspaceModel.undoCommand();
            view.repaint();
        } catch (Exception ex) {
            this.showAlert(ex.getMessage());
        }

        this.menubarView.setEnabledUndo(workspaceModel.canUndo());
        this.menubarView.setEnabledRedo(workspaceModel.canRedo());
    }

    public void redo() {
        try {
            workspaceModel.redoCommand();
            view.repaint();
        } catch (Exception ex) {
            this.showAlert(ex.getMessage());
        }

        this.menubarView.setEnabledUndo(workspaceModel.canUndo());
        this.menubarView.setEnabledRedo(workspaceModel.canRedo());
    }

    public void loadNextCommand() {
        try {
            this.workspaceModel.processLoadedCommand(model);
            view.repaint();
            shapesPanelView.repaint();
        } catch (Exception ex) {
            this.showAlert(ex.getMessage());
        }

        if (this.workspaceModel.hasLoadedAllCommands()) {
            this.workspaceModel.setToolAction(ToolAction.SELECT);
            this.toolbarView.setEnabledCommands(true);
            this.menubarView.isInLoaderMode(false);
            this.menubarView.setEnabledUndo(workspaceModel.canUndo());
            this.menubarView.setEnabledRedo(workspaceModel.canRedo());
            this.menubarView.setVisibleObjectOptions(this.model.getAllSelectedShapeIndexes().size() > 0);
        }
    }

    public void moveSelectedForward() {
        ICommand command = new MoveSelectedForward(model);
        executeCommand(command);
    }

    public void moveSelectedBackward() {
        ICommand command = new MoveSelectedBackward(model);
        executeCommand(command);
    }

    public void moveSelectedToFront() {
        ICommand command = new MoveSelectedToFront(model);
        executeCommand(command);
    }

    public void moveSelectedToBack() {
        ICommand command = new MoveSelectedToBack(model);
        executeCommand(command);
    }

    public void duplicateSelected() {
        ICommand command = new DuplicateSelected(model);
        this.executeCommand(command);
    }

    public void startNewWorkspace() {
        int option = this.showConfirm("This will clear your current canvas space, are you sure?");
        if (option == JOptionPane.YES_OPTION) {
            this.clearWorkspace();
        }

    }

    public void saveAsRawFile() {
        JFileChooser jFileChooser = getJFileChooser("Save Raw File Dialog");
        jFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
        int response = jFileChooser.showSaveDialog(view);

        if (response != JFileChooser.APPROVE_OPTION) {
            if (response != JFileChooser.CANCEL_OPTION) {
                showAlert("There was an error while performing saving of raw file");
            }
            return;
        }

        try {
            String filepath = jFileChooser.getSelectedFile().getPath();
            FileManager file = new FileManager(new RawFileOperator(model));
            file.saveFile(filepath);
        } catch (Exception ex) {
            showAlert(ex.getMessage());
        }

    }

    public void loadARawFile() {
        JFileChooser jFileChooser = getJFileChooser("Load Raw File Dialog");
        int response = jFileChooser.showOpenDialog(view);

        if (response != JFileChooser.APPROVE_OPTION) {
            if (response != JFileChooser.CANCEL_OPTION) {
                showAlert("There was an error while performing loading of raw file");
            }
            return;
        }

        if (jFileChooser.getSelectedFile() == null) {
            showAlert("No raw file selected");
            return;
        }

        try {

            String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
            FileManager file = new FileManager(new RawFileOperator(model));

            clearWorkspace();
            file.loadFile(filePath);
            view.repaint();
            shapesPanelView.setDLM(model.getDefaultListModel());
        } catch (Exception ex) {
            showAlert(ex.getMessage());
        }
    }

    public void saveAsLogFile() {
        JFileChooser jFileChooser = getJFileChooser("Save Log Dialog");
        jFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
        int response = jFileChooser.showSaveDialog(view);

        if (response != JFileChooser.APPROVE_OPTION) {
            if (response != JFileChooser.CANCEL_OPTION) {
                showAlert("There was an error while performing loading of log file");
            }
            return;
        }

        try {
            String filepath = jFileChooser.getSelectedFile().getPath();
            FileManager file = new FileManager(new LogFileOperator(workspaceModel));
            file.saveFile(filepath);
        } catch (Exception ex) {
            showAlert(ex.getMessage());
        }
    }

    public void loadALogFile() {
        JFileChooser jFileChooser = getJFileChooser("Load Log Dialog");
        int response = jFileChooser.showOpenDialog(view);

        if (response != JFileChooser.APPROVE_OPTION) {
            if (response != JFileChooser.CANCEL_OPTION) {
                showAlert("There was an error while performing loading of log file");
            }
            return;
        }

        if (jFileChooser.getSelectedFile() == null) {
            showAlert("No raw file selected");
            return;
        }

        try {

            String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
            FileManager file = new FileManager(new LogFileOperator(workspaceModel));

            this.clearWorkspace();
            file.loadFile(filePath);
            view.repaint();
            this.menubarView.setEnabledUndo(workspaceModel.canUndo());
            this.menubarView.setEnabledRedo(workspaceModel.canRedo());
            this.menubarView.isInLoaderMode(true);
            this.toolbarView.setEnabledCommands(false);
        } catch (Exception ex) {
            showAlert(ex.getMessage());
        }
    }

    /*
     * Toolbar commands
     */

    public void setToolAction(ToolAction action) {
        if (action == this.workspaceModel.getToolAction()) {
            return;
        }

        if (action != ToolAction.SELECT && model.getAllSelectedShapeIndexes().size() != 0) {
            ICommand deselectAll = new DeselectAllShapes(model);
            executeCommand(deselectAll);
        }

        if (action == ToolAction.POINT || action == ToolAction.LINE) {
            toolbarView.setEnabledPreviewShapeBackgroundColor(false);
        } else {
            toolbarView.setEnabledPreviewShapeBackgroundColor(true);
        }

        workspaceModel.setToolAction(action);
    }

    private Color getColorFromColorPicker(String title, Color initialColor) {
        Color color = JColorChooser.showDialog(view, title, initialColor);
        return color;
    }

    public void colorPickerPickBackgroundColor() {
        Color selectedColor = getColorFromColorPicker("Choose Background Color", workspaceModel.getShapeColor());
        if (selectedColor != null) {
            workspaceModel.setShapeBackground(selectedColor);
            toolbarView.setPreviewShapeBackgroundColor(selectedColor);
            boolean hasSurfaceShape = false;
            for (Shape shape : model.getAllSelectedShapes()) {
                if (shape instanceof SurfaceShape) {
                    hasSurfaceShape = true;
                }
            }
            if (hasSurfaceShape) {
                ICommand command = new UpdateSelectedShapeBackground(model, selectedColor);
                this.executeCommand(command);
            }
        }
    }

    public void colorPickerPickOutlineColor() {
        Color selectedColor = getColorFromColorPicker("Choose Outline Color", workspaceModel.getShapeBackground());
        if (selectedColor != null) {
            workspaceModel.setShapeColor(selectedColor);
            toolbarView.setPreviewShapeColor(selectedColor);
            if (model.getAllSelectedShapeIndexes().isEmpty()) {
                return;
            }
            ICommand command = new UpdateSelectedShapeColor(model, selectedColor);
            this.executeCommand(command);

        }
    }

    public void setColorPickerShapeColor(Color color) {
        workspaceModel.setShapeColor(color);
        toolbarView.setPreviewShapeColor(color);
    }

    public void setColorPickerShapeBackground(Color color) {
        if (color == null) {
            toolbarView.setEnabledPreviewShapeBackgroundColor(false);
            return;
        }
        toolbarView.setEnabledPreviewShapeBackgroundColor(true);
        toolbarView.setPreviewShapeBackgroundColor(color);
        workspaceModel.setShapeBackground(color);
    }

    public void deleteSelected() {
        int option = this.showConfirm("Are you sure you want to delete selection?");
        if (option == JOptionPane.YES_OPTION) {
            ICommand command = new RemoveSelected(model);
            this.executeCommand(command);
        }
    }

    public void modifySelected() {
        Shape selectedShape = model.getAllSelectedShapes().getFirst();
        Shape update = selectedShape.clone();
        if (selectedShape instanceof Point) {
            DlgManagePoint modal = new DlgManagePoint((Point) update);
            showDialog(modal);
            if (modal.IsSuccessful()) {
                ICommand modifyPoint = new UpdateShape(model, update);
                executeCommand(modifyPoint);
            }
            return;
        }
        if (selectedShape instanceof Line) {
            DlgManageLine modal = new DlgManageLine((Line) update);
            showDialog(modal);
            if (modal.IsSuccessful()) {
                ICommand modifyLine = new UpdateShape(model, update);
                executeCommand(modifyLine);
            }
            return;
        }
        if (selectedShape instanceof Rectangle) {
            DlgManageRectangle modal = new DlgManageRectangle((Rectangle) update);
            showDialog(modal);
            if (modal.IsSuccessful()) {
                ICommand modifyRectangle = new UpdateShape(model, update);
                this.executeCommand(modifyRectangle);
            }
            return;
        }

        if (selectedShape instanceof Donut) {
            DlgManageDonut modal = new DlgManageDonut((Donut) update);
            showDialog(modal);
            if (modal.IsSuccessful()) {
                ICommand modifyDonut = new UpdateShape(model, update);
                this.executeCommand(modifyDonut);
            }
            return;
        }

        if (selectedShape instanceof Circle) {
            DlgManageCircle modal = new DlgManageCircle((Circle) update);
            showDialog(modal);
            if (modal.IsSuccessful()) {
                ICommand modifyCircle = new UpdateShape(model, update);
                this.executeCommand(modifyCircle);
            }
            return;
        }

        if (selectedShape instanceof HexagonAdapter) {
            DlgManageHexagon modal = new DlgManageHexagon((HexagonAdapter) update);
            showDialog(modal);
            if (modal.IsSuccessful()) {
                ICommand modifyHexagon = new UpdateShape(model, update);
                this.executeCommand(modifyHexagon);
            }
            return;
        }

    }

    public void setEnabledToolbarDelete(boolean isEnabled) {
        toolbarView.setEnabledDelete(isEnabled);
    }

    public void setEnabledToolbarModify(boolean isEnabled) {
        toolbarView.setEnabledModify(isEnabled);
    }

    public void setEnabledMenubarObjectOptions(boolean isEnabled) {
        menubarView.setVisibleObjectOptions(isEnabled);
    }

}
