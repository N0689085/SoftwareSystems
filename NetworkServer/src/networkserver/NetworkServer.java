/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkserver;
import java.net.*; import java.io.*;
/**
 *
 * @author wajon
 */
public class NetworkServer 
{
    public static void main(String[] args) throws IOException 
    {
        ServerSocket server = new ServerSocket(22);
        while(true)
        {
            System.out.println("Waiting...");
            //establish connection
            Socket client = server.accept();
            System.out.println("Connected " + client.getInetAddress());
            
            clientRequestHandler th = new clientRequestHandler(client);
            Thread t = new Thread(th);
            t.start();
            
        }
    }
}




