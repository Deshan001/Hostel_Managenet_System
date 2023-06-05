package lk.ijse.hostelmanagementsystem.service.custom;

import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Room;
import lk.ijse.hostelmanagementsystem.entity.custom.RoomType;
import lk.ijse.hostelmanagementsystem.service.CrudService;
import lk.ijse.hostelmanagementsystem.tm.ReserveOrAvailableRoomsTM;
import lk.ijse.hostelmanagementsystem.tm.RoomTM;
import org.hibernate.Session;

import java.util.List;

public interface RoomService extends CrudService<RoomDTO ,String> {
    List<RoomDTO> getAvailableRoom(RoomTypeDTO roomType);
    List<RoomTM> getRoomDataFToTable();
    List<ReserveOrAvailableRoomsTM>getAvailableRoom();

}
