/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiel;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import spiel.FXMLDocumentController;

/**
 *
 * @author gafaa
 */
public class Spiel extends Application {
    
    //private static FXMLDocumentController objFXML = new FXMLDocumentController();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        /*
        ArrayList<String> arrayVar = new ArrayList();
        arrayVar.add("Hii");
        arrayVar.add("Hii2");
        arrayVar.add("Hii3");
        //String chatHistory = convertArrayList(arrayVar);
        //loadChatBox(chatHistory);
        */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        //FXMLDocumentController.loadChatBox("blah blah");
        
        
        
        
    }
    
    public static void printToScreen(FXMLDocumentController obj, String input){
        obj.loadChatBox(input);
    }
    
    
    public String convertArrayList(ArrayList tempArrayList) {
        String returnString = "";
        for (int i = 0; i < tempArrayList.size(); i++) {
            returnString += tempArrayList.get(i) + "\n";
        }
        System.out.println(returnString);
        return returnString;
    }
    public static void loadChatHistory(String tempChatHistory){
    //chatboxOutput
    
    }
}
