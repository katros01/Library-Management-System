/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Model.Borrow;
import Model.BorrowDAO;
import Model.Reservation;
import Model.ReservationDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Amalitech
 */
public class TransactionPageController implements Initializable {
    
    @FXML
    private TextField emailTextField;

    @FXML
    private TableView<Borrow> borrowsTable;
    @FXML
    private TableColumn<Borrow, Long> idCol;
    @FXML
    private TableColumn<Borrow, String> bookCol;
    @FXML
    private TableColumn<Borrow, String> dateCol;
    @FXML
    private TableColumn<Borrow, String> returnDateCol;
    @FXML
    private TableColumn<Borrow, String> statusCol;
    @FXML
    private TableView<Reservation> reservationsTable;
    @FXML
    private TableColumn<Reservation, Long> resIdCol1;
    @FXML
    private TableColumn<Reservation, String> resBookCol1;
    @FXML
    private TableColumn<Reservation, String> resDateCol1;
    @FXML
    private TableColumn<Reservation, String> reservationCol;
    @FXML
    private TableColumn<Reservation, String> resReturnDateCol;
    @FXML
    private TableColumn<Reservation, String> resStatusCol;

    private final BorrowDAO borrowDAO = new BorrowDAO();
    private final ReservationDAO reservationDAO = new ReservationDAO();
    private final UserDAO userDAO = new UserDAO();
    private final BookDAO bookDAO = new BookDAO();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookCol.setCellValueFactory(cellData -> {
            Long bookId = cellData.getValue().getBookId();
            Book book = bookDAO.getBookById(bookId);
            return new SimpleStringProperty(book.getName());
        });
        dateCol.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Initialize table columns for reservations
        resIdCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        resBookCol1.setCellValueFactory(cellData -> {
            Long bookId = cellData.getValue().getBookId();
            Book book = bookDAO.getBookById(bookId);
            return new SimpleStringProperty(book.getName());
        });
        resDateCol1.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        reservationCol.setCellValueFactory(new PropertyValueFactory<>("pickUpDate"));
        resReturnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        resStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }    

    @FXML
    private void searchButton(ActionEvent event) {
        String email = emailTextField.getText().trim();
        if (!email.isEmpty()) {
            // Clear existing data in tables
            borrowsTable.getItems().clear();
            reservationsTable.getItems().clear();

            // Get all users
            List<User> users = userDAO.getAllUsers();
            System.out.println(users);
            // Find user ID for the given email
            Long userId = null;
            for (User user : users) {
                if (user.getEmail().equalsIgnoreCase(email)) {
                    userId = user.getId();
                    break;
                }
            }

            if (userId != null) {
                try {
                    // Retrieve borrows and reservations for the found user ID
                    List<Borrow> borrows = borrowDAO.getBorrowsByUserId(userId);
                    List<Reservation> reservations = reservationDAO.getReservationsByUserId(userId);
                    
                    // Populate borrows table
                    borrowsTable.getItems().addAll(borrows);
                    
                    // Populate reservations table
                    reservationsTable.getItems().addAll(reservations);
                } catch (SQLException ex) {
                    Logger.getLogger(TransactionPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
