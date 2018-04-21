package demoproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Borthwick
 */

// ---------- https://www.youtube.com/watch?v=Uo5DY546rKY


public class A_CHAT_SERVER_RETURN implements Runnable{
    
    //Globals
    Socket SOCK;
    private Scanner INPUT;
    private PrintWriter OUT;
    String MESSAGE = "";
    
    //---------------------------------------------------------
    public A_CHAT_SERVER_RETURN(Socket X){
        this.SOCK = X;
    }
    //---------------------------------------------------------
    public void CheckConnection() throws IOException{
        
        if (!SOCK.isConnected()){
            
            for (int i = 1; i <= A_CHAT_SERVER.ConnectionArray.size(); i++) {
                if (A_CHAT_SERVER.ConnectionArray.get(i) == SOCK){
                    
                    A_CHAT_SERVER.ConnectionArray.remove(i);
                }
            }
            for(int i = 1; i <= A_CHAT_SERVER.ConnectionArray.size(); i++) {
                Socket TEMP_SOCK = (Socket) A_CHAT_SERVER.ConnectionArray.get(i-1);
                PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
                TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName() + " Disconnected!");
                TEMP_OUT.flush();
                //Shows Disconnection
                System.out.println(TEMP_SOCK.getLocalAddress().getHostName() + " Disconnected!");
            }  
        }
    }
    //---------------------------------------------------------
    public void run() {
        try {
            try {
                INPUT = new Scanner(SOCK.getInputStream());
                OUT = new PrintWriter(SOCK.getOutputStream());
                
                while(true) {
                    CheckConnection();
                    
                    if(!INPUT.hasNext()){
                        return;}
                    
                    MESSAGE = INPUT.nextLine();
                    
                    System.out.println("Client said: " + MESSAGE);
                    
                    for(int i = 1; 1<= A_CHAT_SERVER.ConnectionArray.size(); i++) {
                        Socket TEMP_SOCK = (Socket) A_CHAT_SERVER.ConnectionArray.get(i-1);
                        PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
                        TEMP_OUT.println(MESSAGE);
                        TEMP_OUT.flush();
                        System.out.println("Sent to: " + TEMP_SOCK.getLocalAddress().getHostName());
                    }
                }
            }
            finally{
                SOCK.close(); }
        }
    catch (Exception X) { System.out.print(X); }
    }
}
