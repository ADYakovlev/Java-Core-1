package ru.yakovlev.javacore;

import ru.yakovlev.javacore.service.AuthService;
import ru.yakovlev.javacore.service.BaseAuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Yakovlev Alexandr
 */

public class MyServer {

    private ServerSocket server;
    private List<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try {
            server = new ServerSocket(Constant.SERVER_PORT);
            Socket socket = null;
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения...");
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при работе сервера");
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            authService.stop();
        }
    }

    public synchronized List<String> getUserList() {
        List<String> name = new ArrayList<>();
        for (ClientHandler o : clients) {
            name.add(o.getName());
        }
        return name;
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) return true;
        }
        return false;
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public synchronized void privateMsg(String nameFrom, String msg) {
        String[] spl = msg.split(" ");
        System.out.println("Приватное сообщение от " + nameFrom + " для " + spl[1]);
        for (ClientHandler o : clients) {
            if (o.getName().equals(spl[1])) {
                o.sendMsg("Приватное от " + nameFrom + " : " + spl[2]);
            }
        }
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o);
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.add(o);
    }

    public static void main(String[] args) {
        new MyServer();
    }

}
