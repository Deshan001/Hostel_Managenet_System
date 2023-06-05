package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.service.custom.RoomService;
import lk.ijse.hostelmanagementsystem.service.custom.RoomTypeService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomTypeServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.RoomTM;
import lk.ijse.hostelmanagementsystem.tm.RoomTypeTM;

import java.util.List;
import java.util.Optional;

public class ManageRoomsFormController {

    public JFXTextField txtRoomId;
    public ComboBox<RoomTypeDTO> cbType;
    public JFXButton btnAddRoom;
    public JFXButton btnDeleteRoom;
    public JFXButton btnUpdateRoom;
    public JFXTextField txtDescription;
    public JFXTextField txtRoomTypeId;
    public JFXTextField txtKeyMoney;
    public JFXButton btnAddRoomType;
    public JFXButton btnDeleteRoomType;
    public JFXButton btnUpdateRoomType;
    public JFXTextField txtAvailability;
    public TableView<RoomTM> tblRoom;
    public TableColumn<RoomTM,String> colRoomId;
    public TableColumn<RoomTM,String>  colType;
    public TableColumn<RoomTM,Double>  colKeyMoney;
    public JFXButton btnSearch;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;

    private RoomTypeService roomTypeService;
    private RoomService roomService;

    public void initialize(){
        roomTypeService = new RoomTypeServiceImpl();
        roomService =new RoomServiceImpl();
        setComboBox();
        visualizeComboBox();
        visualize();
        getDataToTable();
    }

    public void setComboBox(){
        List<RoomTypeDTO> all = roomTypeService.getAll();
        cbType.setItems(FXCollections.observableArrayList(all));
    }

    public void visualizeComboBox(){
        cbType.setConverter(new StringConverter<RoomTypeDTO>() {
            @Override
            public String toString(RoomTypeDTO object) {
                if (object==null)return null;
                return object.getDescription();

            }

            @Override
            public RoomTypeDTO fromString(String string) {
                return null;
            }
        });
    }

    public void btnAddRoomOnAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = collectRoomData();
        RoomDTO save =roomService.save(roomDTO);
        if (save!=null){
            new Alert(Alert.AlertType.INFORMATION,"Room Added Success").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Room Added Failed").show();
        }
    }

    public void btnDeleteRoomOnAction(ActionEvent actionEvent) {
        try {
            RoomDTO roomDTO = roomService.search(txtRoomId.getText());
            if (roomDTO==null){
                new Alert(Alert.AlertType.INFORMATION,"Room Not Found").show();
                return;
            }
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.isPresent()){
                if (buttonType.get().equals(ButtonType.YES)){
                    boolean b = roomService.delete(roomDTO);
                    if (b){
                        new Alert(Alert.AlertType.INFORMATION,"Room Deleted ").show();
                    }else {
                        new Alert(Alert.AlertType.INFORMATION,"Room Deleted Fail").show();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateRoomOnAction(ActionEvent actionEvent) {
        boolean b = roomService.update(collectRoomData());
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"Room Updated").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Room updated Fail").show();
        }
    }

    public void btnAddRoomTypeOnAction(ActionEvent actionEvent) {
        RoomTypeDTO roomTypeDTO =collectRoomTypeDTO();
        RoomTypeDTO save =roomTypeService.save(roomTypeDTO);
        if (save!=null){
            new Alert(Alert.AlertType.INFORMATION,"Data Added").show();
            setComboBox();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Data Adding Failed").show();
        }
    }

    public void btnDeleteRoomTypeOnAction(ActionEvent actionEvent) {
        try {
            RoomTypeDTO roomTypeDTO = roomTypeService.search(txtRoomTypeId.getText());
            if (roomTypeDTO==null){
                new Alert(Alert.AlertType.INFORMATION,"Room Type Not Found").show();
                return;
            }
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.isPresent()){
                if (buttonType.get().equals(ButtonType.YES)){
                    boolean b = roomTypeService.delete(roomTypeDTO);
                    if (b){
                        new Alert(Alert.AlertType.INFORMATION,"Room Type Deleted ").show();
                    }else {
                        new Alert(Alert.AlertType.INFORMATION,"Room Type Deleted Fail").show();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateRoomTypeOnAction(ActionEvent actionEvent) {
        boolean b = roomTypeService.update(collectRoomTypeDto());
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"Room Type Updated").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Room Type updated Fail").show();
        }
    }

    public RoomTypeDTO collectRoomTypeDTO(){
        String roomTypeId = txtRoomTypeId.getText();
        String description = txtDescription.getText();
        double keyMoney = Double.parseDouble(txtKeyMoney.getText());

        return new RoomTypeDTO(roomTypeId,keyMoney,description,null);
    }
   /* public RoomDTO collectRoomDTO(){
        String id = txtRoomId.getText();
        *//*String availability = txtAvailability.getText();*//*
        RoomTypeDTO roomType= cbType.getSelectionModel().getSelectedItem();

        return new RoomDTO(id,roomType,null);
    }*/

    public RoomDTO collectRoomData(){
        String id = txtRoomId.getText();
        String availability = "Available";
        RoomTypeDTO roomType = cbType.getSelectionModel().getSelectedItem();

        return new RoomDTO(id,availability,roomType,null);
    }

    public RoomTypeDTO collectRoomTypeDto(){
        String roomTypeId = txtRoomTypeId.getText();
        String description = txtDescription.getText();
        double keyMoney =Double.parseDouble(txtKeyMoney.getText());

        return new RoomTypeDTO(roomTypeId,keyMoney,description,null);

    }
    public void visualize(){
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
    }

    public void getDataToTable(){
        List<RoomTM> data = roomService.getRoomDataFToTable();
        ObservableList<RoomTM> tblData = FXCollections.observableArrayList(data);
        tblRoom.setItems(tblData);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtRoomTypeId.getText();
        try {
            RoomTypeTM roomTypeTM = roomTypeService.searchRoomType(id);
            String description = roomTypeTM.getDescription();
            txtDescription.setText(description);
            double key_money = roomTypeTM.getKey_money();
            txtKeyMoney.setText(String.valueOf(key_money));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnClearRoomTypeOnAction(ActionEvent actionEvent) {
        txtRoomTypeId.clear();
        txtDescription.clear();
        txtKeyMoney.clear();
    }

    public void btnClearRoomOnAction(ActionEvent actionEvent) {
        txtRoomId.clear();
    }

}
