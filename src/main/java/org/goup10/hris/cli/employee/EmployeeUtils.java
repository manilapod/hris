package org.goup10.hris.cli.employee;

import org.goup10.hris.entities.Employee;

public class EmployeeUtils {
    public static void printEmployees(Employee[] employees) {
        System.out.println("The database returned " + employees.length + " employees as follows:");
        for (Employee employee: employees) {
            printEmployee(employee);
        }
    }

    public static void printEmployee(Employee employee) {
        System.out.println("-----------------------");
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Benefit ID: " + employee.getBenefitId());
        System.out.println("First Name: " + employee.getFirstName());
        System.out.println("Last Name: " + employee.getLastName());
//        String email;
//        Date birthDate;
//        String jobRole;
//        String address;
//        String telephoneNumber;
//        String state;
//        Boolean inTraining;
//        Integer performance;
//        Date startedDate;
//        String lastUpdate;

    }
}
