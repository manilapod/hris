package org.goup10.hris.entities;

import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@TableGenerator(name="SequenceTable", initialValue=10, allocationSize=50)
public class PTE {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="SequenceTable")
    Integer pteId;

    Integer trainingId;
    String firstName;
    String lastName;
    String email;
    Integer zipCode;
    String position;
    String dataApplied;
    String appStatus;
    Timestamp lastUpdate;
}