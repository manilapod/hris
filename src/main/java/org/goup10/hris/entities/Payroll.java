package org.goup10.hris.entities;

import lombok.*;
import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@TableGenerator(name="SequenceTable", initialValue=10, allocationSize=50)
public class Payroll {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="SequenceTable")
    Integer payrollId;

    Integer employeeId;
    Long hourlyRate;
    Long grossSalary;
    Long salary;
    Long yearlyBonus;
    Long stateTax;
    Long federalTax;
    String employmentType;
    Integer weeklyHours;
    String payCycle;
    Integer extraHours;
    String effective;
}
