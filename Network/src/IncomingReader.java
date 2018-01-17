import javax.swing.*;
import java.io.BufferedReader;

public class IncomingReader implements Runnable {
    BufferedReader reader;
    JTextArea incoming;

    @Override
    public void run() {
        String message;
        try{
            while((message=reader.readLine()) != null){
                System.out.println("should write in incoming: " + message);
                incoming.append(message + "\n");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public IncomingReader(BufferedReader br, JTextArea jta){
        reader = br;
        incoming = jta;
    }
}
