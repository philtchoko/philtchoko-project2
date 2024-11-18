import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientHandler extends Thread{

    Socket sock;
    public ClientHandler(Socket sock){
        this.sock=sock;
    }

    public void run(){
        PrintWriter out = null;
        BufferedReader in = null;


        try{
            out = new PrintWriter(sock.getOutputStream());
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String msg = in.readLine();
                String key = "12345";
                if(!(msg.equals(key))){
                    out.println("couldn't handshake");
                    //System.out.println("DEBUG 1: is it here?");
                    sock.close();  
                }
                else{
                    out.println("12345");
                    out.flush();
                }
            //read and echo back forever!
            // why do i loop forever?
            while(true){
                if(msg == null){
                    break;
                }
                // will this properly find the int in the message?
                try{
                int intNumber = Integer.parseInt(msg);
                ArrayList <Integer> factors = new ArrayList<>();
                // factorization
                for(int i=1; i<= intNumber; i++){
                    if (intNumber % i == 0){
                        factors.add(i);
                    }
                }
                out.println("The number " + intNumber + " has " + factors.size() + " factors");
                out.flush();
                }
                catch(Exception e){
                    System.err.println("Integer was not entered");
                }
            }

            //close the connections
            // System.out.println("DEBUG: is it here?");
            try{
                out.close();
                in.close();
                sock.close();
            }
            catch(Exception e) {
                out.println("IO Exception");

            }
        }
        catch(Exception e){
            out.println("There was an exception on the server");
            out.flush();
        }

        //note the loss of the connection
        System.out.println("Connection lost: "+ sock.getRemoteSocketAddress());
    }

}
