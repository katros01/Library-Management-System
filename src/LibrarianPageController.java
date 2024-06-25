/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Amalitech
 */
public class LibrarianPageController implements Initializable {
    @FXML
    private BorderPane frame1;
    private AnchorPane frame;
    
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, Long> idCol;
    @FXML
    private TableColumn<Book, String> nameCol;
    @FXML
    private TableColumn<Book, Integer> quantityCol;
    @FXML
    private TableColumn<Book, String> authorCol;
    @FXML
    private TableColumn<Book, String> genreCol;
    
    Main scene = new Main();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        BookDAO book = new BookDAO();
        List<Book> books = book.getAllBooks();
        ObservableList<Book> allBooks = FXCollections.observableArrayList(book.getAllBooks());
        bookTable.setItems(allBooks);
    }

    public void backHome() {
        try {
            scene.changeScene("landingFXML.fxml");
        } catch (IOException ex) {
            Logger.getLogger(LandingFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public void allBooks() {
//        frame1.setCenter(frame);
        loadPage("/BooksPage.fxml");
    }
    
    public void listBorrowers() {
        loadPage("/BorrowersPage.fxml");
    }
    
    public void listAllReservations() {
        loadPage("/ReservationsPage.fxml");
    }
    
    public void loadPage(String page){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(page));
            frame1.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(LibrarianPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addBookButton(ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddBook.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LibrarianPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initTable() {
        
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
    genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
}
    
}
