import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleChatServer {
    static ArrayList clientOutputStreams;


    public static void main (String[] args){
        new SimpleChatServer().go();
    }

    public void go(){
        clientOutputStreams = new ArrayList();
        try{
            ServerSocket serverSock = new ServerSocket(5000);

            while(true){
                Socket clientSocket = serverSock.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("got a connection");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void tellEveryone(String message){
        Iterator it = clientOutputStreams.iterator();
        while(it.hasNext()){
            try{
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

}
