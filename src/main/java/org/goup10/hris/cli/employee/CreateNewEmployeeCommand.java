package org.goup10.hris.cli.employee;

import org.goup10.hris.cli.HrisCliApplication;
import org.goup10.hris.cli.base.BaseCommand;
import org.goup10.hris.entities.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;

import java.sql.Date;

public class CreateNewEmployeeCommand extends BaseCommand {
    public CreateNewEmployeeCommand() {
        super("Create New Employee");
    }

    @Override
    public void execute(Map<String, Object> context) {
        Scanner scanner = new Scanner(System.in);
        String url = (String)context.get(HrisCliApplication.ENDPOINT);
        String endpoint = url + "/employees";

        try {
            System.out.println("Please enter the employee info below:");
            System.out.print("Benefit ID: ");
            int benefitId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

            System.out.print("Birthday (YYYY/MM/DD): ");
            String birthDateStr = scanner.next();
            Date birthDate = new Date(dateFormat.parse(birthDateStr).getTime());
            scanner.nextLine();

            System.out.print("Job Role: ");
            String jobRole = scanner.nextLine();

            System.out.print("Address: ");
            String address = scanner.nextLine();

            System.out.print("Telephone Number: ");
            String telephoneNumber = scanner.nextLine();

            System.out.print("State: ");
            String state = scanner.nextLine();

            System.out.print("In Training (true/false): ");
            boolean inTraining = Boolean.parseBoolean(scanner.nextLine());

            System.out.print("Performance: ");
            int performance = Integer.parseInt(scanner.next());
            scanner.nextLine();

            System.out.print("Start Date (YYYY/MM/DD): ");
            String startDateStr = scanner.next();
            Timestamp startDate =  new Timestamp(dateFormat.parse(startDateStr).getTime());
            scanner.nextLine();

            System.out.print("Update Note: ");
            String lastUpdate = scanner.nextLine();

            Employee employee = Employee.builder()
                    .benefitId(benefitId)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .birthDate(birthDate)
                    .jobRole(jobRole)
                    .address(address)
                    .telephoneNumber(telephoneNumber)
                    .state(state)
                    .inTraining(inTraining)
                    .performance(performance)
                    .startedDate(startDate)
                    .lastUpdate(lastUpdate)
                    .build();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Employee> response = restTemplate.postForEntity(endpoint, employee, Employee.class);

            System.out.println("Created employee with id [" + response.getBody().getEmployeeId() + "] successfully!");
        } catch (Exception e) {
            System.out.println("The following error occurred while creating new employee, please try again! [" + e.getMessage() + "]");
        }
    }
}
