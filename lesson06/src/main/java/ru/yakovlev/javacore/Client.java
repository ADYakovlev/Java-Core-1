package ru.yakovlev.javacore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.jar.JarFile;

/*
 *@author Yakovlev Alexandr
 */
public class Client extends JFrame {
    private String SERVER_ADDR = "localhost";
    private int SERVER_PORT = 8189;
    private Socket sock;
    private Scanner in;
    private PrintWriter out;

    final JTextField textField = new JTextField("", 15);
    JPanel panel = new JPanel();

    public Client() {
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 300, 400);
        setLayout(new BorderLayout());
        setResizable(false);

        JButton send = new JButton("Send");
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(textArea);

        add(textArea, BorderLayout.CENTER);
//        add(jScrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        panel.add(textField);
        panel.add(send);

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("\nClient: "+textField.getText());
                sendMsg();
            }
        });


        setVisible(true);


        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            public void run() {
                while (true){
                    textArea.append("\nServer: "+in.nextLine());
                }
            }
        }).start();

    }


    public void sendMsg() {
        out.println(textField.getText());
        out.flush();
        textField.setText("");
    }

}
