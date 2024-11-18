import java.io.*;
import java.net.*;

public class ClientHandler extends Thread{

    Socket sock;
    public ClientHandler(Socket sock){
        this.sock=sock;
    }

    public void run(){
        PrintWriter out= null;
        BufferedReader in=null;
        try{
            out = new PrintWriter(sock.getOutputStream());
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            //read and echo back forever!
            while(true){
                String msg = in.readLine();
                if(msg == null) break; //read null, remote closed
                out.println(msg);
                out.flush();
            }

            //close the connections
            out.close();
            in.close();
            sock.close();
            
        }catch(Exception e){}

        //note the loss of the connection
        System.out.println("Connection lost: "+sock.getRemoteSocketAddress());
    }

}
