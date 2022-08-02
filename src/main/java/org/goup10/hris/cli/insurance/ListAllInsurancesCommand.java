package org.goup10.hris.cli.insurance;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.goup10.hris.entities.Insurance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class ListAllInsurancesCommand extends BaseCommand {
    public ListAllInsurancesCommand() {
        super("List All Insurances");
    }

    @Override
    public void execute(Map<String, Object> context) {
        String url = (String)context.get(HrisCliApplication.ENDPOINT);
        String endpoint = url + "/insurances";

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Insurance[]> response = restTemplate.getForEntity(endpoint, Insurance[].class);
            Insurance[] insurances = response.getBody();
            InsuranceUtils.printInsurances(insurances);
        } catch (Exception e) {
            System.out.println("The following error occurred while listing all employees, please try again! [" + e.getMessage() + "]");
        }
    }


}
