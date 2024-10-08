package drawing.strategy;

import drawing.mvc.models.WorkspaceModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFileOperator implements IFileOperator {

    WorkspaceModel workspaceModel;

    public LogFileOperator(WorkspaceModel workspaceModel) {
        super();
        this.workspaceModel = workspaceModel;
    }

    @Override
    public void saveFile(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(workspaceModel.getCommandLogString());
        fileWriter.close();
    }

    @Override
    public void loadFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> commandLog = new ArrayList<>();
            String line;

            // Read each line and add it to the list
            while ((line = reader.readLine()) != null) {
                commandLog.add(line);
            }

            // Pass the list of strings to your model
            workspaceModel.initFromCommandLogList(commandLog);
        } catch (Exception ex) {
            throw ex;
        }
    }

}
