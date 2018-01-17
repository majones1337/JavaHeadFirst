import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatClient {
    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;

    public static void main(String[] args){
        SimpleChatClient client = new SimpleChatClient();
        client.go();
    }

    public void go(){
        JFrame frame = new JFrame("Simple chat client");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15,50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        setUpNetworking();
        sendButton.addActionListener(new SendButtonListener(writer,outgoing));
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);


        Thread readerThread = new Thread(new IncomingReader(reader,incoming));
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(700,500);
        frame.setVisible(true);
    }

    private void setUpNetworking(){
        try {
            sock = new Socket(InetAddress.getLocalHost(),5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
