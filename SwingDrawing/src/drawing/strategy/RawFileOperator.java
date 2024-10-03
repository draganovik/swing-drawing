package drawing.strategy;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

import javax.swing.*;
import java.io.*;

public class RawFileOperator implements IFileOperator {

    CanvasModel model;

    public RawFileOperator(CanvasModel model) {
        super();
        this.model = model;
    }

    @Override
    public void saveFile(String filePath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(model.getDefaultListModel());
        objectOutputStream.close();
        fileOutputStream.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void loadFile(String filePath) throws IOException, ClassNotFoundException {

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            DefaultListModel<Shape> newListModel = (DefaultListModel<Shape>) objectInputStream.readObject();
            model.setDefaultListModel(newListModel);
        } catch (Exception ex) {
            throw ex;
        }
    }

}
