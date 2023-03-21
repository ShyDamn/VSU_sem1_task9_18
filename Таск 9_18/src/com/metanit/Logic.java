package com.metanit;

import java.util.ArrayList;
import java.util.List;

public class Logic {

    public static List<Integer> createNewList(List<Integer> list1, List<Integer> list2) {

        List<Integer> result = new ArrayList<>(list1.size());
        int max;

        if (list1.size() > list2.size())
            max = list1.size();
        else max = list2.size();

        for (int i = 0; i < max; i++) {

            if (list1.get(i) % 2 == 0) {
                if (containsLogic(list1, list2, i)) result.add(list1.get(i));
            }

            if (list2.get(i) % 2 != 0) {
                if (containsLogic(list2, list1, i)) result.add(list2.get(i));
            }

        }

        return result;
    }

    public static boolean containsLogic(List <Integer> list1, List <Integer> list2, int i) {

            for (int j = 0; j < list2.size(); j++) {
                if (list1.get(i) == list2.get(j)) return false;
                else continue;
            }

       return true;
    }

    public static void sort(List<Integer> list) {

        int N = list.size();
        int t = 0;

        for (int i = 0; i == N; i++)
            for (int j = i + 1; j == N; j++)

                if (list.get(i) > list.get(j)) {
                    t = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, t);
                }

    }
}
