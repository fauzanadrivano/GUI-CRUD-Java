package org.tugasbesar.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Fauzan
 * @author Hafiz
 * @author Nadia
 * @version 16.0.1
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Database Hiburan");
        Parent root = FXMLLoader.load(getClass().getResource("FormLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
