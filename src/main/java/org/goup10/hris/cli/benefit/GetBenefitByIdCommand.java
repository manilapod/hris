package org.goup10.hris.cli.benefit;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.goup10.hris.entities.Benefit;
import org.goup10.hris.entities.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Scanner;

public class GetBenefitByIdCommand extends BaseCommand {
    public GetBenefitByIdCommand() {
        super("Get Benefit by ID");
    }

    @Override
    public void execute(Map<String, Object> context) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter benefit ID: ");
        int id = scanner.nextInt();

        String url = (String)context.get(HrisCliApplication.ENDPOINT);
        String endpoint = url + "/benefits/" + id;

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Benefit> response = restTemplate.getForEntity(endpoint, Benefit.class);
            Benefit benefit = response.getBody();

            System.out.println("Found one employee as follows:");
            BenefitUtils.printBenefit(benefit);
        } catch (Exception e) {
            System.out.println("The following error occurred while finding an employee by id, please try again! [" + e.getMessage() + "]");
        }
    }
}
