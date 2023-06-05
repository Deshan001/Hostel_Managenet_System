package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentServiceImpl;

public class AddStudentFormController {


    public JFXTextField txtStudentId;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtName;
    public JFXTextField txtCity;
    public JFXButton btnSave;
    public JFXTextField txtGmail;
    public JFXButton btnClear;
    public JFXButton btnSaveStudents;


    private StudentService studentService;

    public void initialize(){
        studentService = new StudentServiceImpl();
    }

    public StudentDTO collectData(){
        String id = txtStudentId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String contact = txtContactNo.getText();
        String gmail = txtGmail.getText();


        StudentDTO student = new StudentDTO(id,name,address,city,contact,gmail,null);
        return student;
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        txtStudentId.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtContactNo.clear();
        txtGmail.clear();
    }

    public void btnSaveStudentsOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = collectData();
        StudentDTO save = studentService.save(studentDTO);
        if (save!=null){
            new Alert(Alert.AlertType.INFORMATION,"Student Added Success").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Student Added Failed").show();
        }
    }
}