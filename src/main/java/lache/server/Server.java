package lache.server;

import lache.common.Log;
import lache.common.Settings;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Server {
    public static List<ThreadsSockets> threadsSockets = new ArrayList<>();
    private static final String settingsServerFileName = "settingsServer.txt";

    public void start() throws IOException {

        Settings.checkSettingsFile(settingsServerFileName, "Server");
        Map<String, String> settings = Settings.getSettings(settingsServerFileName);
        int port = Integer.valueOf(settings.get("port"));

        System.out.println("SERVER ждет клиентов ");
        Log log = Log.getInstance();
        log.log("Server", "", "SERVER ждет клиентов ");

        ServerSocket serverSocket = new ServerSocket(port);
        try {
            while (true) {
                Socket socket = serverSocket.accept();  // ждем подключения
                try {
                    threadsSockets.add(new ThreadsSockets(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            log.log("Server", "", "SERVER завершил работу ");
            serverSocket.close();
        }
    }
}
