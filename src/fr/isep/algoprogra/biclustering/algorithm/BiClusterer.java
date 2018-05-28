package fr.isep.algoprogra.biclustering.algorithm;

import fr.isep.algoprogra.biclustering.matrix.BooleanMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BiClusterer {

    protected final BooleanMatrix matrix;

    public BiClusterer(BooleanMatrix matrix) {

        this.matrix = matrix;
    }

    /**
     * This method will cluster a boolean matrix
     * @return a list of sub matrix that will represents positive
     *          clusters of the original boolean matrix dataset
     */
    public abstract List<BooleanMatrix> cluster();

    /**
     * This method is aimed at removing submatrix duplicatas. In fact, most algorithms
     * tends to create clusters that are themselves contained into bigger and more
     * reliable clusters. We want to keep only the biggest one when they are effectively
     * more reliable.
     *
     * @param commonColumns the colunns indexes of the future sub cluster
     * @param commonRows the row indexes of the future sub cluster
     * @param clusters the already found clusters
     * @return a boolean indicating wether this is a sub cluster or not
     */
    protected boolean isSubCluster(List<Integer> commonColumns, List<Integer> commonRows, List<BooleanMatrix> clusters) {

        /*
         * First step is to go through each of our already found clusters.
         * We cross the copied array because we will maybe remove elements
         * of the original one during the process
         */
        List<BooleanMatrix> copiedClusters = new ArrayList<>(clusters);
        for(BooleanMatrix mat : copiedClusters) {

            /*
             * We now retrieve the actual submatrix columns and row indexes arrays
             */
            List<Integer> comparedMatRows = Stream.of(mat.getParentRows()).collect(Collectors.toList());
            List<Integer> comparedMatColumns = Stream.of(mat.getParentColumns()).collect(Collectors.toList());

            /*
             * And copy them to ensure we won't alter them.
             */
            List<Integer> copiedComparedMatRows = new ArrayList<>(comparedMatRows);
            List<Integer> copiedComparedMatColumns = new ArrayList<>(comparedMatColumns);

            /*
             * We now find the intersection of the new to come cluster and the actual one
             */
            copiedComparedMatColumns.retainAll(commonColumns);
            copiedComparedMatRows.retainAll(commonRows);

            /*
             * If the intersection has as many columns and rows as the new cluster to create, then
             * we are in the case of a subcluster so we don't want to add this cluster.
             */
            if(copiedComparedMatColumns.size() == commonColumns.size() && copiedComparedMatRows.size() == commonRows.size()) {
                return true;
            }

            /*
             * If the intersection has as many columns and rows as the actual submatrix, then
             * the cluster we are trying to add contains the actual cluster we are analysing,
             * therefore we will remove it from the array but this won't be a sub cluster so
             * we still can return false
             */
            if(copiedComparedMatColumns.size() == comparedMatColumns.size() && copiedComparedMatRows.size() == comparedMatRows.size())
            {
                clusters.remove(mat);
            }

        }


        return false;
    }
}
