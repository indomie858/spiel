/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author gafaa
 */
public class FXMLDocumentController implements Initializable {
    
    //@FXML
    //private Label label;
    
    @FXML public TextArea chatboxOutput = new TextArea();
    
    @FXML
    private void handleSendButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
    }
    
    @FXML
    private void handleConnectButtonAction(ActionEvent event) {
        
    }
    
    @FXML
    private void handleDisconnectButtonAction(ActionEvent event) {
        
    }
    
    @FXML
    private void handleChatboxInput(){
        
    }
    
    @FXML
    private void handleChatboxOutput(){
        
    }
    
    @FXML
    private void handleUsernameInputbox(){
        
    }
    
    public void loadChatBox(String tempChatBox){
        chatboxOutput.setEditable(true);
        chatboxOutput.appendText(tempChatBox);
        chatboxOutput.setEditable(false);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Spiel spielObj = new Spiel();
        ArrayList list = new ArrayList();
        
        list.add("hghghghgh");
        
        spielObj.convertArrayList(list);
        
        
    }    
    
}
