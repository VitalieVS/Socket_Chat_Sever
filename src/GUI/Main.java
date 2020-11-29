package GUI;

import javax.swing.*;

public class Main {
    private JButton button1;
    private JPanel mainPanel;

    public static void main(String[] args) {
        Main form = new Main();
        form.initComponents();
    }

    private void initComponents(){
        JFrame frame = new JFrame("Socket");
        frame.setContentPane(new Main().mainPanel);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
