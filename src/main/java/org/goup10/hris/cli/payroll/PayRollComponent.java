package org.goup10.hris.cli.payroll;

import org.goup10.hris.cli.base.HrisComponent;
import org.goup10.hris.cli.base.MainMenuCommand;
import org.goup10.hris.cli.base.TerminateCommand;

public class PayRollComponent extends HrisComponent {
    public PayRollComponent() {
        super("PayRoll Management");

        this.add(new ListAllPayRollsCommand());
        this.add(new GetPayRollByIdCommand());
        this.add(new DeletePayRollByIdCommand());
        this.add(new MainMenuCommand());
        this.add(new TerminateCommand());
    }
}
