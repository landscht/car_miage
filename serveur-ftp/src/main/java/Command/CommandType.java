package main.java.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandType {

    public static final String PASS_CMD = "PASS";
    public static final String USER_CMD = "USER";
    public static final String EPRT_CMD = "EPRT";
    public static final String STOR_CMD = "STOR";
    public static final String QUIT_CMD = "QUIT";

    public static final Map<String, Command> COMMAND = new HashMap<>();
    static {
        COMMAND.put(PASS_CMD, new PassCmd());
        COMMAND.put(USER_CMD, new UserCmd());
        COMMAND.put(EPRT_CMD, new EprtCmd());
        COMMAND.put(STOR_CMD, new StorCmd());
        COMMAND.put(QUIT_CMD, new QuitCmd());
    }

}
