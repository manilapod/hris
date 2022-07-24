package org.goup10.hris.cli.base;

import java.util.HashMap;
import java.util.Map;

// Implemented this class as singleton using the example in https://www.baeldung.com/java-singleton
public class HrisExecutor {
    private static HrisExecutor instance;

    public static HrisExecutor getInstance() {
        if (instance == null) {
            instance = new HrisExecutor();
        }
        return instance;
    }

    private Map<String, Object> context;
    private HrisCommand nextCommand;

    private HrisExecutor() {
        this.nextCommand = null;
    }

    public void execute(Map<String, Object> context) {
        this.context = context;

        while (this.nextCommand != null) {
            HrisCommand command = this.nextCommand;
            this.nextCommand = null;
            command.execute(this.context);
        }
    }

    public void next(HrisCommand command) {
        this.nextCommand = command;
    }

    public void fallThrough(HrisCommand command) {
        if (this.nextCommand == null) {
            this.next(command);
        }
    }

    public HrisCommand getNextCommand() {
        return this.nextCommand;
    }
}
