package lk.ijse.hostelmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.service.custom.JoinService;
import lk.ijse.hostelmanagementsystem.service.custom.StudentRoomService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.JoinServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentRoomServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.RemainingKeyMoneyTM;

import java.util.List;

public class RemainingKeyFormController {
    public TableView<RemainingKeyMoneyTM> tblKeyMoney;
    public TableColumn<RemainingKeyMoneyTM,String> colRoomId;
    public TableColumn<RemainingKeyMoneyTM,String> colRType;
    public TableColumn<RemainingKeyMoneyTM,String> colStudent;
    public TableColumn<RemainingKeyMoneyTM,Double> colFullKeyMoney;
    public TableColumn<RemainingKeyMoneyTM,Double> colPaidAmount;
    public TextField txtStudentId;
    public TextField txtSName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEndDate;
    public TextField txtStartDate;
    public TextField txtKeyMoney;
    public TextField txtPaying;

    private JoinService joinService = new JoinServiceImpl();
    private final StudentRoomService studentRoomService = new StudentRoomServiceImpl();
    public void initialize(){
        visualize();
        getData();
    }

    public void getData(){
        List<RemainingKeyMoneyTM> details = joinService.getRemainingKeyMoneydetails();
        ObservableList<RemainingKeyMoneyTM> data = FXCollections.observableArrayList(details);
        tblKeyMoney.setItems(data);
    }

    public void visualize(){
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room"));
        colRType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colFullKeyMoney.setCellValueFactory(new PropertyValueFactory<>("fullKeyMoney"));
        colPaidAmount.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
    }

    public void btnDoneOnAction(ActionEvent actionEvent) {
        RemainingKeyMoneyTM item = tblKeyMoney.getSelectionModel().getSelectedItem();
        double amount = item.getPaidAmount() + Double.parseDouble(txtPaying.getText());
        item.setPaidAmount(amount);
        item.getStudentRoom().setAdvance(amount);
        boolean b = studentRoomService.updateKeyMoney(item.getStudentRoom());
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Operation Failed").show();
        }
    }

    public void tblRemainingKeyMoneyClickOnAction(MouseEvent mouseEvent) {
        RemainingKeyMoneyTM selectedItem = tblKeyMoney.getSelectionModel().getSelectedItem();
        if (selectedItem!=null)setData(selectedItem);
    }


    public void setData(RemainingKeyMoneyTM data){
        StudentRoomDTO studentRoom = data.getStudentRoom();
        StudentDTO student = studentRoom.getStudent();
        txtStudentId.setText(student.getId());
        txtSName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContact.setText(student.getContact());
        txtStartDate.setText(studentRoom.getFrom().toString());
        txtEndDate.setText(studentRoom.getTo().toString());
        txtKeyMoney.setText(String.valueOf(data.getFullKeyMoney()-data.getPaidAmount()));

    }
}
