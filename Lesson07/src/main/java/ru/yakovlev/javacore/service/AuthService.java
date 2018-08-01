package ru.yakovlev.javacore.service;

/*
 *@author Yakovlev Alexandr
 */

public interface AuthService {

    void start();

    String getNickByLoginPass(String login, String pass);

    void stop();

}

