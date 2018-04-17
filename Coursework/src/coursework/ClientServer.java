
package coursework;

import java.net.*;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author lydialane
 */
public class ClientServer {
  
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("192.168.0.16", 22);
            System.out.println("Connected to " + server.getInetAddress());
            
            //create io streams
            DataInputStream inFromServer = new DataInputStream(server.getInputStream());
            DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
            String messageFromServer = inFromServer.readUTF();
            
            // take user input
            Scanner in = new Scanner (System.in);
                     // input loop until matches username or password
            boolean matchesCredentials = false;
            
            while (matchesCredentials == false) {
            //send to server
                try {
                
                System.out.println("Server said: " + messageFromServer);
                String username = in.nextLine();
                String password = in.nextLine();
                outToServer.writeUTF(username);
                outToServer.writeUTF(password);
                messageFromServer = inFromServer.readUTF();
                System.out.println("Server said: " + messageFromServer);
                String isCorrect = inFromServer.readUTF();
                if ("true".equals(isCorrect)) {
                    matchesCredentials = true;
                }
                else //if ("false".equals(isCorrect))
                {
                    matchesCredentials = false;
                    messageFromServer = inFromServer.readUTF();
                }
                //messageFromServer = inFromServer.readUTF();
                }
                
            catch (IOException e) {
            }
        }
        
    }
}
