package org.goup10.hris.cli.employee;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.goup10.hris.entities.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class ListAllEmployeesCommand extends BaseCommand {
    public ListAllEmployeesCommand() {
        super("List All Employees");
    }

    @Override
    public void execute(Map<String, Object> context) {
        String url = (String)context.get(HrisCliApplication.ENDPOINT);
        String endpoint = url + "/employees";

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Employee[]> response = restTemplate.getForEntity(endpoint, Employee[].class);
            Employee[] employees = response.getBody();
            EmployeeUtils.printEmployees(employees);
        } catch (Exception e) {
            System.out.println("The following error occurred while listing all employees, please try again! [" + e.getMessage() + "]");
        }
    }


}
