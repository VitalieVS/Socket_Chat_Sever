package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    private JPanel mainPanel;
    private JTextArea clientTextArea;
    private JTextField clientTextField;
    private JButton clientSendButton;

    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    public ChatClient() {
        clientSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msgout = "";
                msgout = clientTextField.getText().trim();
                try {
                    dout.writeUTF(msgout);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void main(String[] args) {
        ChatClient form = new ChatClient();
        form.initComponents();
        form.messaging();
    }

    public void messaging() {
        try {
            s = new Socket("127.0.0.1", 1201);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            String msgin = "";
            while (!msgin.equals("exit")) {
                msgin = din.readUTF();
                clientTextArea.setText(
                        clientTextArea.getText().trim() + "\n Server:\t" + msgin);
            }
        } catch (Exception e) {
            System.out.println("error:" + e);
        }
    }

    private void initComponents(){
        JFrame frame = new JFrame("Chat Client");
        frame.setContentPane(new ChatClient().mainPanel);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
