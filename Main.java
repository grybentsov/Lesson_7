package ru.geekbrains.lesson_7;

import java.io.*;
import java.util.*;


public class Main {
    static String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ,.;:/abcdefghijklmnopqrsruvwxvz0123456789+-*/=";
    static Random random = new Random();

    public static void main(String[] args) {

        writeIntoFile("C:/java/file001.txt", stringCreator(50));
        writeIntoFile("C:/java/file002.txt", stringCreator(100));

        String [] files = {"file001.txt", "file002.txt"};
        writeIntoThirdFile(files, "file003.txt");

        toFindWord("file002.txt", "cat");

    }

    // 1.Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет)
    // метод для генерации строки заданной длины
    public static String stringCreator(int stringLength) {
        StringBuilder stringBuilder = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++) {
            stringBuilder.append(symbols.charAt(random.nextInt(symbols.length())));
        }
        return stringBuilder.toString();
    }

    // МЕтод для создания и записи текста в файл
    public static void writeIntoFile(String fileName, String text) {
        try {
            PrintWriter printWriter = new PrintWriter(fileName);
            printWriter.write(text);
            printWriter.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

        // 2.Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
    public static void writeIntoThirdFile(String [] files, String fileName){
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            for (String file: files){
                FileInputStream fis = new FileInputStream(fileName);
                int i;
                do {
                    i = fis.read();
                    if(i != -1){
                        fos.write(i);
                    }
                }while (i != -1);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    // 3* Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле (работаем только с латиницей).
    public static void toFindWord(String filePath, String word) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            int index = 0;
            int character = 0;
            byte[] wordBytes = word.getBytes();
            do {
                character = fis.read();
                if (wordBytes[index] == character) {
                    index++;
                    if (index == wordBytes.length) {
                        System.out.println("This word is found in the file.");
                        break;
                    } else {
                        index = 0;
                    }
                }
            } while (character != -1) ;
        } catch(FileNotFoundException ex){
                ex.printStackTrace();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }



