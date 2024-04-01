package command.Utility;

import java.io.*;
import java.util.ArrayDeque;

public class ScriptUtil implements Reader{

    /**
     * Utility class for reading script files and pushing them to the stack to work around recursions
     */
    private static final ArrayDeque<String> filepaths = new ArrayDeque<>();
    private static final ArrayDeque<BufferedReader> reader = new ArrayDeque<>();

    public static String readfile() throws IOException {
        return reader.getFirst().readLine();
    }

    public static void pushFile(String file) throws FileNotFoundException {
        reader.push(new BufferedReader(new FileReader(file)));
        filepaths.push(file);
    }

    public static void popfile() throws IOException {
        reader.getFirst().close();
        reader.pop();
        filepaths.pop();
    }
    public static boolean fileReapeting(String filepath){
        return filepaths.contains(new File(filepath).getAbsolutePath());
    }

    @Override
    public String nextline() {
        try {
            return readfile();
        } catch (IOException e) {
            return "";
        }
    }
}
