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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Amalitech
 */
public class BooksPageController implements Initializable {

    @FXML
    private AnchorPane frame;
    @FXML
    private TableView<Book> listBooks;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        BookDAO book = new BookDAO();
        List<Book> books = book.getAllBooks();
        ObservableList<Book> allBooks = FXCollections.observableArrayList(book.getAllBooks());
        listBooks.setItems(allBooks);
        System.out.println("@@@@@@@@@@@");
    }    

    @FXML
    private void getAddBook(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddBook.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BooksPageController.class.getName()).log(Level.SEVERE, null, ex);
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
