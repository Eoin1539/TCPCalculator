/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpechoclient;
import java.io.*;
import java.net.*;

/**
 *
 * @author Eoin Kirwan x16472486 
 */
public class TCPEchoClient 
{
    private static InetAddress host;
    private static final int PORT = 1235;

    public static void main(String[] args) {
     try 
     {
        host = InetAddress.getLocalHost();
     } 
     catch(UnknownHostException e) 
     {
	System.out.println("Host ID not found!");
	System.exit(1);
     }
     run();
   }
    
   private static void run() {
    Socket link = null;				
    try 
    {
	link = new Socket(host,PORT);		
        
	BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
	PrintWriter out = new PrintWriter(link.getOutputStream(),true);	
        
	BufferedReader userEntry =new BufferedReader(new InputStreamReader(System.in));
	String message = null;
        String response= null;
        System.out.println("Welcome! To exit programme please enter STOP");
	do {
            System.out.print("Enter equation: ");
            message =  userEntry.readLine();
            out.println(message); 		
            response = in.readLine();		
            System.out.println("\n<SERVER> " + response);
	}while (!message.equals("STOP"));
    } 
    catch(IOException e)
    {
	//e.printStackTrace();
        System.out.println("\n* Server not started, please start the server first and then run the client*");
        System.exit(1);
    } 
    finally 
    {
        try 
        {
            
            System.out.println("\n* Closing connection... *");
            link.close();
            System.exit(1);
	}catch(IOException e)
        {
            System.out.println("Unable to disconnect!");
            System.exit(1);
	}
    }
 } 
}  
