package org.tugasbesar.demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormLoginController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    String username = "database123";
    String password = "inipassword";
    
    @FXML
    private Button btnLogin;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String cek1 = tfUsername.getText();
        String cek2 = tfUsername.getText();
        if(Empty()){
            if(cek1.equals(tfUsername.getText())){
                if(cek2.equals(tfPassword.getText())){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Inputan Tidak Valid !!!");
                    alert.showAndWait();
                } else {
                    Parent root = FXMLLoader.load(getClass().getResource("FormMenu.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("FormMenu.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
    }
    }
    
    private boolean Empty(){
        if(tfUsername.getText().isEmpty() | tfPassword.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Inputan Tidak Boleh Kosong !!!");
            alert.showAndWait();
            
            return false;
        }
        return true;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
