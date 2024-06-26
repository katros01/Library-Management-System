/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Model.Borrow;
import Model.BorrowDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UpdateStatusPageController implements Initializable {

    @FXML
    private ComboBox<String> statusId;

    
    private Borrow borrowToUpdate;

    private final BorrowDAO borrowDAO = new BorrowDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initData(Borrow borrow) {
        this.borrowToUpdate = borrow;
        
        statusId.getItems().addAll("Borrowed", "Returned", "Overdue");
    }

    @FXML
    private void updateStatusButton(ActionEvent event) {
        String newStatus = statusId.getValue();
        if (newStatus != null) {
            try {
                borrowToUpdate.setStatus(newStatus);
                
                borrowDAO.updateBorrow(borrowToUpdate);
                closeStage();
            } catch (SQLException ex) {
                Logger.getLogger(UpdateStatusPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            
        }
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        closeStage();
    }
    
    private void closeStage() {
        Stage stage = (Stage) statusId.getScene().getWindow();
        stage.close();
    }
    
}
