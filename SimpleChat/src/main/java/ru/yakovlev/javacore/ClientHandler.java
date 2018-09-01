package ru.yakovlev.javacore;

//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
                                sendMsg(Constant.AUTHORIZE_OK + " " + nick);
                                name = nick;
                                myServer.broadcastMsg(name + Constant.CHAT_ENTERED);
                                myServer.subscribe(this);
                                break;
                            } else sendMsg("Учтенная записть уже используется");
                        } else {
                            sendMsg("Неверные логин/пароль");
                        }
                    } else {
                    System.out.println("Хуйню прислали: "+ str);
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
            e.printStackTrace();
            System.out.println("Гавнище лезет с входящего потока");
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

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
