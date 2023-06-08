package lache.common;

public enum Commands {
    FINISH_WORK("/exit"),
    CONNECT_CLOSE("/close");

    private String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
