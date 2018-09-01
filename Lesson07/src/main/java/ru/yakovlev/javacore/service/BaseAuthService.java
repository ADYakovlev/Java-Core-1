package ru.yakovlev.javacore.service;

import java.util.ArrayList;
import java.util.List;

/*
 *@author Yakovlev Alexandr
 */

public class BaseAuthService implements AuthService {

    private class Entery {
        private String login;
        private String pass;
        private String nick;

        public Entery(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }

    private List<Entery> enteries;

    @Override
    public void start() {

    }

    @Override
    public String getNickByLoginPass(String login, String pass) {
        for (Entery o : enteries) {
            if (o.login.equals(login) && o.pass.equals(pass)) return o.nick;
        }
        return null;
    }

    @Override
    public void stop() {

    }

    public BaseAuthService() {
        enteries = new ArrayList<>();
        enteries.add(new Entery("login1", "pass1", "nick1"));
        enteries.add(new Entery("login2", "pass2", "nick2"));
        enteries.add(new Entery("login3", "pass3", "nick3"));

    }
}
