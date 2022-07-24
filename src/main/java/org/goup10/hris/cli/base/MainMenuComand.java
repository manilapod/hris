package org.goup10.hris.cli.base;

import org.goup10.hris.cli.RootComponent;

import java.util.Map;

public class MainMenuComand extends BaseCommand {
    public MainMenuComand() {
        super("Go to Main Menu");
    }

    @Override
    public void execute(Map<String, Object> context) {
        HrisExecutor executor = HrisExecutor.getInstance();
        executor.next(new RootComponent());
    }
}
