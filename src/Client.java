import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    protected static Socket clientSocket = null;
    protected static Scanner scanner = new Scanner(System.in);
    protected static String username = null;
    protected static DataInputStream inputStream = null;
    protected static DataOutputStream outputStream = null;

    public static void main(String[] args) throws IOException {
        try {
            System.out.print("Please enter your username: ");
            username = scanner.next();
            System.out.print("\n");

            //connect to server
            boolean connected = connect("127.0.0.1", 6789);
            if(connected)
                System.out.println("Connected to server");
            else
                System.out.println("Error connecting to server");

            // obtaining input and out streams
            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());

            int choice = Menu.topMenu();

            //TODO: structured programming
            while (true){
                String command = null;

                //if user opts to list existing rooms
                if (1 == choice){
                    command = username + " LIST";
                }
                //if user opts to join an existing room
                else if (2 == choice){
                    System.out.println("Please enter the name of the room you would like to join: ");
                    String roomName = scanner.next();

                    command = username + " JOIN " + roomName;
                }
                //if user opts to create a new room
                else if (3 == choice) {
                    System.out.print("Please enter the name of the room you would like to create: ");
                    String newRoomName = scanner.next();

                    command = username + " CTRM " + newRoomName;
                }
                //if user opts to list all users in a chat room
                else if (4 == choice){
                    System.out.print("Please enter the name of the room whose members you would like to list: ");
                    String roomName = scanner.next();

                    command = username + " LSMB " + roomName;
                }
                //if user opts to post a msgText to a chat room
                else if (5 == choice){
                    System.out.print("Please enter the name of the room you would like to post to: ");
                    String roomName = scanner.next();
                    scanner.nextLine();

                    System.out.print("Please enter your message: ");
                    String message = scanner.nextLine();

                    command = username + " POST " + roomName + " " + message;
                }
                //if user opts to retrieve all messages posted to a chat room
                else if (6 == choice){
                    System.out.print("Please enter the name of the room whose messages you would like shown: ");
                    String roomName = scanner.next();

                    command = username + " RETV " + roomName;
                }
                //if user opts to leave a chat room
                else if (7 == choice){
                    System.out.print("Please enter the name of the room you would like to leave: ");
                    String roomName = scanner.next();

                    //command = username + " LEAV " + roomName;
                    command = roomName;
                }
                //if user opts to disconnect from server
                else if (8 == choice){
                    command = "Exit";
                }

                outputStream.writeUTF(command);

                if(command.equals("Exit"))
                {
                    System.out.println("Closing this connection : " + clientSocket);
                    clientSocket.close();
                    System.out.println("Connection closed");
                    break;
                }

                // printing date or time as requested by client
                String received = inputStream.readUTF();
                System.out.println(received);

                choice = Menu.topMenu();
            }

            // closing resources
            scanner.close();
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
}
