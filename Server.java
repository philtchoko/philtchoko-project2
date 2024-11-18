import java.util.*;
import java.time.LocalDateTime;
import java.io.*;
import java.net.*;

public class Server {
    private int clients;
    private int port;
    private ArrayList <LocalDateTime> connectedTimes;
    private ServerSocket serverSock;
    

    public Server(int port){
        this.port = port;

        try{
            serverSock = new ServerSocket(port);
        }catch(Exception e){
            System.err.println("Cannot establish server socket");
            System.exit(1);
        }
    }

    public void serve(int n){
        for(int i=0; i<clients; i++){
            try{
                //accept incoming connection
                Socket clientSock = serverSock.accept();
                addTime();
                //System.out.println("New connection: "+ clientSock.getRemoteSocketAddress());
                
                //start the thread
                (new ClientHandler(clientSock)).start();
                
                //continue looping
            }catch(Exception e){
                System.err.print("IO Exception");
            } //exit serve if exception
        }
        return;
    }
    

    public ArrayList <LocalDateTime> getConnectedTimes(){
        return connectedTimes;
    }

    public void addTime(){
        LocalDateTime now = LocalDateTime.now();
        connectedTimes.add(now);
    }

    public void disconnect(){
        try{
            serverSock.close();
        }
        catch(Exception e){
            System.err.print("IO Exception");
            System.exit(1);
        }
    }
}
