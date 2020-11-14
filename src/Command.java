import java.io.Serializable;

public class Command implements Serializable {
    User user;
    String code;
    String room;
    String message;

    protected Command(User user, String code, String room, String message){
        this.user = user;
        this.code = code;
        this.room = room;
        this.message = message;
    }

    protected User getUser(){
        return this.user;
    }

    protected String getCode(){
        return this.code;
    }

    protected String getRoom() { return this.room; }

    protected String getMessage() { return this.message;}
}