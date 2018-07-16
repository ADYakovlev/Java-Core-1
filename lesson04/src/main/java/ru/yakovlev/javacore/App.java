package ru.yakovlev.javacore;

/*
 * @author Yakovlev Alexandr
 */

public class App {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 400;



    public static void main( String[] args ) {
    myWindow dialog = new myWindow(300,300,300,300, "Dialog",false);
    dialog.setVisible(true);
    }
}
