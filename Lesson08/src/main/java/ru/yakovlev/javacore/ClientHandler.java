package ru.yakovlev.javacore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Timer;
import java.util.TimerTask;

/*
 *@author Yakovlev Alexandr
 */

public class ClientHandler {
    private MyServer myServer;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;

    public String getName() {
        return name;
    }

    public void setSocket(){

    }

    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            new Thread(() -> {autharization();}).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при созданиии обработчика клиента");
        }
    }

    public void autharization() {
        try {
            while (true) {
                String str = in.readUTF();
                if (str.startsWith(Constant.AUTHORIZE)) {
                    String[] parts = str.split("\\s");
                    String nick = myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                    if (nick != null) {
                        if (!myServer.isNickBusy(nick)) {
                            sendMsg(Constant.AUTHORIZE_OK + nick);
                            name = nick;
                            myServer.broadcastMsg(name + Constant.CHAT_ENTERED);
                            myServer.subscribe(this);
                            break;
                        } else sendMsg("Учтенная записть уже используется");
                    } else {
                        sendMsg("Неверные логин/пароль");
                    }
                }
            }
            while (true) {
                String str = in.readUTF();
                System.out.println("От " + name + ": " + str);
                if (str.equals(Constant.END)) break;
                else if (str.startsWith(Constant.PRIVATE_MESSAGE)) {
                    myServer.privateMsg(this.name, str);
                } else myServer.broadcastMsg(name + ": " + str);
            }
        } catch (IOException e) {
            System.out.println("Время вышло");
        } finally {
            myServer.unsubscribe(this);
            myServer.broadcastMsg(name + " вышел из чата");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void CheckAutharisationTimer(Socket socket) {
        long timer_s = 0;
        long timer_f = 0;
        long timer_d = Constant.AUTHORIZATION_TIME;
        boolean flaq = true;
        while (flaq) {
            try {
                if (timer_s == 0) {
                    timer_s = System.currentTimeMillis();
                    timer_f = timer_s + timer_d;
                }
                if (timer_f <= System.currentTimeMillis()) {
                    socket.close();
                    System.out.println("подключение разорвано");
                    flaq = false;
                    timer_s = 0;
                }
            } catch (IOException e) {
                System.out.println("Timer IOExcepyion");
            }
        }

    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
