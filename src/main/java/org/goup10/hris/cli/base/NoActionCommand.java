package org.goup10.hris.cli.base;

import java.util.Map;

public class NoActionCommand extends BaseCommand {
    public NoActionCommand() {
        super("No Action");
    }

    @Override
    public void execute(Map<String, Object> context) {
    }
}
