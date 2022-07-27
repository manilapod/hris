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
public class Insurance {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SequenceTable")
    Integer insuranceId;

    Integer benefitId;
    long life_prem;
    long health_prem;
}
