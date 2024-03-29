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
public class Tax {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="SequenceTable")
    Integer taxId;

    Integer payrollId;
    long statePrem;
    long federalPrem;
}