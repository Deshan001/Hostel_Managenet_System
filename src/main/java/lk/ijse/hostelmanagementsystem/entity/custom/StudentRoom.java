package lk.ijse.hostelmanagementsystem.entity.custom;

import lk.ijse.hostelmanagementsystem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentRoom implements SuperEntity {
    @Id
    private String id;
    private Double advance;
    private Date paymentDate;
    private Date fromDate;
    private Date toDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id")
    private Student student;

    @JoinColumn(name = "room_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Room room;


}
