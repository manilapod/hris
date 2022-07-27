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
public class Benefit {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SequenceTable")
    Integer benefitId;

    Integer employeeId;
    Integer retirementId;
    Integer insuranceId;
    String life_insurance;
    String retirement_plans;
    String tuition_assistance;
}
