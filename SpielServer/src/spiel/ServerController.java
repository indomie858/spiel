//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

package spiel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import server.Server;
import server.ServerThread;

/**
 *
 * @author gafaa
 */
public class ServerController implements Initializable {  //server controller
    private int portNumber = 0;
    private Server serverObj;
    private ServerThread serverThread;
    

    @FXML
    private TextArea serverTextfield = new TextArea();
    @FXML
    private Button startServerButton = new Button();
    @FXML
    private Button forkBomb = new Button();
    @FXML
    private Button exitButton = new Button();
    @FXML
    private void handleStartButtonAction(ActionEvent event) {
       serverObj = new Server(this);
       serverThread = serverObj.getServerThread();
       startServerButton.setDisable(true);
       forkBomb.setDisable(false);
       exitButton.setDisable(true);
    }
    @FXML
    private void exitButtonAction(ActionEvent event){
        System.exit(0);
    }
    
    @FXML
    private void handleEndButtonAction(ActionEvent event) throws IOException, InterruptedException {           
            serverObj.serverOffline();       
            serverTextfield.appendText("FORK BOMB initiated!! \nAll connected clients have been wiped out! \n" );
            exitButton.setDisable(false);
            startServerButton.setDisable(false);
            forkBomb.setDisable(true);
            serverObj.closeServerSocket();
            serverTextfield.appendText("Server is now offline..\n");
    }
    
    public void updateChatBoxOutput(String text){
        serverTextfield.appendText(text);
    }
    
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
