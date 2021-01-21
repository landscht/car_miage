package main.java.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandType {

    public static final String PASS_CMD = "PASS";

    public static final Map<String, Command> COMMAND = new HashMap<>();
    static {
        COMMAND.put(PASS_CMD, new PassCmd());
    }

}
