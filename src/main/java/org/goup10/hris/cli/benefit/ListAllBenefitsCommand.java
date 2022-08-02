package org.goup10.hris.cli.benefit;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.goup10.hris.cli.benefit.BenefitUtils;
import org.goup10.hris.entities.Benefit;
import org.goup10.hris.entities.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class ListAllBenefitsCommand extends BaseCommand {
    public ListAllBenefitsCommand() {
        super("List All Benefits");
    }

    @Override
    public void execute(Map<String, Object> context) {
        String url = (String)context.get(HrisCliApplication.ENDPOINT);
        String endpoint = url + "/employees";

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Benefit[]> response = restTemplate.getForEntity(endpoint, Benefit[].class);
            Benefit[] benefits = response.getBody();
            BenefitUtils.printBenefits(benefits);
        } catch (Exception e) {
            System.out.println("The following error occurred while listing all benefits, please try again! [" + e.getMessage() + "]");
        }
    }


}
