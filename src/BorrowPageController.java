/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Model.Borrow;
import Model.BorrowDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Amalitech
 */
public class BorrowPageController implements Initializable {

    @FXML
    private ComboBox<String> emailId;
    private List<User> userList;
    @FXML
    private ComboBox<String> bookfId;
    private List<Book> bookList; // List to hold books fetched from DAO

    private final BookDAO bookDAO = new BookDAO();
    @FXML
    private DatePicker returnDate;

    private final UserDAO userDAO = new UserDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       userList = userDAO.getAllUsers();
        for (User user : userList) {
            emailId.getItems().add(user.getEmail());
            System.out.println(user.getEmail());
        }
        
       bookList = bookDAO.getAllBooks();
        for (Book book : bookList) {
            bookfId.getItems().add(book.getName());
            System.out.println(book.getName());
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
            Logger.getLogger(BorrowPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addButon(ActionEvent event) {
        String selectedEmail = emailId.getValue();
        Long userId = getUserIdByEmail(selectedEmail);
        String selectedBookName = bookfId.getValue();
        Long bookId = getBookIdByName(selectedBookName);
        
        String returnDateString = String.valueOf(returnDate.getValue());

        if (selectedEmail == null || selectedBookName == null || returnDateString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            
        try {
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date returnDateValue = null;
            try {
                returnDateValue = dateFormat.parse(returnDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            Borrow borrow = new Borrow(userId, bookId, returnDateValue, "Borrowed");
            BorrowDAO borrowDAO = new BorrowDAO(); 
            borrowDAO.addBorrow(borrow);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Borrow entry added successfully.");
            alert.showAndWait();
            
            
            emailId.setValue(null);
            bookfId.setValue(null);
            returnDate.setValue(null);
            
        } catch (SQLException ex) {
                Logger.getLogger(BorrowPageController.class.getName()).log(Level.SEVERE, null, ex); 
        }

        }
    }
    
    private Long getUserIdByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return user.getId();
            }
        }
        return null; // Handle case where email is not found
    }
    
    private Long getBookIdByName(String name) {
        for (Book book : bookList) {
            if (book.getName().equals(name)) {
                return book.getId();
            }
        }
        return null; // Handle case where book name is not found
    }

    @FXML
    private void cancelButton(ActionEvent event) {
    }
    
}
