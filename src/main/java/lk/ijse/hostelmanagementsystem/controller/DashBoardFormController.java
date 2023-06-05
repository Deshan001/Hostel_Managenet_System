package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardFormController {
    public JFXButton btnHome;
    public JFXButton btnManageStudent;
    public JFXButton btnReserveDetails;
    public JFXButton btnManageRooms;
    public JFXButton btnRemainingKey;

    public JFXButton btnLogOut;
    public AnchorPane DashBoardContext2;
    public AnchorPane DashBoardContext1;
    public JFXButton btnReserveRooms;

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext2.getChildren().clear();
        DashBoardContext2.getChildren().add(FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml")));
    }

    public void btnManageStudentOnAction(ActionEvent actionEvent) throws IOException {
       DashBoardContext2.getChildren().clear();
       DashBoardContext2.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ManageStudentForm.fxml")));
    }

    public void btnReserveDetailsOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext2.getChildren().clear();
        DashBoardContext2.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ReservationDetailsForm.fxml")));
    }

    public void btnManageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext2.getChildren().clear();
        DashBoardContext2.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ManageRoomsForm.fxml")));
    }

    public void btnReserveRoomsOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext2.getChildren().clear();
        DashBoardContext2.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ReserveRoomsForm.fxml")));
    }

    public void btnRemainingKeyOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext2.getChildren().clear();
        DashBoardContext2.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RemainingKeyForm.fxml")));
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext1.getChildren().clear();
        DashBoardContext1.getChildren().add(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml")));
    }


}
