/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside;

import java.net.*;
import java.io.*;
/**
 *
 * @author lydialane
 */
public class ClientSide implements Runnable{
    String username;
    String password;
    
    Socket server;
    DataInputStream inFromServer; 
    DataOutputStream outToServer; 
    
    public ClientSide(String _username, String _password) throws IOException
    {
        username = _username;
        password = _password;
    }
    
    @Override
    public void run()
    {
        try
        {
            server = new Socket("192.168.0.16", 22);
            System.out.println("Connected to " + server.getInetAddress());
            
            inFromServer = new DataInputStream(server.getInputStream());
            outToServer = new DataOutputStream(server.getOutputStream());
            
            outToServer.writeUTF(username);
            outToServer.writeUTF(password);
            
            
            
        }
        catch (IOException e) { };
    }
    
}
