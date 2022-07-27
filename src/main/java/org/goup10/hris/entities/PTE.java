package org.goup10.hris.entities;

import lombok.*;

import javax.persistence.*;
import java.security.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@TableGenerator(name="SequenceTable", initialValue=10, allocationSize=50)
public class PTE {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SequenceTable")
    Integer pteId;

    Integer trainingId;
    String first_name;
    String last_name;
    String email;
    Integer zip_code;
    String position;
    String data_applied;
    String app_status;
    Timestamp last_update;
}
