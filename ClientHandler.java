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

                if(!msg.equals(key)){
                    out.println("couldn't handshake");
                    out.flush();
                    //System.out.println("DEBUG 1: is it here?");
                    return;  
                }
                out.println("12345");
                out.flush();
            
            Thread.sleep(2000);

            while((msg = in.readLine()) != null){
                // will this properly find the int in the message?
                try{
                int intNumber = Integer.parseInt(msg);
                if (intNumber > Integer.MAX_VALUE){
                    out.println("There was an exception on the server");
                    out.flush();
                    break;
                }
                ArrayList <Integer> factors = new ArrayList<>();
                
                // factorization
                for(int i=1; i<= intNumber; i++){
                    if (intNumber % i == 0){
                        factors.add(i);
                        //System.out.println(i);
                        
                    }
                }
                System.out.println(factors);
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
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
                if (sock != null){
                    sock.close();
                }
            
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
