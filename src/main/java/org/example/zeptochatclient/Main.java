package org.example.zeptochatclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.zeptochatclient.util.PropertiesUtil;

import static org.example.zeptochatclient.util.PropertiesConstants.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        primaryStage.setOnCloseRequest(event -> controller.exitAction());
        primaryStage.setTitle(PropertiesUtil.get(CLIENT_NAME_PROPERTY));
        primaryStage.setScene(new Scene(
                root,
                PropertiesUtil.getInt(CLIENT_WIDTH_PROPERTY),
                PropertiesUtil.getInt(CLIENT_HEIGHT_PROPERTY)
        ));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
