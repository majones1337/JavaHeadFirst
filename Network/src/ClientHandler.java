import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {
    BufferedReader reader;
    Socket sock;

    public ClientHandler(Socket clientSocket){
        try{
            sock = clientSocket;
            InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(isReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;
        try{
            while ((message = reader.readLine())!=null){
                System.out.println("read " + message);
                SimpleChatServer.tellEveryone(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}