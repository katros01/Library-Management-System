/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Amalitech
 */
public class AddBookController implements Initializable {

    @FXML
    private TextField nameId;
    @FXML
    private TextField quantityId;
    @FXML
    private TextField authorId;
    @FXML
    private TextField genreId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addButon(ActionEvent event) {
        
        String name = nameId.getText();
//        int quantity = Integer.valueOf(quantityId.getText());
        String quantity = quantityId.getText();
        String author = authorId.getText();
        String genre = genreId.getText();

        if (name.isEmpty() || quantity.isEmpty() || author.isEmpty() || genre.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            Book book = new Book(name, Integer.valueOf(quantityId.getText()), "", author, genre);
            BookDAO bookContainer = new BookDAO();
            
            bookContainer.insertBook(book);

        }
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        nameId.setText(null);
        quantityId.setText(null);
        authorId.setText(null);
        genreId.setText(null);
    }
    
}
