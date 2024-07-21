package resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DataStructures {

    public int maxArrayNumber(int[] array) {
        int max = array[0];
        for (int number : array) {
            if (number > max) {
                max = number;
            }
        }
        return max;

    }

    public int minArrayNumber(int[] array) {
        int min = array[0];
        for (int number : array) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }

    public void sortedArray(int[] array) {
        List<Integer> sortedArray = new ArrayList<>();
        for (int i : array) {
            sortedArray.add(i);
        }

        Collections.sort(sortedArray);

        System.out.println(sortedArray);

        IntStream.range(0, array.length).forEach(i -> array[i] = sortedArray.get(i));
    }
}
