package fr.isep.algoprogra.biclustering.algorithm;

import fr.isep.algoprogra.biclustering.matrix.BooleanMatrix;

import java.util.ArrayList;
import java.util.List;

public class ColumnPositiveScanBiClusterer extends BiClusterer {

    private static final int MIN_ELEMENTS_IN_COMMON = 3;
    private static final int MAX_ELEMENTS_IN_COMMON = 20;


    public ColumnPositiveScanBiClusterer(BooleanMatrix matrix) {
        super(matrix);
    }

    /**
     * This method is a very heuristic method for finding clusters in
     * boolean matrix. This is totally not optimized because of its
     * O(n!) complexity. We limited its capacities and it does gives
     * some slightly interesting patterns but it is very limited.
     *
     * The idea behind this algorithm is the following :
     *
     * We start scanning the columns of the matrix one by one.
     * What we are looking for are positive elements in this columns.
     * If there is more than a certain threshold, then we will try to
     * see if the corresponding rows were the positive occures have
     * other similiraties. Most of the time, there are about 25 positive
     * per column, which means there is litterally no chance find positive
     * values for 2 more columns on the same rows.
     *
     * That's why we then remove one entry of those 25 positive and try
     * with now 24 rows. This will most likely not work and we the iterate
     * with one less property until we have less than the minimum common
     * elements or finally find sufficient matches.
     *
     * But we pick the row to remove randomly each time so that the idea is
     * to try to remove each one of them, one at a time which transforms
     * the problem into a O(n!) complexity problem. That's why we limit the
     * max size of the positive values we accept to analyse while stopping
     * the algorithm as soon as we find a match (we could have gone further
     * to find all matches possible).
     *
     *
     * @return the clusters as a list of matrix
     */
    @Override
    public List<BooleanMatrix> cluster() {

        List<BooleanMatrix> clusters = new ArrayList<>();

        for (int i = 0; i < matrix.getColumnsCount(); i++) {
            boolean[] parameterValues = matrix.getColumn(i);

            List<Integer> positiveElements = new ArrayList<>();

            for (int j = 0; j < parameterValues.length; j++) {
                if(parameterValues[j])
                    positiveElements.add(j);
            }

            if(positiveElements.size() >= MIN_ELEMENTS_IN_COMMON && positiveElements.size() <= MAX_ELEMENTS_IN_COMMON) {

                findCommonColumns(i, positiveElements, clusters);
            }

        }

        return clusters;
    }

    private boolean findCommonColumns(int i, List<Integer> positiveElements, List<BooleanMatrix> clusters) {

        List<Integer> commonColumns = findCommonColumnsWithExactRows(i, positiveElements);

        if(commonColumns.size() >= MIN_ELEMENTS_IN_COMMON)
        {
            BooleanMatrix subMatrix =
                    matrix.subMatrix(
                            commonColumns.toArray(new Integer[commonColumns.size()]),
                            positiveElements.toArray(new Integer[positiveElements.size()])
                    );
            clusters.add(subMatrix);
            subMatrix.computeScore(matrix);
            return true;
        }


        if(positiveElements.size() > MIN_ELEMENTS_IN_COMMON) {


            for(Integer el : positiveElements) {

                List<Integer> copiedPositiveElements = new ArrayList<>(positiveElements);
                copiedPositiveElements.remove(el);
                boolean found = findCommonColumns(i, copiedPositiveElements, clusters);

                if(found)
                    return true;
            }
        }

        return false;
    }



    private List<Integer> findCommonColumnsWithExactRows(int i, List<Integer> positiveElements) {


        List<Integer> commonColumns = new ArrayList<>();
        commonColumns.add(i);

        for (int i2 = i + 1; i2 < matrix.getColumnsCount(); i2++) {
            // If it's not the same column, we will search for corresponance

            int connectedComponents = 0;
            boolean[] parameterValues = matrix.getColumn(i2);
            for (int j = 0; j < matrix.getRowsCount(); j++) {

                if(parameterValues[j] && positiveElements.contains(j))
                    connectedComponents ++;

            }

            if(connectedComponents == positiveElements.size())
            {
                commonColumns.add(i2);
            }

        }

        return commonColumns;
    }

}
