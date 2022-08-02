package org.goup10.hris.cli.payroll;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.goup10.hris.entities.Payroll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class ListAllPayRollsCommand extends BaseCommand {
    public ListAllPayRollsCommand() {
        super("List All Employees");
    }

    @Override
    public void execute(Map<String, Object> context) {
        String url = (String)context.get(HrisCliApplication.ENDPOINT);
        String endpoint = url + "/employees";

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Payroll[]> response = restTemplate.getForEntity(endpoint, Payroll[].class);
            Payroll[] payrolls = response.getBody();
            PayRollUtils.printPayRolls(payrolls);
        } catch (Exception e) {
            System.out.println("The following error occurred while listing all payrolls, please try again! [" + e.getMessage() + "]");
        }
    }


}
