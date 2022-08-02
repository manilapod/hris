package org.goup10.hris.cli.payroll;

import org.goup10.hris.entities.Payroll;

public class PayRollUtils {
    public static void printPayRolls(Payroll[] payrolls) {
        System.out.println("The database returned " + payrolls.length + " payRolls as follows:");
        for (Payroll payRoll: payrolls) {
            printPayRoll(payRoll);
        }
    }

    public static void printPayRoll(Payroll payRoll) {
        System.out.println("-----------------------");
        System.out.println("PayRoll ID: " + payRoll.getPayrollId());
        System.out.println("Employee ID: " + payRoll.getEmployeeId());
        System.out.println("Hourly rate: " + payRoll.getHourlyRate());
        System.out.println("Gross salary: " + payRoll.getGrossSalary());
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


