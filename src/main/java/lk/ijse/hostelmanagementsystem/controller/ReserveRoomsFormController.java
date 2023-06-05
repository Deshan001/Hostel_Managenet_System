package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lk.ijse.hostelmanagementsystem.service.custom.RoomService;
import lk.ijse.hostelmanagementsystem.service.custom.RoomTypeService;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.service.custom.TransactionService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomTypeServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.TransactionServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class ReserveRoomsFormController {
    public JFXTextField txtReservationNo;
    public JFXTextField txtPayingAmount;
    public JFXTextField txtRoomsAvailability;
    public JFXTextField txtKeyMoney;
    public JFXComboBox<RoomTypeDTO> cbRoomTypeId;
    public JFXComboBox<StudentDTO> cbStudentId;
    public TableView table;
    public TableColumn colStudentId;
    public TableColumn colRoomTypeId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colPayingAmount;
    public TableColumn colDateFrom;
    public TableColumn colDateTo;
    public DatePicker datePicker1;
    public DatePicker datePicker2;
    public JFXComboBox<RoomDTO> cmbRoom;

    private final StudentService studentService =new StudentServiceImpl();
    private final RoomTypeService roomTypeService = new RoomTypeServiceImpl();
    private final RoomService roomService = new RoomServiceImpl();
    public JFXTextField txtStudentName;
    public JFXButton btnAdd;
    public JFXButton btnClear;
    private TransactionService transactionService =new TransactionServiceImpl();


    public void initialize(){
        setConverter();
        setStudentData();
        setRoomTypeData();

    }

    public void registerOnAction(ActionEvent actionEvent) {
    }


    public void setConverter(){
        cbStudentId.setConverter(new StringConverter<StudentDTO>() {
            @Override
            public String toString(StudentDTO student) {
                if (student!=null)return student.getId();
                return null;
            }

            @Override
            public StudentDTO fromString(String string) {
                return null;
            }
        });
        cbRoomTypeId.setConverter(new StringConverter<RoomTypeDTO>() {
            @Override
            public String toString(RoomTypeDTO roomType) {
                if (roomType!=null)return roomType.getId();
                return null;
            }

            @Override
            public RoomTypeDTO fromString(String string) {
                return null;
            }
        });
        cmbRoom.setConverter(new StringConverter<RoomDTO>() {
            @Override
            public String toString(RoomDTO room) {
                if (room!=null)return room.getId();
                return null;
            }

            @Override
            public RoomDTO fromString(String string) {
                return null;
            }
        });
    }

    public void setStudentData(){
        List<StudentDTO> all = studentService.getAll();
        cbStudentId.setItems(FXCollections.observableArrayList(all));
    }

    public void setRoomTypeData(){
        List<RoomTypeDTO> all = roomTypeService.getAll();
        cbRoomTypeId.setItems(FXCollections.observableArrayList(all));
    }

    public void setRoomData(RoomTypeDTO type){
        List<RoomDTO> availableRooms = roomService.getAvailableRoom(type);
        cmbRoom.setItems(FXCollections.observableArrayList(availableRooms));
    }

    public void cbRoomTypeIdOnAction(ActionEvent actionEvent) {
        RoomTypeDTO type = cbRoomTypeId.getSelectionModel().getSelectedItem();
        if (type!=null){
            setRoomData(type);
            txtKeyMoney.setText(String.valueOf(type.getKey_money()));
        }

    }

    public void cmbRoomOnAction(ActionEvent actionEvent) {
    }

    public void cbStudentIdOnAction(ActionEvent actionEvent) {
        StudentDTO st = cbStudentId.getSelectionModel().getSelectedItem();
        if (st!=null){
            txtStudentName.setText(st.getName());
        }
    }



    public void btnAddOnAction(ActionEvent actionEvent) {
        String reservationId = txtReservationNo.getText();
        StudentDTO student = cbStudentId.getSelectionModel().getSelectedItem();
        RoomDTO room = cmbRoom.getSelectionModel().getSelectedItem();
        double payment = Double.parseDouble(txtPayingAmount.getText());

        room.setAvailability("Not Available");

        StudentRoomDTO studentRoom = new StudentRoomDTO(reservationId, payment,LocalDate.now(),datePicker2.getValue(),datePicker2.getValue(),student,room);

        boolean isAdded = transactionService.reserveRoom(studentRoom);
        if(isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Operation Failed").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtReservationNo.clear();
        txtStudentName.clear();
        txtPayingAmount.clear();
        txtKeyMoney.clear();
        cmbRoom.getItems().clear();
       cbStudentId.getItems().clear();
        cbRoomTypeId.getItems().clear();
        datePicker1.getEditor().clear();
        datePicker2.getEditor().clear();
    }
}
