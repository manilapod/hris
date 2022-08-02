package org.goup10.hris.cli.insurance;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.goup10.hris.entities.Insurance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Scanner;

public class GetInsuranceByIdCommand extends BaseCommand {
    public GetInsuranceByIdCommand() {
        super("Get Insurance by ID");
    }

    @Override
    public void execute(Map<String, Object> context) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter insurance ID: ");
        int id = scanner.nextInt();

        String url = (String)context.get(HrisCliApplication.ENDPOINT);
        String endpoint = url + "/insurances/" + id;

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Insurance> response = restTemplate.getForEntity(endpoint, Insurance.class);
            Insurance insurance = response.getBody();

            System.out.println("Found one employee as follows:");
            InsuranceUtils.printInsurance(insurance);
        } catch (Exception e) {
            System.out.println("The following error occurred while finding an employee by id, please try again! [" + e.getMessage() + "]");
        }
    }
}
