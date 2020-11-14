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

            // the following loop performs the exchange of
            // information between client and client handler
            while (true)
            {
                System.out.println(inputStream.readUTF());
                String tosend = scanner.nextLine();
                outputStream.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop
                if(tosend.equals("Exit"))
                {
                    System.out.println("Closing this connection : " + clientSocket);
                    clientSocket.close();
                    System.out.println("Connection closed");
                    break;
                }

                // printing date or time as requested by client
                String received = inputStream.readUTF();
                System.out.println(received);
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
