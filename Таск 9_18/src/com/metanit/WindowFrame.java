package com.metanit;

import util.ArrayUtils;
import util.JTableUtils;
import util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class WindowFrame extends JFrame {


    private JPanel panelMain;
    private JTable tableInput1;
    private JTable tableOutput;
    private JButton buttonSaveOutputIntoFile;
    private JButton buttonSaveInputInfoFile;
    private JButton buttonExecute;
    private JButton buttonLoadInputFromFile1;
    private JTable tableInput2;
    private JButton buttonLoadInputFromFile2;



    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;

    public WindowFrame() {

        JFrame jfrm = new JFrame();

        jfrm.setTitle("Task_08");
        jfrm.setContentPane(panelMain);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();

        JTableUtils.initJTableForArray(tableInput1, 200, true, true, true, true);
        JTableUtils.initJTableForArray(tableInput2, 200, true, true, true, true);
        JTableUtils.initJTableForArray(tableOutput, 200, true, true, true, true);
        //tableOutput.setEnabled(false);
        tableInput1.setRowHeight(25);
        tableInput2.setRowHeight(25);
        tableOutput.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        jfrm.setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        jfrm.pack();
        jfrm.setVisible(true);

        buttonLoadInputFromFile1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                            int[] dataArr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                            JTableUtils.writeArrayToJTable(tableInput1, dataArr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonSaveInputInfoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {

                            int[] dataArr = JTableUtils.readIntArrayFromJTable(tableInput1);
                            String file = fileChooserSave.getSelectedFile().getPath();
                            if (!file.toLowerCase().endsWith(".txt")) {
                                file += ".txt";
                            }
                            ArrayUtils.writeArrayToFile(file, dataArr);

                    }

                } catch (Exception except) {
                    SwingUtils.showErrorMessageBox(except);
                }
            }
        });


        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[] dataArr1 = JTableUtils.readIntArrayFromJTable(tableInput1);
                    int[] dataArr2 = JTableUtils.readIntArrayFromJTable(tableInput2);

                    List<Integer> list1 = Arrays.stream(dataArr1).boxed().toList();
                    List<Integer> list2 = Arrays.stream(dataArr2).boxed().toList();

                  List<Integer> list3 = Logic.createNewList(list1, list2);
                  Logic.sort(list3);

                   int[] outDataArr = new int[list3.size()];

                   for (int i = 0; i < list3.size(); i++) {
                       outDataArr[i] = list3.get(i);
                   }

                   JTableUtils.writeArrayToJTable(tableOutput, outDataArr);

                } catch (Exception except) {
                    SwingUtils.showErrorMessageBox(except);
                }
            }
        });

        buttonSaveOutputIntoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] dataArr = JTableUtils.readIntArrayFromJTable(tableOutput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, dataArr);
                    }
                } catch (Exception except) {
                    SwingUtils.showErrorMessageBox(except);
                }
            }
        });

        buttonLoadInputFromFile2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] dataArr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput2, dataArr);
                    }
                } catch (Exception ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
