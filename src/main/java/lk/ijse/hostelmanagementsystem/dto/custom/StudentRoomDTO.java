package lk.ijse.hostelmanagementsystem.dto.custom;

import lk.ijse.hostelmanagementsystem.dto.SuperDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRoomDTO implements SuperDTO{
    private String id;
    private Double advance;
    private LocalDate paymentDate;
    private LocalDate from;
    private LocalDate to;
    private StudentDTO student;
    private RoomDTO room;
}
