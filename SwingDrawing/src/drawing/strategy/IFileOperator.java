package drawing.strategy;

import java.io.IOException;

public interface IFileOperator {
    public void saveFile(String filePath) throws IOException;

    public void loadFile(String filePath) throws IOException, ClassNotFoundException;
}
