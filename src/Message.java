import java.util.Date;
import java.text.SimpleDateFormat;

public class Message {
    protected String timestamp;
    protected String username;
    protected String msgText;

    protected Message(String username, String message){
        this.timestamp = new SimpleDateFormat("HH:mm MM/dd/yyyy").format(new Date());
        this.username = username;
        this.msgText = message;
    }

    @Override
    public String toString(){
        return this.timestamp + "\nUser: " + this.username + "\n" + this.msgText + "\n";
    }
}