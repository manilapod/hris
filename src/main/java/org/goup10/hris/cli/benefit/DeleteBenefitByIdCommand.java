package org.goup10.hris.cli.benefit;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.goup10.hris.entities.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Scanner;

public class DeleteBenefitByIdCommand  extends BaseCommand {
    public DeleteBenefitByIdCommand() {
        super("Delete Employee by ID");
    }

    @Override
    public void execute(Map<String, Object> context) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();

        String url = (String) context.get(HrisCliApplication.ENDPOINT);
        String endpoint = url + "/employees/" + id;

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete(endpoint);

            System.out.println("Employee with id [" + id + "] deleted successfully!");
        } catch (Exception e) {
            System.out.println("The following error occurred while deleting the employee, please try again! [" + e.getMessage() + "]");
        }
    }
}
