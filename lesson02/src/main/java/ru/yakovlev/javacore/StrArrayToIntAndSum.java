package ru.yakovlev.javacore;

/*
 *@author Yakovlev Alexandr
 * 1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4, при подаче
 * массива другого размера необходимо бросить исключение MyArraySizeException.
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
 * Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или
 * текст вместо числа), должно быть брошено исключение MyArrayDataException – с детализацией, в
 * какой именно ячейке лежат неверные данные.
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения
 * MySizeArrayException и MyArrayDataException и вывести результат расчета.
 */
public class StrArrayToIntAndSum {
    public static void main(String[] args)throws MyArrayDataException, MyArraySizeException{
        String[][] array = {
                {"1", "a", "3", "4"},
                {"5", "6", "3", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"},
        };
        try {
            System.out.println("Array sum = "+sum(array));
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }

    }
    public static int sum(String[][] array)throws MyArrayDataException, MyArraySizeException{
        int sum=0;
        int MAX_SIZE_X=4;
        int MAX_SIZE_Y=4;
        int x=0;
        int y=0;
        String ans;
        if (array.length!=MAX_SIZE_X||array[0].length!=MAX_SIZE_Y) throw new MyArraySizeException();
        try {
            for (x=0; x < array.length; x++) {
                for (y = 0; y < array[0].length; y++) {
                    sum += Integer.parseInt(array[x][y]);
                }
            }
        } catch (Exception e) {
            throw new MyArrayDataException(e, x+1, y+1);
        }

        return sum;
    }
}
