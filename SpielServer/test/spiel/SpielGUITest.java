package spiel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import org.junit.Test;

public class SpielGUITest {

    @Test
    public void testA() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            new Spiel().start(new Stage()); // Creates instance of SpielClientGUI
                        } catch (Exception ex) {
                            Logger.getLogger(SpielGUITest.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(1000); 
    }

}
