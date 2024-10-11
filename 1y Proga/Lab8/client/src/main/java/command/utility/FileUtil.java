package command.utility;

public class FileUtil {

    /**
     * Utility class for checking if the program is switched to "file mode"
     */
    public static boolean isFileMode = false;

    public static void setFileMode(boolean FileMode) {
        FileUtil.isFileMode = FileMode;
    }
}
