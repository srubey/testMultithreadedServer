import java.io.*;
import java.net.*;
import java.util.Scanner;

/*public class Client {
    protected static Scanner in = null;
    protected static Socket clientSocket = null;
    protected static String username = null;
    protected static DataInputStream inputStream;
    protected static DataOutputStream outputStream;

    public static void main(String[] args) {
        try {
            in = new Scanner(System.in);

            //connect to server
            boolean connected = connect("127.0.0.1", 6789);
            if (connected)
                System.out.println("Connected to server");
            else
                System.out.println("Error connecting to server");

            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());

            System.out.print("Please enter your username: ");
            username = in.next();
            System.out.print("\n");

            int choice = Menu.topMenu();
            boolean disc = false;  //flips to true if user opts to disconnect

            while (8 != choice) {
                String command = null;

                //if user opts to list existing rooms
                if (1 == choice) {
                    command = username + " LIST";
                }
                //if user opts to join an existing room
                else if (2 == choice) {
                    System.out.println("Please enter the name of the room you would like to join: ");
                    String roomName = in.next();

                    command = username + " JOIN " + roomName;
                }
                //if user opts to create a new room
                else if (3 == choice) {
                    System.out.print("Please enter the name of the room you would like to create: ");
                    String newRoomName = in.next();

                    command = username + " CTRM " + newRoomName;
                }
                //if user opts to list all users in a chat room
                else if (4 == choice) {
                    System.out.print("Please enter the name of the room whose members you would like to list: ");
                    String roomName = in.next();

                    command = username + " LSMB " + roomName;
                }
                //if user opts to post a msgText to a chat room
                else if (5 == choice) {
                    System.out.print("Please enter the name of the room you would like to post to: ");
                    String roomName = in.next();
                    in.nextLine();

                    System.out.print("Please enter your message: ");
                    String message = in.nextLine();

                    command = username + " POST " + roomName + " " + message;
                }
                //if user opts to retrieve all messages posted to a chat room
                else if (6 == choice) {
                    System.out.print("Please enter the name of the room whose messages you would like shown: ");
                    String roomName = in.next();

                    command = username + " RETV " + roomName;
                }
                //if user opts to leave a chat room
                else if (7 == choice) {
                    System.out.print("Please enter the name of the room you would like to leave: ");
                    String roomName = in.next();

                    //command = username + " LEAV " + roomName;
                    command = "Date";
                }
                //if user opts to disconnect
                else if (8 == choice) {
//                command = username + " DISC ";
                    command = "Exit";
                    disc = true;
                }

                //send command to the server, capture the server's return msgText
                String retMsg = sendCommand(command);

                //if user opts to disconnect, close the connection
                if (disc) {
                    //disconnect from server
                    clientSocket.close();
                    System.out.println("Disconnected from server");
                    break;
                }
                //if not disconnected, send return msgText to handler, print result
                else {
                    System.out.print(msgHandler(retMsg));

                    //return to menu
                    choice = Menu.topMenu();
                }
            }
            in.close();
            inputStream.close();
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //connect to the server
    protected static boolean connect(String address, int port) {
        boolean success = false;

        try {
            clientSocket = new Socket(address, port);
            success = true;
        } catch (IOException i) {
            System.out.println(i);
        }

        return success;
    }

    //disconnect from the server, close all resources
    protected static boolean disconnect(){
        boolean success = false;

        try {
            clientSocket.close();
            in.close();
            inputStream.close();
            outputStream.close();
            success = true;
        }
        catch(IOException i) {
            System.out.println(i);
        }

        return success;
    }

    //send command to the server in the form of a string object
    protected static String sendCommand(String command){
        String message = "";

        try {
            //send command to server
            outputStream.writeUTF(command);

            //read msgText returned from server
            Object obj = inputStream.readUTF();
            message = obj.toString();
        }catch(IOException io){
            message = "Server communcation error";
        }

        return message;
    }

    //handle messages returned from server
    protected static String msgHandler(String retMsg){
        String toPrint;

        //return user-friendly msgText derived from server response.
        //if server returns info other than OK or ERR msgs, just return that info (i.e. lists, chat messages, etc)
        switch(retMsg){
            case "OK_CTRM":
                toPrint = "\nRoom Created\n";
                break;
            case "OK_JOIN":
                toPrint = "\nRoom joined\n";
                break;
            case "OK_LEAV":
                toPrint = "\nRoom left\n";
                break;
            case "OK_POST":
                toPrint = "\nMessage posted\n";
                break;
            case "ERR_DUPLICATEROOM":
                toPrint = "\nA room by that name already exists.  Please choose a different name.\n";
                break;
            case "ERR_NOROOMS":
                toPrint = "\nThere are currently no rooms available to list\n";
                break;
            case "ERR_NONEXISTENTROOM":
                toPrint = "\nRoom does not exist\n";
                break;
            case "ERR_NOTINROOM":
                toPrint = "\nYou are not currently in that room\n";
                break;
            case "ERR_ROOMEMPTY":
                toPrint = "\nRoom is empty\n";
                break;
            case "ERR_ILLEGALCOMMAND":
                toPrint = "\nClient error: illegal command.  Please contact the developer.\n";
                break;
            case "ERR_NOMESSAGES":
                toPrint = "\nNo messages have been posted to this room\n";
                break;
            default:
                toPrint = "\n" + retMsg;
        }

        return toPrint;
    }
}*/

public class Client {
    protected static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        try
        {
            // establish the connection with server port 5056
            Socket s = new Socket("127.0.0.1", 6789);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true)
            {
                System.out.println(dis.readUTF());
                String tosend = in.nextLine();
                dos.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop
                if(tosend.equals("Exit"))
                {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // printing date or time as requested by client
                String received = dis.readUTF();
                System.out.println(received);
            }

            // closing resources
            in.close();
            dis.close();
            dos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
