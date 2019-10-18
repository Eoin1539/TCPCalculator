/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpechoserver;
import java.io.*;
import java.net.*;
/**
 * @author Eoin Kirwan x16472486
 */
public class TCPEchoServer 
{
  private static ServerSocket servSock;
  private static final int PORT = 1235;

  public static void main(String[] args) {
    System.out.println("Opening port...\n");
    try 
    {
        servSock = new ServerSocket(PORT);      //Step 1.
    }catch(IOException e) 
    {
         System.out.println("Unable to attach to port!");
         System.exit(1);
    }
    do {
         run();
    }while (true);

  }
  
  private static void run()
  {
    Socket link = null;                        
    try 
    {
      link = servSock.accept();              
      BufferedReader in = new BufferedReader( new InputStreamReader(link.getInputStream())); 
      PrintWriter out = new PrintWriter(link.getOutputStream(),true); 
     
      String message = in.readLine();         
      while (!message.equals("STOP")) 
      {
          
        if (message.matches("[0-9]+")&& message.matches("[-+*/]")){
          
          
         System.out.println("Equation received: " + message);
        //[-+*/]
        String[] equation = message.split("[-+*/]");
       double num1 =Double.parseDouble(equation[0]);
       double num2 =Double.parseDouble(equation[1]);
       String check1 = equation[0];
       String check2 = equation[1];
        String answer ="";
         double total =0;
       if(check1.equals("[0-9]+")&&check2.equals("[0-9]+")){
       
       if(message.contains("-")){
           total=num1-num2;
           answer=num1+"-"+num2+"="+total;
           out.println(answer); 
           System.out.println(answer);
        
       }
       
       else if(message.contains("+")){
           total=num1+num2;
           answer=num1+"+"+num2+"="+total;
           out.println(answer);
           System.out.println(answer);
         
       }
       
       else if(message.contains("*")){
           total=num1*num2;
           answer=num1+"*"+num2+"="+total;
           out.println(answer);   
           System.out.println(answer);
         
       }
       
       else if(message.contains("/")){
           total=num1/num2;
           answer=num1+"/"+num2+"="+total;
           out.println(answer); 
           System.out.println(answer);
         
       }
       
        message = in.readLine();
        
          
         
      	
        }
        else{
            out.println("Error, input not valid!");
                message = in.readLine();
            }
        }
       else{
                out.println("Error, input not valid!");
                message = in.readLine();
                }
    } 
    }
    catch(IOException e)
    {
        e.printStackTrace();
    }
    finally 
    {
       try {
	    System.out.println("\n* STOP command received* \n*Closing connection... *");
            link.close();				    
	}
       catch(IOException e)
       {
            System.out.println("Unable to disconnect!");
	    System.exit(1);
       }
    }
  } 
} 