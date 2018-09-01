import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.time.LocalDate.now;

/*
 *@author Yakovlev Alexandr
 */
public class Task03 {

    public static final int PAGE_SIZE = 1800;
    public static final int TEN_MB = 10 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
        File bigFile = new File("BigFile.txt");
        if (!bigFile.exists()) createBigFile(bigFile);
        System.out.println("Starting app: " + now());
        long pageCount = bigFile.length() / PAGE_SIZE;
        RandomAccessFile randomAccessFile = new RandomAccessFile(bigFile, "r");
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter page number: ");
                int pageNumber = sc.nextInt();
                if (pageNumber < 1 || pageNumber > pageCount) {
                    System.out.println("Invalid page...");
                    continue;
                }
                int position = PAGE_SIZE * (pageNumber - 1);
                randomAccessFile.seek(position);
                System.out.println("Start read page: " + now());
                for (int i = 0; i < PAGE_SIZE / 4; i++) {
                    System.out.print(randomAccessFile.read());
                }
                System.out.println("Finish read: " + now());
            }
        }
    }

    private static void createBigFile(File file) throws IOException {
        int fileSize = calculateFileSize();
        int data = 0;

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        while (file.length() < fileSize) {
            for (int i = 0; i < PAGE_SIZE / 4; i++) {
                out.writeInt(data);
            }
            data++;
            data %= 10;
        }
        out.close();
    }

    private static int calculateFileSize() {
        int fileSize = PAGE_SIZE;
        while (fileSize < TEN_MB) {
            fileSize += PAGE_SIZE;
        }
        return fileSize;
    }
}

