package org.goup10.hris.cli.payroll;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Scanner;

public class DeletePayRollByIdCommand extends BaseCommand {
    public DeletePayRollByIdCommand() {
        super("Delete Payroll by ID");
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
            restTemplate.delete(endpoint);

            System.out.println("Payroll with id [" + id + "] deleted successfully!");
        } catch (Exception e) {
            System.out.println("The following error occurred while deleting the payroll, please try again! [" + e.getMessage() + "]");
        }
    }
}
