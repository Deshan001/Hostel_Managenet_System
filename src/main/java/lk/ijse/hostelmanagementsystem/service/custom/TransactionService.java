package lk.ijse.hostelmanagementsystem.service.custom;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;

public interface TransactionService {
    boolean reserveRoom(StudentRoomDTO studentroomDTO);
    boolean makeLeave(StudentRoomDTO studentRoomDTO);
}
