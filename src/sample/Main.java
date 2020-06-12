package sample;

import DataBase.SqliteConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;
    public static Stage getStage() {
        return stage;
    }
    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(new Scene(root, 350, 300));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        SqliteConnection.createUserAccountsDataBase();
        launch(args);
    }
}
