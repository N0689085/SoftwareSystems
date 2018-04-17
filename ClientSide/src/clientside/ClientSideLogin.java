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
public class ClientSideLogin implements Runnable{
    String username;
    String password;
    String messageFromServer;
    
    Socket server;
    DataInputStream inFromServer; 
    DataOutputStream outToServer; 
    
    public ClientSideLogin(String _username, String _password) throws IOException
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
            //tells server to create login thread
            outToServer.writeUTF("login");
            
            outToServer.writeUTF(username);
            outToServer.writeUTF(password);
            
            messageFromServer = inFromServer.readUTF();
            System.out.print(messageFromServer);
            
            Login.unameLabel.setText(messageFromServer);

        }
        catch (IOException e) { };
    }
    
}
