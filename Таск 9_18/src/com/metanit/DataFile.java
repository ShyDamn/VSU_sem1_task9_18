package com.metanit;

import java.io.*;
import java.util.*;



public class DataFile {

    public static void writeToFile(List<Integer> list, String file){
        try (PrintWriter writer = new PrintWriter(file,"UTF-8")){
                writer.print(list);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println("Ошибка");
        }
    }

    public static void readFile( List<Integer> list, String file) {

        try (Scanner scanner = new Scanner(new File(file))){

            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }

        } catch (FileNotFoundException e) {
            System.err.print("Error");
        }

    }

}
