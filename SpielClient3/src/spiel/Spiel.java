//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

package spiel;


import client.ClientThread;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;


/**
 *
 * @author gafaa
 */
public class Spiel extends Application {
    
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        
        Image imageIcon = new Image("/spiel/spiel_icon.jpg");
       
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(imageIcon);
        stage.setTitle("S.P.I.E.L");
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { 
               stage.setX(event.getScreenX() - xOffset);
               stage.setY(event.getScreenY() - yOffset);
            }
        });
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent t) {
            Platform.exit();
            System.exit(0);
        }
        });
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
  
    }
    
    
}
