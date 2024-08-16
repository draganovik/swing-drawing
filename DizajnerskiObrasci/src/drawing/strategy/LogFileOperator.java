package drawing.strategy;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.DefaultListModel;

import drawing.mvc.models.WorkspaceModel;

public class LogFileOperator implements IFileOperator {

	WorkspaceModel model;

	public LogFileOperator(WorkspaceModel model) {
		super();
		this.model = model;
	}

	@Override
	public void saveFile(String filePath) throws IOException {
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(model.getCommandLogString());
		fileWriter.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void loadFile(String filePath) throws IOException, ClassNotFoundException {

		try (FileInputStream fileInputStream = new FileInputStream(filePath);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
			DefaultListModel<String> newListModel = (DefaultListModel<String>) objectInputStream.readObject();
			model.initFromCommandLogListModel(newListModel);
		} catch (Exception ex) {
			throw ex;
		}
	}

}
