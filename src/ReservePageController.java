/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Model.Reservation;
import Model.ReservationDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Amalitech
 */
public class ReservePageController implements Initializable {

    @FXML
    private ComboBox<String> emailId;
    @FXML
    private ComboBox<String> bookfId;
    @FXML
    private Label pickUpDate;
    @FXML
    private DatePicker returnDate;
    @FXML
    private DatePicker pickDate;

    private List<User> userList;
    private List<Book> bookList;

    private final UserDAO userDAO = new UserDAO();
    private final BookDAO bookDAO = new BookDAO();
    private final ReservationDAO reservationDAO = new ReservationDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       userList = userDAO.getAllUsers();
        for (User user : userList) {
            emailId.getItems().add(user.getEmail()); // Populate ComboBox with emails
        }

        // Fetch all books and populate ComboBox
        bookList = bookDAO.getAllBooks();
        for (Book book : bookList) {
            bookfId.getItems().add(book.getName()); // Populate ComboBox with book names
        }
    }    

    @FXML
    private void getAddUser(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("RegisterPage.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReservePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addButon(ActionEvent event) {
         String selectedEmail = emailId.getValue();
        String selectedBookName = bookfId.getValue();
        java.util.Date pickDateValue = java.sql.Date.valueOf(pickDate.getValue());
        java.util.Date returnDateValue = java.sql.Date.valueOf(returnDate.getValue());

        Long userId = getUserIdByEmail(selectedEmail);
        Long bookId = getBookIdByName(selectedBookName);

        if (userId == null || bookId == null || pickDateValue == null || returnDateValue == null) {
            // Handle case where any of the fields are not selected
            return;
        }

        Reservation reservation = new Reservation(userId, bookId, pickDateValue, returnDateValue, "Reserved");
        reservationDAO.insertReservation(reservation);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Reservation entry added successfully.");
        alert.showAndWait();
            
        emailId.setValue(null);
        bookfId.setValue(null);
        pickDate.setValue(null);
        returnDate.setValue(null);
    }

    @FXML
    private void cancelButton(ActionEvent event) {
    }
    
     private Long getUserIdByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return user.getId();
            }
        }
        return null; 
    }

    private Long getBookIdByName(String name) {
        for (Book book : bookList) {
            if (book.getName().equals(name)) {
                return book.getId();
            }
        }
        return null; 
    }
    
}
