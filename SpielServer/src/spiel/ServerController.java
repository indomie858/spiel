//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

package spiel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.Server;

/**
 *
 * @author gafaa
 */
public class ServerController implements Initializable {  //server controller
    private int portNumber = 0;
    private Server serverObj;
    
    @FXML
    private TextField portTextField = new TextField();
    @FXML
    private Button startServerButton = new Button();
    @FXML
    private void handleStartButtonAction(ActionEvent event) {
 
       serverObj = new Server();
        
    }
    
    @FXML
    private void handleEndButtonAction(ActionEvent event) {
        
        
        System.out.println("Server closed");
        System.exit(0);
 
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}