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
public class ClientSideRegister implements Runnable
{
    String username;
    String password;
    String name;
    String dob;
    String location;
    String messageFromServer;
    
    Socket server;
    DataInputStream inFromServer; 
    DataOutputStream outToServer; 
    
    public ClientSideRegister(String _username, String _password, String _name, String _dob, String _location) throws IOException
    {
        username = _username;
        password = _password;
        name = _name;
        dob = _dob;
        location = _location;
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
            //tells server to create register thread
            outToServer.writeUTF("register");
            
            outToServer.writeUTF(username);
            outToServer.writeUTF(password);
            outToServer.writeUTF(name);
            outToServer.writeUTF(dob);
            outToServer.writeUTF(location);
           
            messageFromServer = inFromServer.readUTF();
            System.out.print(messageFromServer);
            
            Register.usernameTakenLabel.setText(messageFromServer);

        }
        catch (IOException e) { };
    }    
}
