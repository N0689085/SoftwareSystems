package demoproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Borthwick
 */
public class A_CHAT_SERVER{ 
    
    //Globals
    public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
    public static ArrayList<String> CurrentUsers = new ArrayList<String>();
    //--------------------------------------------------------------------------
    
    public static void main(String[] args) throws IOException{
        try{
            final int PORT =444;
            ServerSocket SERVER = new ServerSocket(PORT);
            System.out.println("Waiting for clients..");
            
            while(true){
            Socket SOCK = SERVER.accept();
            ConnectionArray.add(SOCK);
            
            System.out.println("Client connected from: " + SOCK.getLocalAddress().getHostName());
            AddUserName(SOCK);
            
            A_CHAT_SERVER_RETURN CHAT = new A_CHAT_SERVER_RETURN(SOCK);
            Thread X = new Thread(CHAT);
            X.start();
            
            }             
        }
        catch(Exception X) {System.out.print(X); }
    }
    //--------------------------------------------------------------------------
    
    public static void AddUserName(Socket X) throws IOException{
        Scanner INPUT = new Scanner(X.getInputStream());
        String UserName = INPUT.nextLine();
        CurrentUsers.add(UserName);
        
        for(int i = 1; 1<= A_CHAT_SERVER.ConnectionArray.size(); i++) {
            Socket TEMP_SOCK = (Socket) A_CHAT_SERVER.ConnectionArray.get(i-1);
            PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
            OUT.println("?!" + CurrentUsers);
            OUT.flush();
        }
        
    }
}
