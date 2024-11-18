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
            System.err.print("IO Exception");
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
        String passcode = "12345";
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the pin: ");
            String pin = scanner.nextLine();
            if (!(pin.equals(passcode))){
                disconnect();
            }
            scanner.close();
        }
        catch(Exception e){
            System.err.print("There was an exception on the server");
            System.exit(1);

        }


        return;
    }


    public String request(String number){
        String response = null;
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println("Sending request: Factorize " + number);
            //fetching response
            response = reader.readLine();

        }
        catch(Exception e){
            System.err.print("There was an exception on the server");
            System.exit(1);
        }

        return response;
    }

    public void disconnect(){
        try{
            socket.close();
        }
        catch(Exception e){
            System.err.print("IO Exception");
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
