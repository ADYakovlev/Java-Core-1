package ru.yakovlev.javacore;

/*
 *@author Yakovlev Alexandr
 */

public class MyArrayDataException extends Exception{
    private int x, y;

    public MyArrayDataException(Exception e, int x, int y){
        super(e.getMessage()+" Position is y:"+x+" x:"+y);
        this.x=x;
        this.y=y;
    }

}
