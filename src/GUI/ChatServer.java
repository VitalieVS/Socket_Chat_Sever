package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private JPanel mainPanel;
    public JButton serverSendButton;
    private JTextField serverTextField;
    private JTextArea serverTextArea;

    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;


    public ChatServer() {
        serverSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msgout = "";

                msgout = serverTextField.getText().trim();
                try {
                    dout.writeUTF(msgout); // send
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void messaging() {
        String msgin = "";
        try {
            ss = new ServerSocket(1201); // port
            s = ss.accept();

            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());


            while (!msgin.equals("exit")) {
                msgin = din.readUTF();
                serverTextArea.setText(
                        serverTextArea.getText().trim() + "\n" + msgin);

            }
        } catch(Exception e) {
            System.out.println("error:" + e);
        }
    }

    public static void main(String[] args) {
        ChatServer form = new ChatServer();
        form.initComponents();
        form.messaging();
    }

    private void initComponents(){
        JFrame frame = new JFrame("Chat Server");
        frame.setContentPane(new ChatServer().mainPanel);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
