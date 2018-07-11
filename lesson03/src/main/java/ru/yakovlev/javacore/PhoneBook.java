package ru.yakovlev.javacore;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

/*
 *@author Yakovlev Alexandr
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать
 * номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
 * (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
 * Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять
 * еще дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.).
 * Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main(), прописывая add() и get().
 */
public class PhoneBook {

    static HashMap<String, String> hs = new HashMap<String, String>();

    public static void main(String[] args) {
        String name = "Волненко";
        add("Яковлев","+79220000000");
        add("Волненко","+79220000001");
        add("Гончаров","+79220000002");
        add("Яковлев","+79220000003");
        add("Балакирева","+79220000004");
        add("Волненко","+79220000005");
        add("Ченцов","+79220000006");
        add("Чичук","+79220000007");
        add("Сыскаев","+79220000008");
        add("Телегин","+79220000009");
        System.out.println(get(name));
    }

    public static void add(String name, String number){
        hs.put(number,name);
    }

    public static ArrayList<String> get(String name){
        ArrayList<String> res = new ArrayList<String>();
        Set<Map.Entry<String, String>> set = hs.entrySet();
        for(Map.Entry<String, String> o : set) {
            if (o.getValue()==name) {
            res.add("Имя: "+o.getValue()+" Тел.: "+o.getKey());
            }
        }
        return res;
}

}
