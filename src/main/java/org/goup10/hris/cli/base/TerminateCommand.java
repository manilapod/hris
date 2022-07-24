package org.goup10.hris.cli.base;

import java.util.Map;

public class TerminateCommand extends BaseCommand {
    public TerminateCommand() {
        super("Exit the Application");
    }

    @Override
    public void execute(Map<String, Object> context) {
        System.out.println("Thank you for using HRIS! Have a nice day!");
        HrisExecutor executor = HrisExecutor.getInstance();
        executor.next(new NoActionCommand());
    }
}
