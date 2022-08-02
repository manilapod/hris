package org.goup10.hris.cli.benefit;

import org.goup10.hris.cli.base.HrisComponent;
import org.goup10.hris.cli.base.MainMenuCommand;
import org.goup10.hris.cli.base.TerminateCommand;

public class BenefitComponent extends HrisComponent {
    public BenefitComponent() {
        super("Benefit Management");

        this.add(new ListAllBenefitsCommand());
        this.add(new GetBenefitByIdCommand());
        this.add(new DeleteBenefitByIdCommand());
        this.add(new MainMenuCommand());
        this.add(new TerminateCommand());
    }
}
