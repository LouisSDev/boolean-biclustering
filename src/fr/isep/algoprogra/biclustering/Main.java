package fr.isep.algoprogra.biclustering;

import fr.isep.algoprogra.biclustering.algorithm.BiClusterer;
import fr.isep.algoprogra.biclustering.algorithm.ColumnCombinationsScanBiClusterer;
import fr.isep.algoprogra.biclustering.algorithm.ColumnPositiveScanBiClusterer;
import fr.isep.algoprogra.biclustering.matrix.AnimalsGenesMatrixReader;
import fr.isep.algoprogra.biclustering.matrix.BooleanMatrix;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final int BENCHMARK_ITERATIONS_COUNT = 10;

    public static void main(String[] args) throws IOException {

        BooleanMatrix geneMatrix = AnimalsGenesMatrixReader.read();
        System.out.println(geneMatrix.toString());


        testBiclusterer(new ColumnPositiveScanBiClusterer(geneMatrix));
        System.out.println("---------------------");
        testBiclusterer(new ColumnCombinationsScanBiClusterer(geneMatrix));

    }

    private static void testBiclusterer(BiClusterer biClusterer) {

        Long startTime = System.currentTimeMillis();

        List<BooleanMatrix> clusters = biClusterer.cluster();


        /*
         * We run the biclustering n times to benchmark more precisely its
         * performances
         */
        for (int i = 0; i < BENCHMARK_ITERATIONS_COUNT - 1; i++) {
            biClusterer.cluster();
        }

        System.out.printf(
                "Tested Biclusterer %s %d times in %d ms.%n",
                biClusterer.getClass(),
                BENCHMARK_ITERATIONS_COUNT,
                System.currentTimeMillis() - startTime
        );

        clusters = clusters.stream().sorted().limit(15).collect(Collectors.toList());

        for(BooleanMatrix cluster : clusters) {

            System.out.println(cluster);
        }
    }
}
