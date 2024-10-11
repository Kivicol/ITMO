package command.utility;

import java.io.*;
import java.util.ArrayDeque;

public class ScriptUtil implements Reader{

    /**
     * Utility class for reading script files and pushing them to the stack to work around recursions
     */

    private static final ArrayDeque<String> filePaths = new ArrayDeque<>();
    private static final ArrayDeque<BufferedReader> reader = new ArrayDeque<>();


    public String readFile() throws IOException {
        return reader.getFirst().readLine();
    }

    public void pushFile(String file) throws FileNotFoundException {
        reader.push(new BufferedReader(new FileReader(file)));
        filePaths.push(file);
    }

    public void popFile() throws IOException {
        reader.getFirst().close();
        reader.pop();
        filePaths.pop();
    }
    public boolean fileReapeting(String filepath){
        return filePaths.contains(new File(filepath).getAbsolutePath());
    }

    @Override
    public String nextLine() {
        try {
            return readFile();
        } catch (IOException e) {
            return "";
        }
    }
}
