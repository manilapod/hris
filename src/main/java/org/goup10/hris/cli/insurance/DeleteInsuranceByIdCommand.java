package org.goup10.hris.cli.insurance;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.goup10.hris.entities.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Scanner;

public class DeleteInsuranceByIdCommand  extends BaseCommand {
    public DeleteInsuranceByIdCommand() {
        super("Delete Employee by ID");
    }

    @Override
    public void execute(Map<String, Object> context) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter insurance ID: ");
        int id = scanner.nextInt();

        String url = (String) context.get(HrisCliApplication.ENDPOINT);
        String endpoint = url + "/insurances/" + id;

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete(endpoint);

            System.out.println("insurance with id [" + id + "] deleted successfully!");
        } catch (Exception e) {
            System.out.println("The following error occurred while deleting the insurance, please try again! [" + e.getMessage() + "]");
        }
    }
}
