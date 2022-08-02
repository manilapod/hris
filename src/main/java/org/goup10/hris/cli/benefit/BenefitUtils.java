package org.goup10.hris.cli.benefit;

import org.goup10.hris.entities.Benefit;
import org.goup10.hris.entities.Employee;

public class BenefitUtils {
    public static void printBenefits(Benefit[] benefits) {
        System.out.println("The database returned " + benefits.length + " benefits as follows:");
        for (Benefit benefit: benefits) {
            printBenefit(benefit);
        }
    }

    public static void printBenefit(Benefit benefit) {
        System.out.println("-----------------------");
        System.out.println("Employee ID: " + benefit.getEmployeeId());
        System.out.println("Benefit ID: " + benefit.getBenefitId());
        System.out.println("Life insurance: " + benefit.getLifeInsurance());
        System.out.println("Last Name: " + benefit.getRetirementPlans());
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
