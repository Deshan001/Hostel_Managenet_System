package lk.ijse.hostelmanagementsystem.service.custom;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.service.CrudService;
import lk.ijse.hostelmanagementsystem.tm.ReserveOrAvailableRoomsTM;

import java.util.HashMap;
import java.util.List;

public interface StudentRoomService extends CrudService<StudentRoomDTO , String> {
  List<ReserveOrAvailableRoomsTM>getReserveRoomDetails();
  boolean updateKeyMoney(StudentRoomDTO studentRoomDTO);

    HashMap<String, Double> getMonthlyIncome();
}
