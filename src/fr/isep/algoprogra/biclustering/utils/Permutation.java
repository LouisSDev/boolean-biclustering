package fr.isep.algoprogra.biclustering.utils;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    /**
     * The core method here that is called recursively to generate all of the possible
     * combinations.
     *
     * @param arr the array to sort out
     * @param data the actual data array used
     * @param start the starting index for the combination search
     * @param end the end index for the combination search
     * @param index the index we work on
     * @param r the size of the combinations to search for
     * @param combinations the already retrieved combinations to add new one to
     */
    private static void combinationUtil(int arr[], int data[], int start, int end, int index, int r, List<int[]> combinations)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
            int[] combination = new int[r];
            for (int j = 0; j < r; j++)
                combination[j] = data[j];
            combinations.add(combination);
            return;
        }
 
        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++)
        {
            data[index] = arr[i];
            combinationUtil(arr, data, i + 1, end, index + 1, r, combinations);
        }
    }

    /**
     *
     *
     * The main function that returns all combinations of size r
     * in arr[] of size n. This function mainly uses combinationUtil()
     *
     * @param arr the array to find all combinations for
     * @param r the size of the combinations to find
     * @return the list of combinations of size r
     */
    public static List<int[]> createCombinations(int arr[], int r)
    {
        // A temporary array to store all combination one by one
        int data[] = new int[arr.length];
 
        // Print all combination using temprary array 'data[]'
        List<int[]> combinations = new ArrayList<>();
        combinationUtil(arr, data, 0, arr.length - 1, 0, r, combinations);
        return combinations;
    }

    /**
     * Works very similarly as the other combination creation method {@link #createCombinations(int[], int)}
     * but instead return all combinations of size between fromR and toR
     * @param arr the array to find combinations forr
     * @param fromR the minimum size of the combinations to search for
     * @param toR the maximum size of the combinations to search for
     * @return all relevant combinations of size ranging from fromR to toR
     */
    public static List<int[]> createCombinations(int arr[], int fromR, int toR) {


        List<int[]> combinations = new ArrayList<>();


        for (int r = fromR; r <= toR; r++) {

            int data[] = new int[arr.length];

            combinationUtil(arr, data, 0, arr.length - 1, 0, r, combinations);

        }

        return combinations;
    }
 

}