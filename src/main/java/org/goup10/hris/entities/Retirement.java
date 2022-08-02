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
    long irsAMOUNT; //401k
    long ptbAMOUNT; // 403B
    long tsaAMOUNT; // 457B
}