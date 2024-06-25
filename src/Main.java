/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Amalitech
 */
public class Main extends Application {
    private static  Stage stage;
    @Override
    public void start(Stage primaryStage) {
        try {
                stage =primaryStage;
                Parent  root = FXMLLoader.load(getClass().getResource("landingFXML.fxml"));

                Scene scene = new Scene(root, 900, 500);

                primaryStage.setTitle("Katros Library");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void changeScene(String fxml) throws IOException{
        Parent  pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
