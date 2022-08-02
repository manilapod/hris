package org.goup10.hris.cli;

import org.goup10.hris.cli.base.HrisComponent;
import org.goup10.hris.cli.base.TerminateCommand;
import org.goup10.hris.cli.benefit.BenefitComponent;
import org.goup10.hris.cli.employee.EmployeeComponent;
import org.goup10.hris.cli.insurance.InsuranceComponent;
import org.goup10.hris.cli.payroll.PayRollComponent;

public class RootComponent extends HrisComponent {
    public RootComponent() {
        super("Human Resource Information System (HRIS)");

        this.add(new EmployeeComponent());
        this.add(new BenefitComponent());
        this.add(new InsuranceComponent());
        this.add(new PayRollComponent());
        this.add(new TerminateCommand());
    }
}
