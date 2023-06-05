package lk.ijse.hostelmanagementsystem.entity.custom;

import lk.ijse.hostelmanagementsystem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoomType implements SuperEntity {
    @Id
    private String id;
    private Double key_money;
    private String description;
    @OneToMany(targetEntity = Room.class,mappedBy = "roomType")
    @ToString.Exclude
    private List<Room> rooms;

}
