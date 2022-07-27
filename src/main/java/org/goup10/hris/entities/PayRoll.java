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
public class PayRoll {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SequenceTable")
    Integer payrollId;

    Integer employee_id;
    Long hourly_rate;
    Long gross_salary;
    Long salary;
    Long yearly_bonus;
    Long state_tax;
    Long federal_tax;
    Long employment_gender;
    Integer weekly_hours;
    String pay_cycle;
    Integer extra_hour;
    String  effective;
}
