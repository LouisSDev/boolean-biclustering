package fr.isep.algoprogra.biclustering.algorithm;

import fr.isep.algoprogra.biclustering.matrix.BooleanMatrix;
import fr.isep.algoprogra.biclustering.utils.Permutation;

import java.util.ArrayList;
import java.util.List;

public class ColumnCombinationsScanBiClusterer extends BiClusterer {


    private static final int MIN_COLUMNS_IN_COMMON = 8;
    private static final int MIN_ROWS_IN_COMMON = 4;
    private static final int MAX_ELEMENTS_IN_COMMON = 15;


    public ColumnCombinationsScanBiClusterer(BooleanMatrix matrix) {
        super(matrix);
    }

    @Override
    public List<BooleanMatrix> cluster() {

        List<BooleanMatrix> clusters = new ArrayList<>();

        /*
         * Converting the columns into an int[] to be used with
         * our combinations utils
         */
        int[] colArr = new int[matrix.getColumnsCount()];

        for (int i = 0; i < matrix.getColumnsCount(); i++) {
            colArr[i] = i;
        }

        // Creating the columns combinations
        List<int[]> columnsCombinations = Permutation.createCombinations(colArr, MIN_COLUMNS_IN_COMMON, MAX_ELEMENTS_IN_COMMON);



        for(int[] combination : columnsCombinations) {
            /*
             * For each one of them, we will find the rows that match all of this
             * combination requirements
             */
            List<Integer> commonRows = findCorrespondingRows(combination);

            /*
             * If we don't have enough rows matching this criteria, then we won't keep it
             * because it will be considered as a non viable cluster.
             */
            if(commonRows.size() >= MIN_ROWS_IN_COMMON) {

                /*
                 * We now want to transform the int[] into a List<Integer> for
                 * better interoperability with our other sub methodes
                 */
                List<Integer> commonColumns = new ArrayList<>();

                for (int i = 0; i < combination.length; i++) {
                    commonColumns.add(combination[i]);
                }

                /*
                 * The resulting cluster should only be stored if it is not a
                 * sub cluster of an already stored cluster
                 */
                if(!isSubCluster(commonColumns, commonRows, clusters))
                {
                    /*
                     * Now we can simply create the new sub matrix, compute its
                     * score and add to the list of clusters.
                     */
                    BooleanMatrix subMatrix =
                            matrix.subMatrix(
                                commonColumns.toArray(new Integer[commonColumns.size()]),
                                commonRows.toArray(new Integer[commonRows.size()])
                            );

                    clusters.add(subMatrix);

                    subMatrix.computeScore(matrix);
                }
            }
        }

        return clusters;
    }

    /**
     * This method will return all rows that matches a given column combination.
     *
     * If the column combination is let's say [3, 5, 8] then we want to find
     * the rows that has its column 3, 5 and 8 filled with a positive value.
     *
     * @param combination the combination to find matching rows for
     * @return the list of indexes of the matching rows
     */
    private List<Integer> findCorrespondingRows(int[] combination) {

        List<Integer> correspondingRows = new ArrayList<>();

        /*
         * For each row, we will check all of the values of its
         * elements at the indexes mentionned in the combination
         * array. If one of those elements is false, then this is
         * not a match and the crossed row won't be added to the
         * array of corresponding rows.
         */
        for (int i = 0, matrix1Length = matrix.getMatrix().length; i < matrix1Length; i++) {

            boolean[] row = matrix.getMatrix()[i];
            boolean corresponds = true;

            for (int j = 0; j < combination.length; j++) {
                if(!row[combination[j]])
                {
                    corresponds = false;
                    break;
                }
            }

            if (corresponds)
                correspondingRows.add(i);
        }

        return correspondingRows;

    }
}
