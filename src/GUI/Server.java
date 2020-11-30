package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {
    private JPanel mainPanel;
    public JButton serverSendButton;
    private JTextField serverTextField;
    private JTextArea serverTextArea;

    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;


    public Server() {
        initComponents();
        serverSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msgout = "";

                msgout = serverTextField.getText().trim();
                try {
                    dout.writeUTF(msgout);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        serverMessaging();
    }

    public void serverMessaging() {
        String msgin = "";
        try {
            ss = new ServerSocket(1201);
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

    void initComponents(){
        this.setTitle("Socket - Server");
        this.setContentPane(mainPanel);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
