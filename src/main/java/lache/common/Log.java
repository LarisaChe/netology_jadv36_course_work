package lache.common;

import java.io.IOException;
import java.time.LocalDateTime;

public class Log {

    private static String logFileNameTemplate = "%s_chat.log";
    private static Log INSTANCE = null;

    private Log() {}

    public static Log getInstance() {
        if (INSTANCE == null) {
            synchronized (Log.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Log();
                }
            }
        }
        return INSTANCE;
    }

    public void log(String client, String fromClient, String msg) throws IOException {
        String logFileName = String.format(logFileNameTemplate, client);
        String logMsg = "[" + LocalDateTime.now() + "] " + fromClient + ": " + msg;
        boolean append = true;
        WorkWithFiles.saveToFile(logFileName, logMsg, append);
    }

}
