package org.tugasbesar.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Fauzan
 * @author Hafiz
 * @author Nadia
 * @version 16.0.1
 */
public class MainController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfJudul;
    @FXML
    private TextField tfSutradara;
    @FXML
    private TextField tfAktor;
    @FXML
    private TextField tfPH;
    @FXML
    private TextField tfTahun;
    @FXML
    private TableView<Film> tvData;
    @FXML
    private TableColumn<Film, Integer> kolomID;
    @FXML
    private TableColumn<Film, String> kolomJudul;
    @FXML
    private TableColumn<Film, String> kolomSutradara;
    @FXML
    private TableColumn<Film, String> kolomAktor;
    @FXML
    private TableColumn<Film, String> kolomPH;
    @FXML
    private TableColumn<Film, Integer> kolomTahun;
    @FXML
    private Button btnInput;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnBack;
    
    /**
     * method untuk memilih method mana yang akan dijalankan.
     * @param event variable untuk menyimpan aksi dari user.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if(event.getSource() == btnInput){
            insertRecord();
        }else if(event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteRecord();
        }else if(event.getSource() == btnExit){
            exit();
        }else if(event.getSource() == btnBack){
            Parent root = FXMLLoader.load(getClass().getResource("FormMenu.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showFilm();
    }    
    
    /**
     * method untuk memanggil JDBC driver dari database.
     * @return conn value.
     */
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datafilm", "root", "");
            return conn;
        }catch(SQLException e){
            System.out.println("Error : "+e.getMessage());
            return null;
        }
    }
    
    /**
     * method untuk menyimpan data dari database ke Observablelist.
     * @return filmList value.
     */
    public ObservableList<Film> getFilmList(){
        ObservableList<Film> filmList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM film";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Film film;
            while(rs.next()){
                film = new Film(rs.getInt("id"), rs.getString("judul"), rs.getString("sutradara"), rs.getString("aktor"), rs.getString("label"), rs.getInt("tahun"));
                filmList.add(film);
            }
        }catch(SQLException e){
            System.out.println("Error : "+e.getMessage());
        }
        return filmList;
    }
    
    /**
     * method untuk menampilkan value/nilai ke kolom tabel.
     */
    public void showFilm(){
        ObservableList<Film> list = getFilmList();
        
        kolomID.setCellValueFactory(new PropertyValueFactory<>("id"));
        kolomJudul.setCellValueFactory(new PropertyValueFactory<Film, String>("judul"));
        kolomSutradara.setCellValueFactory(new PropertyValueFactory<Film, String>("sutradara"));
        kolomAktor.setCellValueFactory(new PropertyValueFactory<Film, String>("aktor"));
        kolomPH.setCellValueFactory(new PropertyValueFactory<Film, String>("label"));
        kolomTahun.setCellValueFactory(new PropertyValueFactory<Film, Integer>("tahun"));
        
        tvData.setItems(list);
    }
    
    /**
     * method untuk memasukkan data inputan ke database.
     */
    private void insertRecord(){
        String cek = tfTahun.getText();
        if(validasi()){
            if(isNumeric(cek)){
                String query = "INSERT INTO film VALUES (" + tfID.getText() + ",'" + tfJudul.getText() + "','" + tfSutradara.getText() + "','" + tfAktor.getText() + "','" + tfPH.getText() + "'," + tfTahun.getText() +")";
                executeQuery(query);
                showFilm();
            }
        }
    }
    
    /**
     * method untuk memperbarui data yang ada di database.
     */
    private void updateRecord(){
        String cek = tfTahun.getText();
        if(validasi()){
            if(isNumeric(cek)){
                String query = "UPDATE film SET judul = '" + tfJudul.getText() + "', sutradara = '" + tfSutradara.getText() + "', aktor = '" + tfAktor.getText() + "', label = '" + tfPH.getText() + "', tahun = " + tfTahun.getText() + " WHERE id = " + tfID.getText() + "";
                executeQuery(query);
                showFilm();
            }
        }
    }
    
    /**
     * method untuk menghapus data dari database.
     */
    private void deleteRecord(){
        String query = "DELETE FROM film WHERE id =" + tfID.getText() + "";
        executeQuery(query);
        showFilm();
    }
    
    /**
     * method untuk mengeksekusi perintah ke database.
     * @param query untuk menyimpan perintah.
     */
    private void executeQuery(String query){
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    private void exit(){
        System.exit(0);
    }
    
    /**
     * method untuk memvalidasi inputan tidak boleh kosong.
     * @return validasi value.
     */
    private boolean validasi(){
        if(tfID.getText().isEmpty() | tfJudul.getText().isEmpty() | tfSutradara.getText().isEmpty() | tfAktor.getText().isEmpty() | tfPH.getText().isEmpty() | tfTahun.getText().isEmpty()){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Inputan Data Tidak Boleh Kosong !!!");
            alert.showAndWait();
            
            return false;
        }
        return true;
    }
    
    /**
     * method untuk memastikan inputan tahun berupa integer.
     * @param tahun untuk menyimpan value tahun.
     * @return isNumeric value.
     * @exception
     */
    private boolean isNumeric(String tahun) {
    try {
        Integer.parseInt(tahun);
    } catch (Exception e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Inputan Data Tidak Valid !!!");
        alert.showAndWait();
            
        return false;
    }
        return true;
    }
    
}
