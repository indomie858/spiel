//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

package client;

/**
 *
 * @author gafaa
 */
public class Message {
    private String message;
    private boolean isNewMessage = false;

    
    public String getMessage(){
        isNewMessage = false;
        return message;
    }
    
    public void setMessage(String message){
        this.message = message;
        isNewMessage = true;
    }

    public boolean isNewMessage() {
        return isNewMessage;
    }
    
    
    
}
