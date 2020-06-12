package sample;

import DataBase.SqliteConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private JFXTextField suser;

    @FXML
    private JFXPasswordField spassword;

    @FXML
    private JFXTextField luser;

    @FXML
    private JFXPasswordField lpassword;

    @FXML
    private Label lInfo;

    @FXML
    private Label sInfo;

    private Stage stage = Main.getStage();

    @FXML
    void getLogin(MouseEvent event) {
        if(luser.getText().isEmpty() || lpassword.getText().isEmpty()){
            lInfo.setText("*Invalid Input");
        } else if (SqliteConnection.isSignUp(luser.getText(),lpassword.getText())){
            System.out.println("Successfully Login");
            stage.close();
        } else {
            lInfo.setText("Account doesn't exists");
        }
    }

    @FXML
    void loadSignUp(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignUp.fxml"));
            stage.setScene(new Scene(root, 350, 300));
        }catch (IOException e){
            System.out.println("Sign-In page loaded");
        }
    }

    @FXML
    void back(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
            stage.setScene(new Scene(root, 350, 300));
        }catch (IOException e){
            System.out.println("Login page loaded");
        }
    }

    @FXML
    void close(MouseEvent event) {
        stage.close();
    }

    @FXML
    void signedUp(MouseEvent event) {
        if(suser.getText().isEmpty() || spassword.getText().isEmpty()){
            sInfo.setText("*Invalid User");
        } else if(SqliteConnection.isSignUp(suser.getText(),spassword.getText())){
            sInfo.setText(suser.getText()+"is already a user");
        }else{
            SqliteConnection.insertAccount(suser.getText(),spassword.getText());
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }
}
