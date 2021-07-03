import java.util.ArrayList;

/*
 *@author Yakovlev Alexandr
 *  обобщения
    1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
    2. Написать метод, который преобразует массив в ArrayList;
    3. Большая задача:
    Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
    Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
    Для хранения фруктов внутри коробки можно использовать ArrayList;
    Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
    Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
    Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
    Не забываем про метод добавления фрукта в коробку.
 */
public class Main<T> {

    public static <T> T[] change(T[] arr, int a, int b) {
        T var;
        var = arr[a];
        arr[a] = arr[b];
        arr[b] = var;
        return arr;
    }

    public static <T> ArrayList<T> toArrayList(T[] arr) {
        ArrayList<T> arrL = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            arrL.add(arr[i]);
        }
        return arrL;
    }

    public static <T> void arrPrint(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"1", "2", "3", "4",};
        arrPrint(arr);
        System.out.println("arr ------------");
        change(arr, 1, 2);
        arrPrint(arr);
        System.out.println("arrChange ------");

        Box<Apple> appleBox = new Box<>();
        for (int i = 0; i < 5; i++) {
            appleBox.add(new Apple());
        }
        System.out.println("appleBox weight: " + appleBox.getWeight());

        Box<Apple> appleBox1 = new Box<>();
        appleBox1.add(new Apple());
        System.out.println("appleBox1 weight: " + appleBox1.getWeight());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange());
        System.out.println("orangeBox weight: " + orangeBox.getWeight());

        System.out.println("Compare orangeBox and appleBox: " + orangeBox.compare(appleBox));

        appleBox.union(appleBox1);
        System.out.println("appleBox1 after union: " + appleBox1.getWeight());
    }
}
