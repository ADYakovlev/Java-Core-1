package ru.yakovlev.javacore;

//import com.sun.tools.classfile.Opcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 *@author Yakovlev Alexandr
 * 1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
 * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 * Посчитать, сколько раз встречается каждое слово.
 */
public class App 
{

    public static void main( String[] args )
    {
        String[] arrayWords = {
                "Anna",
                "Anna",
                "Petr",
                "Ivan",
                "Alexandr",
                "Daria",
                "Gosha",
                "Vasya",
                "Bob",
                "Alla",
                "Ivan",
                "Zeus",
                "Michail",
                "Sergey",
                "Anton",
                "Margo",
                "Vitya",
                "Anna"
        };
        hashMapCount(arrayWords);
    }

    public static void hashMapCount (String[] array){

        HashMap<String, Integer> hm = new HashMap <String, Integer>();
        for (int i = 0; i < array.length ; i++) {
            Integer counter=0;
            for (int j = 0; j < array.length; j++) {
                if (array[i]==array[j])counter++;
            }
            hm.put(array[i],counter);
        }
        Set<Map.Entry<String, Integer>> set = hm.entrySet();
        for (Map.Entry<String, Integer> o: set) {
            System.out.print(o.getKey()+": ");
            System.out.println(o.getValue());
        }
    }

}
