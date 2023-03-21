package com.metanit;


import javax.swing.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static class InputArgs {
        public String inputFile;
        public String inputFile1;
        public String outputFile;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static InputArgs parseArgs(String[] args) {
        InputArgs params = new InputArgs();

        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (args.length < 2) {
                params.help = true;
                params.error = true;
                return params;
            }
            params.inputFile = args[0];
            if (args.length > 1) {
                params.inputFile1 = args[1];
                params.outputFile = args[2];
            }
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }

    public static void winMain() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WindowFrame();
            }
        });
    } 

    public static void main(String[] args) throws RuntimeException {

        InputArgs params = parseArgs(args);

        if (params.help) {
            PrintStream out = params.error ?  System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }

        if (params.window) {
            winMain();
        } else {

            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            List<Integer> list3;

            DataFile.readFile(list1, params.inputFile);
            DataFile.readFile(list2, params.inputFile1);

            list3 = Logic.createNewList(list1, list2);
            Logic.sort(list3);

            DataFile.writeToFile(list3, params.outputFile);

        }



    }
}
