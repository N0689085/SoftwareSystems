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
public class ClientRegisterHandler implements Runnable
{
DataOutputStream outToClient;
DataInputStream inFromClient;
Socket client;

String sendToClient;
String dataFromClient;

String fileName = null;
File newUser;
FileWriter fout;
PrintWriter pout;


    public ClientRegisterHandler(Socket _client) throws IOException {
        client = _client;
        outToClient = new DataOutputStream(client.getOutputStream());
        inFromClient = new DataInputStream(client.getInputStream());
    } //constructor
    
    @Override
    public void run()
    {
        try 
        {
            fileName = inFromClient.readUTF();
            newUser = new File("C:\\Users\\wajon\\OneDrive\\Documents\\Project\\NetworkServer\\src\\networkserver\\Users\\" + fileName + ".txt");
            if (newUser.createNewFile())
            {
                fout = new FileWriter(newUser,false);
                pout = new PrintWriter(fout, true);
                //runs the loop to input the password, name, date of  birth and location, all with a "*" inbetween
                for (int i = 0; i < 4; i++)
                {
                    dataFromClient = inFromClient.readUTF();
                    System.out.println(dataFromClient);
                    pout.println(dataFromClient + System.lineSeparator());
                }
                pout.close(); //close the stream   
                
            }
            else
            {
                sendToClient = "Username already exists";
                outToClient.writeUTF(sendToClient);
                System.out.println(sendToClient);
            }
        }
        catch (IOException e) 
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
