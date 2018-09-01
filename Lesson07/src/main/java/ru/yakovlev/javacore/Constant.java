package ru.yakovlev.javacore;

/*
 *@author Yakovlev Alexandr
 */

public final class Constant {
    public static final String SERVER_ADDRES = "localhost";
    public static final Integer SERVER_PORT = 8190;
    public static final Integer PADDING_X = 400;
    public static final Integer PADDING_Y = 400;
    public static final Integer WIDTH_WINDOW = 300;
    public static final Integer HEIGHT_WINDOW = 400;
    public static final String END = "/end";
    public static final String PRIVATE_MESSAGE = "/w";
    public static final String AUTHORIZE_OK = "/authok";
    public static final String AUTHORIZE = "/auth";
    public static final String CHAT_ENTERED = ": Зашел в чат";
    public static final String CONNECT = "Вы успешно авторизовались...\n";
    public static final long AUTHORIZATION_TIME = 12000;

    private Constant() {
    }

}
