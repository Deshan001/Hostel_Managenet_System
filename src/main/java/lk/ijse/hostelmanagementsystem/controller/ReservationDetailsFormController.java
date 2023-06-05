package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.service.custom.RoomService;
import lk.ijse.hostelmanagementsystem.service.custom.StudentRoomService;
import lk.ijse.hostelmanagementsystem.service.custom.TransactionService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentRoomServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.TransactionServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.ReserveOrAvailableRoomsTM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ReservationDetailsFormController {
    public TableView<ReserveOrAvailableRoomsTM> tblReserveRoom;
    public TableColumn<ReserveOrAvailableRoomsTM,String> colReId;
    public TableColumn<ReserveOrAvailableRoomsTM,String> colDescription;
    public TableColumn<ReserveOrAvailableRoomsTM,String> colType;
    public TextField txtSId;
    public TextField txtStudentName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtStartDate;
    public TextField txtEndDate;
    public TextField txtKeyMoney;
    public JFXButton btnLeave;
    public TableView tblAvailableRooms;
    public TableColumn colRoomId2;
    public TableColumn colDescription2;
    public TableColumn colType2;

    private StudentRoomService service =new StudentRoomServiceImpl();
    private TransactionService transactionService =new TransactionServiceImpl();
    private RoomService roomService =new RoomServiceImpl();

    public void initialize(){
        visualize();
        getData();
        getAvailableRooms();
    }

    public void visualize(){
        colReId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

        colRoomId2.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colDescription2.setCellValueFactory(new PropertyValueFactory<>("description"));
        colType2.setCellValueFactory(new PropertyValueFactory<>("type"));
    }


    public void getData(){
        List<ReserveOrAvailableRoomsTM> details = service.getReserveRoomDetails();
        ObservableList<ReserveOrAvailableRoomsTM> all = FXCollections.observableArrayList(details);
        tblReserveRoom.setItems(all);
    }

    public void setData(ReserveOrAvailableRoomsTM obj){
        StudentDTO student = obj.getStudentRoom().getStudent();
        txtSId.setText(student.getId());
        txtAddress.setText(student.getAddress());
        txtStudentName.setText(student.getName());
        txtContact.setText(student.getContact());
        txtStartDate.setText(obj.getStudentRoom().getFrom().toString());
        txtEndDate.setText(obj.getStudentRoom().getTo().toString());
        txtKeyMoney.setText(String.valueOf(obj.getStudentRoom().getAdvance()));
    }

    public void tblReserveRoomOnCLickAction(MouseEvent mouseEvent) {
        ReserveOrAvailableRoomsTM detail = tblReserveRoom.getSelectionModel().getSelectedItem();
        if (detail!=null)setData(detail);
    }

    public void btnLeaveOnAction(ActionEvent actionEvent) {
        ReserveOrAvailableRoomsTM selectedItem = tblReserveRoom.getSelectionModel().getSelectedItem();
        Optional<ButtonType> al = new Alert(Alert.AlertType.WARNING,"Are You Sure",ButtonType.YES,ButtonType.NO).showAndWait();
        if ((al.isPresent())&&(selectedItem!=null)){
            if (al.get().equals(ButtonType.NO));
        }
        StudentRoomDTO studentRoom = selectedItem.getStudentRoom();
        studentRoom.setTo(LocalDate.now());
        RoomDTO room = new RoomDTO();
        room.setId(selectedItem.getRoomId());
        room.setAvailability("Available");
        studentRoom.setRoom(room);
        boolean b = transactionService.makeLeave(studentRoom);
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"Operation Success");
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Operation Failed");
        }
    }


    public void getAvailableRooms(){
        List<ReserveOrAvailableRoomsTM> availableRooms = roomService.getAvailableRoom();
        ObservableList<ReserveOrAvailableRoomsTM> all = FXCollections.observableArrayList(availableRooms);
        tblAvailableRooms.setItems(all);
    }
}
