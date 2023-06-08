package lache.server;

import lache.MainServer;
import lache.common.Commands;
import lache.common.Log;

import java.io.*;
import java.net.Socket;

import static lache.server.Server.threadsSockets;

public class ThreadsSockets extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ThreadsSockets(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String msg;
        try {
            Log log = Log.getInstance();
            String loginClient = in.readLine();

            log.log("Server", "", loginClient + "  присоединился к чату.");
            this.send("Вы присоединились к чату");
            sendForAll(loginClient + " присоединился к чату.");

            while (true) {
                msg = in.readLine();
                if (msg.equals(Commands.FINISH_WORK.getCommand())) {
                    synchronized (msg) {
                        if (msg.equals(Commands.FINISH_WORK.getCommand())) {
                            this.send(Commands.CONNECT_CLOSE.getCommand());
                            break;
                        }
                    }
                }
                sendForAll(loginClient + ": " + msg);
                log.log("Server", loginClient, msg);
            }
            sendForAll(loginClient + " вышел из чата ");
            log.log("Server", "", loginClient + " вышел из чата ");
        } catch (IOException e) {
        }
    }

    private void sendForAll(String msg) {
        for (ThreadsSockets thread : threadsSockets) {
            if (thread != this) {
                thread.send(msg);
            }
        }
    }

    private void send(String msg) {
        try {
            out.write(msg+ "\n");
            out.flush();
        } catch (IOException ignored) {
        }
    }
}

