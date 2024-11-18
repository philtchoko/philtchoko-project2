import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    private String host;
    private int port;
    private Socket socket = null;
    private String IP;

    public Client(String host, int port){
        this.host = host;
        this.port = port;

        try{
            socket = new Socket(host, port);
        }
        catch(Exception e){
            System.err.print("issue with the constructor of client");
            e.printStackTrace();
        }
    }

    public Socket getSocket(){
        return socket;
    }

    public String getAddress(){
        return IP;
    }

    public int getPort(){
        return port;
    }

    public void handshake(){
        String key = "12345";
        
        try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println(key);
            writer.flush();
            // possible bug here
            String response = reader.readLine();
            if(key.equals(response)){
                System.out.println("handshake succesful");
            }

        }
        catch(Exception e){
            System.err.print("There was an exception on the server");
    

        }
        


        return;
    }


    public String request(String number){
        String response = ""; 
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println(number);
            writer.flush();
            //fetching response

            // will this return null? wondering how fast the socket receives the output from the server
            response = reader.readLine();
            // disconnect();
        }
        catch(Exception e){
            System.err.print("request method issue");
            e.printStackTrace();
            System.exit(1);
        }
        

        return response;
    }
    // should just close the socket, that should be fine
    public void disconnect(){
        try{
            socket.close();
        }
        catch(Exception e){
            System.err.print("disconnect method issue for client");
            e.printStackTrace();
            System.exit(1);
        }
    }




    /* 
    int intNumber = Integer.parseInt(number);
    ArrayList <Integer> factors = new ArrayList<>();
    for(int i=0; i<= intNumber; i++){
        if (intNumber % i == 0){
            factors.add(i);
        }
    }
    */

    /* 
    public static void main(String args[]){

        Socket sock=null;        
        try{
           sock = new Socket("localhost",2021);
        }catch(Exception e){
            System.err.println("Cannot Connect");
            System.exit(1);
        }

        try{
            PrintWriter pw = new PrintWriter(sock.getOutputStream());
            pw.println("HelloWorld");
            pw.close(); //close the stream
            sock.close();//close the socket
        }catch(Exception e){
            System.err.print("IOException");
            System.exit(1);
        }

    }


    */


}
