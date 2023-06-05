package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.hostelmanagementsystem.dto.custom.UserDTO;
import lk.ijse.hostelmanagementsystem.service.custom.UserService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.UserServiceImpl;

import java.io.IOException;

public class LoginFormController {
    public JFXButton btnLogin;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane LoginFormContext;
    public Label lblWrong;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnLogin) {
            String username = txtUserName.getText();
            String password = txtPassword.getText();
            boolean b = checkLogin(new UserDTO(txtUserName.getText(), txtPassword.getText()));
            if (b) {
                System.out.println("login success");
                Stage window = (Stage) LoginFormContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
                window.centerOnScreen();
            } else if
            (txtUserName.getText().isEmpty() && txtPassword.getText().isEmpty()) {
                lblWrong.setText("Please enter your data.");
            } else {
                lblWrong.setText("Wrong username or password!");
            }
        }
    }

    UserService service=new UserServiceImpl();

    public boolean checkLogin(UserDTO user){
        UserDTO search = service.search(user.getUserName());
        if(search==null){
            lblWrong.setText("Invalid User Name");
            lblWrong.setVisible(true);
            return false;
        }
        if(!user.getPassword().equals(search.getPassword())){
            lblWrong.setText("Invalid Password");
            lblWrong.setVisible(true);
            return false;
        }
        return true;
        //if(user.getPassword().equals())
    }

    public void forgotPasswordOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnLogin.getScene().getWindow());
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ForgetPasswordForm.fxml"))));
        stage.show();
    }
}
