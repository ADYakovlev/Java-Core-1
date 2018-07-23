package ru.yakovlev.javacore;

import java.io.*;
//import java.lang.invoke.StringConcatException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Hello world!
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket sock = null;
        boolean listen = true;
        try {
            serv = new ServerSocket(8189);
            System.out.println("srv run...");
            sock = serv.accept();
            System.out.println("client connect...");
            Scanner sc = new Scanner(sock.getInputStream());
            final PrintWriter pw = new PrintWriter(sock.getOutputStream());
            final Scanner sc1 = new Scanner(System.in);

            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        System.out.println("\n---------------");
                        String omsg = sc1.nextLine();
                        pw.println(omsg);
                        pw.flush();
                    }
                }
            }).start();

            while (listen) {
                String msg = sc.nextLine();
                if (msg.equals("/end")) break;
                System.out.println("\nClient: " + msg);
//                pw.println("эхо "+msg);
//                pw.flush();
//                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                writer.write()"asdasd"

            }
        } catch (IOException e) {
            System.out.println("Ошибка инициализации");
        } finally {
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        туц
    }
}
