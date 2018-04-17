/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author wajon
 */
public class clientRequestHandler implements Runnable
{
    String clientRequest;
    Socket client;
    DataInputStream inFromClient;
    public clientRequestHandler(Socket _client) throws IOException {
        client = _client;
        inFromClient = new DataInputStream(client.getInputStream());
    } //constructor
    
    @Override
    public void run()
    {
        try
        {
            clientRequest = inFromClient.readUTF();
            System.out.println(clientRequest);
            if ("login".equals(clientRequest))
            {
                ClientLoginHandler th = new ClientLoginHandler(client);
                Thread t = new Thread(th);
                t.start();
            }
            else if ("register".equals(clientRequest))
            {
                ClientRegisterHandler th = new ClientRegisterHandler(client);
                Thread t = new Thread(th);
                t.start();
            }
            else
            {
                System.out.println("Protocol not found!, aborting");
            }
        }
        catch (IOException e) { }
    }
}
