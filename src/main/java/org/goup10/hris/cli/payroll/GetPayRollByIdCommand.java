package org.goup10.hris.cli.payroll;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.goup10.hris.entities.Payroll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Scanner;

public class GetPayRollByIdCommand extends BaseCommand {
    public GetPayRollByIdCommand() {
        super("Get Employee by ID");
    }

    @Override
    public void execute(Map<String, Object> context) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter payroll ID: ");
        int id = scanner.nextInt();

        String url = (String) context.get(HrisCliApplication.ENDPOINT);
        String endpoint = url + "/payrolls/" + id;

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Payroll> response = restTemplate.getForEntity(endpoint, Payroll.class);
            Payroll payRoll = response.getBody();

            System.out.println("Found one payroll as follows:");
            PayRollUtils.printPayRoll(payRoll);
        } catch (Exception e) {
            System.out.println("The following error occurred while finding a payroll by id, please try again! [" + e.getMessage() + "]");
        }
    }
}
