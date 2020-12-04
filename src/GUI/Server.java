package GUI;

import Log.Log;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
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

    Log chatLog;

    public Server() {
        initComponents();
        chatLog = new Log();
        serverSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msgout = "";

                msgout = serverTextField.getText().trim();
                try {
                    dout.writeUTF(msgout);
                    if (serverTextArea.getText().isEmpty()) {
                        serverTextArea.setText(
                                serverTextArea.getText().trim() + msgout);
                    } else {
                        serverTextArea.setText(
                                serverTextArea.getText().trim() + "\n" + msgout);
                    }
                    chatLog.writeLog("Server:", msgout);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                chatLog.openLog();
            }

            @Override
            public void windowClosing(WindowEvent e) {
               chatLog.closeLog();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        serverMessaging();
    mainPanel.addHierarchyBoundsListener(new HierarchyBoundsAdapter() { } );
        mainPanel.addComponentListener(new ComponentAdapter() {
        });
        mainPanel.addContainerListener(new ContainerAdapter() {
        });
        mainPanel.addFocusListener(new FocusAdapter() {
        });
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
                if (serverTextArea.getText().isEmpty()) {
                    serverTextArea.setText(
                            serverTextArea.getText().trim() + "Client:" + msgin);
                }
                else {
                    serverTextArea.setText(
                            serverTextArea.getText().trim() + "\nClient:" + msgin);
                }
                chatLog.writeLog("Client:", msgin);

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
