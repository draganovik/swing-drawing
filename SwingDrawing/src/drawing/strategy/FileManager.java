package drawing.strategy;

import java.io.IOException;

public class FileManager implements IFileOperator {

    private IFileOperator fileOperator;

    public FileManager(IFileOperator fileOperator) {
        super();
        this.fileOperator = fileOperator;
    }

    @Override
    public void saveFile(String filePath) throws IOException {
        fileOperator.saveFile(filePath);
    }

    @Override
    public void loadFile(String filePath) throws ClassNotFoundException, IOException {
        fileOperator.loadFile(filePath);
    }

}
