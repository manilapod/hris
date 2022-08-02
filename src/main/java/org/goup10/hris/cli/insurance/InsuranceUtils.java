package org.goup10.hris.cli.insurance;

import org.goup10.hris.entities.Insurance;

public class InsuranceUtils {
    public static void printInsurances(Insurance[] insurances) {
        System.out.println("The database returned " + insurances.length + " insurances as follows:");
        for (Insurance insurance: insurances) {
            printInsurance(insurance);
        }
    }

    public static void printInsurance(Insurance insurance) {
        System.out.println("-----------------------");
        System.out.println("Benefit ID: " + insurance.getBenefitId());
        System.out.println("Life premium: " + insurance.getLifePrem());
        System.out.println("Insurance premium: " + insurance.getHealthPrem());
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
