import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class SendButtonListener implements ActionListener {
    PrintWriter writer;
    JTextField outgoing;
    JTextArea incoming;

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
             writer.println(outgoing.getText());
             writer.flush();
            System.out.println(outgoing.getText());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        outgoing.setText("");
        outgoing.requestFocus();
    }

    public SendButtonListener(PrintWriter pw, JTextField jtf){
        writer = pw;
        outgoing=jtf;
    }
}
