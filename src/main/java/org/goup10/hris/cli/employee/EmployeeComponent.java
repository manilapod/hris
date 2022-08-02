package org.goup10.hris.cli.employee;

import org.goup10.hris.cli.base.HrisComponent;
import org.goup10.hris.cli.base.MainMenuCommand;
import org.goup10.hris.cli.base.TerminateCommand;

public class EmployeeComponent extends HrisComponent {
    public EmployeeComponent() {
        super("Employee Management");

        this.add(new CreateNewEmployeeCommand());
        this.add(new ListAllEmployeesCommand());
        this.add(new GetEmployeeByIdCommand());
        this.add(new DeleteEmployeeByIdCommand());
        this.add(new MainMenuCommand());
        this.add(new TerminateCommand());
    }
}
