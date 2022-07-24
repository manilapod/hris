package org.goup10.hris.cli.employee;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.goup10.hris.entities.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Scanner;

public class GetEmployeeByIdCommand extends BaseCommand {
    public GetEmployeeByIdCommand() {
        super("Get Employee by ID");
    }

    @Override
    public void execute(Map<String, Object> context) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();

        String url = (String)context.get(HrisCliApplication.ENDPOINT);
        String endpoint = url + "/employees/" + id;

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Employee> response = restTemplate.getForEntity(endpoint, Employee.class);
            Employee employee = response.getBody();

            System.out.println("Found one employee as follows:");
            EmployeeUtils.printEmployee(employee);
        } catch (Exception e) {
            System.out.println("The following error occurred while finding an employee by id, please try again! [" + e.getMessage() + "]");
        }
    }
}
