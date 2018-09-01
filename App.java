import java.io.*;
import java.util.*;

/*
 *@author Yakovlev Alexandr
 */
public class App {
    public static void main(String[] args) throws IOException {

        /*1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;*/

        readFile(mkFile("file50.txt", 50));

        /*2. Последовательно сшить 5 файлов в один (файлы также ~100 байт).*/
        SequenceInputStream in = null;
        BufferedOutputStream out = null;
        mkFile("1file100.txt",100);
        mkFile("2file100.txt",100);
        mkFile("3file100.txt",100);
        mkFile("4file100.txt",100);
        mkFile("5file100.txt",100);
        try {
            Enumeration<InputStream> streams = Collections.enumeration(List.of(
                    new FileInputStream("1file100.txt"),
                    new FileInputStream("2file100.txt"),
                    new FileInputStream("3file100.txt"),
                    new FileInputStream("4file100.txt"),
                    new FileInputStream("5file100.txt")
                    ));
            in = new SequenceInputStream(streams);
            out = new BufferedOutputStream(new FileOutputStream("fileFull.txt"));

            int data;
            while((data = in.read())!=-1){
                out.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }

        /*3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
         * Вводим страницу, программа выводит ее в консоль (за страницу можно принять 1800 символов).
         * Время чтения файла должно оставаться в разумных пределах: программа не должна загружаться дольше 10 секунд.
         * Чтение тоже не должно занимать более 5 секунд.
         * */



    }

    private static File mkFile(String fileName, int fileSize) {
        File file = new File(fileName);
        byte[] bytes = new byte[fileSize];
        for (int i = 0; i < fileSize; i++) {
            bytes[i] = (byte) i;
        }
        writeFile(bytes, file);
        return file;
    }

    private static void writeFile(byte[] bytes, File file) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile(File file) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            int x;
            while ((x = bufferedInputStream.read()) != -1) {
                System.out.print(x + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
