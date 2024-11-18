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

            //read and echo back forever!
            while(true){
                String msg = in.readLine();
                int intNumber = Integer.parseInt(msg);
                ArrayList <Integer> factors = new ArrayList<>();
                if (msg == null){
                    break;
                }
                for(int i=0; i<= intNumber; i++){
                    if (intNumber % i == 0){
                        factors.add(i);
                    }
                }
                out.println("The number " + intNumber + " has " + factors.size() + " factors");
                out.flush();
            }

            //close the connections
            out.close();
            in.close();
            sock.close();
            
        }catch(Exception e){}

        //note the loss of the connection
        System.out.println("Connection lost: "+ sock.getRemoteSocketAddress());
    }

}
