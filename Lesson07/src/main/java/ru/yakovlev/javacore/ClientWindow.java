package ru.yakovlev.javacore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
 *@author Yakovlev Alexandr
 */

public final class ClientWindow extends JFrame {

    JTextArea jtMessageArea = new JTextArea();
    JTextField jtMessage = new JTextField();
    JTextField jtLogin = new JTextField();
    JLabel jlLogin = new JLabel("Login");
    JTextField jtPassword = new JTextField();
    JTextField jtUsers = new JTextField();
    JLabel jlPassword = new JLabel("Password");
    JButton send = new JButton("send");
    JButton auth = new JButton("auth");
    JButton find = new JButton("find");
    JPanel bottmPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel jpAuth = new JPanel();
    JPanel jpChat = new JPanel();
    JPanel jpUsers = new JPanel();
    JPanel jpFriendList = new JPanel();
    JTabbedPane tabbedPane = new JTabbedPane();
    JList<String> jListUsers = new JList<>();
    private boolean flaqVisible = false;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientWindow() {

        setTitle("Easygram");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(Constant.PADDING_X, Constant.PADDING_Y, Constant.WIDTH_WINDOW, Constant.HEIGHT_WINDOW);
        setResizable(false);
        setLayout(new BorderLayout());

        setUpTabsPanel();
        setUpChatTab();
        setUpAuthorizeTab();
        setUpUsersListTab();
        setInitChat();

        setVisible(true);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jtMessage.getText().trim().isEmpty()) {
                    sendMsg();
                }
            }
        });

    }

    public void setUpTabsPanel() {
        add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.addTab("Chat", jpChat);
        tabbedPane.addTab("Settings", jpAuth);
        tabbedPane.addTab("Users", jpUsers);
    }

    public void setUpChatTab() {
        jpChat.setLayout(new BorderLayout());
        bottmPanel.setLayout(new BorderLayout());
        bottmPanel.add(jtMessage, BorderLayout.CENTER);
        bottmPanel.add(send, BorderLayout.EAST);
        jtMessageArea.setEditable(false);
        jpChat.add(jtMessageArea, BorderLayout.CENTER);
        jpChat.add(bottmPanel, BorderLayout.SOUTH);
    }

    public void setUpUsersListTab() {
        jpUsers.setLayout(new BorderLayout());
        topPanel.setLayout(new BorderLayout());
        topPanel.add(jtUsers, BorderLayout.CENTER);
        topPanel.add(find, BorderLayout.EAST);
        jtMessageArea.setEditable(false);
        jpUsers.add(jListUsers, BorderLayout.CENTER);
        jpUsers.add(topPanel, BorderLayout.NORTH);
    }

    public void setUpAuthorizeTab() {
        jpAuth.setLayout(new GridLayout(10, 2));
        jpAuth.add(new JPanel());
        jpAuth.add(jlLogin);
        jpAuth.add(jtLogin);
        jpAuth.add(new JPanel());
        jpAuth.add(new JPanel());
        jpAuth.add(jlPassword);
        jpAuth.add(jtPassword);
        jpAuth.add(new JPanel());
        jpAuth.add(new JPanel());
        jpAuth.add(auth);

        auth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String log = jtLogin.getText();
                String pass = jtPassword.getText();
                onAuthClick(log, pass);
            }
        });
    }

    public void setInitChat() {
        try {
            socket = new Socket(Constant.SERVER_ADDRES, Constant.SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            setAuthorized(false);
            Thread t = new Thread(() -> {
                try {
                    while (true) { //петля авторизации
                        String str = in.readUTF();
                        if (str.startsWith(Constant.AUTHORIZE_OK)) {
                            setAuthorized(true);
                            break;
                        }
                        jtMessageArea.append(str + "\n");
                    }
                    while (true) { //петля чтения
                        String str = in.readUTF();
                        if (str.equals(Constant.END)) {
                            break;
                        }
                        jtMessageArea.append(str + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setAuthorized(false);
                }
            });
            t.setDaemon(true);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onAuthClick(String log, String pass) {
        try {
            String login = log;
            String password = pass;
            out.writeUTF(Constant.AUTHORIZE + " " + login + " " + password);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAuthorized(boolean v) {
        if (v == true) {
            jtMessageArea.append(Constant.CONNECT);
            tabbedPane.setSelectedIndex(0);
        }
    }

    public void sendMsg() {
        try {
            final String msg = jtMessage.getText();
            out.writeUTF(msg);
            jtMessage.setText("");
        } catch (IOException e) {
            System.out.println("Ошибка отправки сообщения");
        }
    }

    public static void main(String[] args) {
        new ClientWindow();
    }
}
