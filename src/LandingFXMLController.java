/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Amalitech
 */
public class LandingFXMLController implements Initializable {

    @FXML
    private Button patron;
    @FXML
    private Button liabrarian;

    Main scene = new Main();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openPatronPanel(ActionEvent event) {
        try {
            scene.changeScene("PatronPage.fxml");
        } catch (IOException ex) {
            Logger.getLogger(LandingFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openLibrarianPanel(ActionEvent event) {
        try {
            scene.changeScene("LibrarianPage.fxml");
        } catch (IOException ex) {
            Logger.getLogger(LandingFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
