package org.goup10.hris.cli.insurance;

import org.goup10.hris.cli.base.HrisComponent;
import org.goup10.hris.cli.base.MainMenuCommand;
import org.goup10.hris.cli.base.TerminateCommand;

public class InsuranceComponent extends HrisComponent {
    public InsuranceComponent() {
        super("Insurance Management");

        this.add(new ListAllInsurancesCommand());
        this.add(new GetInsuranceByIdCommand());
        this.add(new DeleteInsuranceByIdCommand());
        this.add(new MainMenuCommand());
        this.add(new TerminateCommand());
    }
}
