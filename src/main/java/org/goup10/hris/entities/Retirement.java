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
public class Retirement {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SequenceTable")
    Integer retirementId;

    Integer benefitId;
    long IRA_AMOUNT; //401k
    long PTB_AMOUNT; // 403B
    long TSA_AMOUNT; // 457B
}
