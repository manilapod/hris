package org.goup10.hris.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@TableGenerator(name="SequenceTable", initialValue=10, allocationSize=50)
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="SequenceTable")
    Integer employeeId;
    Integer benefitId;
    String firstName;
    String lastName;
    String email;
    Date birthDate;
    String jobRole;
    String address;
    String telephoneNumber;
    String state;
    Boolean inTraining;
    Integer performance;
    Date startedDate;
    String lastUpdate;
}
