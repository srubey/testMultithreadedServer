import java.io.*;
import java.net.*;

public class Server {
    protected static RoomList rooms;
    protected static UserList users;

    public static void main(String[] args) throws IOException {
        rooms = new RoomList();
        users = new UserList();
        int port = 6789;

        ServerSocket svrSocket = new ServerSocket(port);

        System.out.println("Server process initiated");
        System.out.println("Listening on port " + port + "\n");

        //start server, run persistently
        while (true) {
            Socket connectionSocket = null;

            try
            {
                // socket object to receive incoming client requests
                connectionSocket = svrSocket.accept();
                System.out.println("Connected to new client " + connectionSocket);

                // obtaining input and out streams
                DataInputStream inStream = new DataInputStream(connectionSocket.getInputStream());
                DataOutputStream outStream = new DataOutputStream(connectionSocket.getOutputStream());

                System.out.println("Assigning thread to client " + connectionSocket);

                // create a new thread object
                Thread thread = new ClientHandler(connectionSocket, inStream, outStream);
                thread.start();
            }
            catch (Exception e){
                connectionSocket.close();
                e.printStackTrace();
            }
        }
    }
}
