/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Model.Borrow;
import Model.BorrowDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Amalitech
 */
public class BorrowersPageController implements Initializable {

    @FXML
    private TableView<Borrow> borrowsTable;
    @FXML
    private TableColumn<Borrow, Long> idCol;
    @FXML
    private TableColumn<Borrow, String> nameCol;
    @FXML
    private TableColumn<Borrow, String> bookCol;
    @FXML
    private TableColumn<Borrow, String> dateCol;
    @FXML
    private TableColumn<Borrow, String> returnDateCol;
    @FXML
    private TableColumn<Borrow, String> statusCol;

    private final BorrowDAO borrowDAO = new BorrowDAO();
    private final UserDAO userDAO = new UserDAO();
    private final BookDAO bookDAO = new BookDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Initialize table columns
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(cellData -> {
            Long userId = cellData.getValue().getUserId();
            User user = userDAO.getUserById(userId);
            return new SimpleStringProperty(user.getName());
        });
        bookCol.setCellValueFactory(cellData -> {
            Long bookId = cellData.getValue().getBookId();
            Book book = bookDAO.getBookById(bookId);
            return new SimpleStringProperty(book.getName());
        });
        dateCol.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load borrow data into the table
        loadBorrows();
    }
    
    private void loadBorrows() {
        try {
            List<Borrow> borrows = borrowDAO.getAllBorrows();
            borrowsTable.getItems().addAll(borrows);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowersPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
