package DataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array2List {
    public static void main(String[] args) {
        Integer[] in1 = {1, 2, 3, 4, 5};
        Integer[] in2 = {3, 4, 5, 6, 7, 8};
        List<Integer> list = new ArrayList<>(Arrays.asList(in2));
        for (int i : in1) {
            //System.out.println(i);
            if (!list.contains(i)) {
                list.add(i);
            }
        }
        Integer[] results = list.toArray(new Integer[0]);
        System.out.println(results.length);
        String str = "sh";

    }
}
