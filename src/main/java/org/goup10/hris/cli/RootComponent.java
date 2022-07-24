package org.goup10.hris.cli;

import org.goup10.hris.cli.base.HrisComponent;
import org.goup10.hris.cli.base.TerminateCommand;
import org.goup10.hris.cli.employee.EmployeeComponent;

public class RootComponent extends HrisComponent {
    public RootComponent() {
        super("Human Resource Information System (HRIS)");

        this.add(new EmployeeComponent());

        // Add other components such as:
        // - BenefitComponent
        // - PayrollComponent
        this.add(new TerminateCommand());
    }
}
