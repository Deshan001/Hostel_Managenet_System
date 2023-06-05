package lk.ijse.hostelmanagementsystem.dto.custom;

import lk.ijse.hostelmanagementsystem.dto.SuperDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeDTO implements SuperDTO {
    private String id;
    private Double key_money;
    private String description;
    private List<RoomDTO> rooms;

}