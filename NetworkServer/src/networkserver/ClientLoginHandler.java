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
public class ClientLoginHandler implements Runnable{
DataOutputStream outToClient;
DataInputStream inFromClient;
Socket client;

String username;
String password;
String correctPassword;
String sendToClient;
String isCorrect;

//boolean loginState = true;

String fileName = null;
FileReader fin;
BufferedReader din;
    public ClientLoginHandler(Socket _client) throws IOException 
    {
        client = _client;
        outToClient = new DataOutputStream(client.getOutputStream());
        inFromClient = new DataInputStream(client.getInputStream());
    } //constructor

@Override
public void run() {
//while (loginState)
//{
    try 
    {
        //Prompts the client trying to connect for a username and password

        //takes in the username and password entered by the user
        username = inFromClient.readUTF();
        password = inFromClient.readUTF();
        //currently used for testing purposes to see what is being sent to the server
        System.out.print("Username: " + username +"\n");
        System.out.print("Password: " + password + "\n");
        //database uses username as the text file name, sets this variable to the username entered by the client
        fileName = username;
            try 
            {
                //searches for the text file in the database
                fin = new FileReader("C:\\Users\\wajon\\OneDrive\\Documents\\Project\\NetworkServer\\src\\networkserver\\Users\\" + fileName + ".txt");
                //if it finds it will set to isCorrect is true (used later to nofity the client on the outcome
                isCorrect = "true";
            }
            catch (FileNotFoundException ex) 
            {
                //catch block to handle FileNotFoundException, will notify the user that a username cannot be found
                sendToClient = "Username incorrect, please try again!\n";
                isCorrect = "false";
            }
        //if the file is found this block is executed to check if the password matches, else will just notify the user the username could not be found
        if ("true".equals(isCorrect))
        {
            din = new BufferedReader(fin);
            //reads the first line of the text file which will contain the password
            correctPassword = din.readLine();
            if (password.equals(correctPassword))
            {
                //notifies the client that their credentials are correct
                sendToClient = "Username and Password correct!\n";
            }
            else 
            {
                sendToClient = "Password incorrect, please try again\n!";
            }
        //sends the credentials are correct message back to the client    
        outToClient.writeUTF(sendToClient);
        System.out.print(sendToClient);
        }
        else
        {
            //notifies the user that their credentials are incorrect
            outToClient.writeUTF(sendToClient);
            System.out.print(sendToClient);
        }
        din.close();
    //catches incorrect input
    } catch (IOException e) { }

//}

    } //end of run
} //end of class
