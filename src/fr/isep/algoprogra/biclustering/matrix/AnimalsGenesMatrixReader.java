package fr.isep.algoprogra.biclustering.matrix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AnimalsGenesMatrixReader {

    private static final String ZOO_DATA_FILENAME = "zoo.data";


    /**
     *
     *
     * This is how the raw file colums are organized
     *
     *
         2.  hair		Boolean
         3.  feathers		Boolean
         4.  eggs		Boolean
         5.  milk		Boolean
         6.  airborne		Boolean
         7.  aquatic		Boolean
         8.  predator		Boolean
         9.  toothed		Boolean
         10. backbone		Boolean
         11. breathes		Boolean
         12. venomous		Boolean
         13. fins		Boolean
         14. legs		Numeric (set of values: {0,2,4,5,6,8})
         15. tail		Boolean
         16. domestic		Boolean
         17. catsize		Boolean
         18. type		Numeric (integer values in range [1,7])
     *
     *
     */

    private static final String[] GENES = new String[] {
            "hair",
            "feathers",
            "eggs",
            "milk",
            "airborne",
            "aquatic",
            "predator",
            "toothed",
            "backbone",
            "breathes",
            "venomous",
            "fins",
            "tail",
            "domestic",
            "catsize",
            "hasNoLegs",
            "hasTwoLegs",
            "hasFourLegs",
            "hasFiveLegs",
            "hasSixLegs",
            "hasEightLegs"
    };


    private static final int[] COLUMN_MAPPING = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16};
    private static final int LEGS_INDEX = 13;


    public static BooleanGeneMatrix read() throws IOException {

        List<String> rawSpecies = Files.readAllLines(Paths.get(ZOO_DATA_FILENAME));
        String[] species = new String[rawSpecies.size()];
        boolean[][] matrix = new boolean[rawSpecies.size()][GENES.length];

        for (int i = 0, rawSpeciesSize = rawSpecies.size(); i < rawSpeciesSize; i++) {
            String entry = rawSpecies.get(i);
            String[] splittedEntry = entry.split(",");

            species[i] = splittedEntry[0];

            int j = 0;
            // We have to skip the 13th column because it is not a boolean but an integer
            for (j = 0; j < COLUMN_MAPPING.length; j++) {
                matrix[i][j] = splittedEntry[COLUMN_MAPPING[j]].equals("1");
            }


            // The idea here is to map the only integer value of this
            // dataset onto many boolean values which is a basic Data cleansing use case.
            int legsCount = Integer.valueOf(splittedEntry[LEGS_INDEX]);

            matrix[i][j] = legsCount == 0;
            matrix[i][j + 1] = legsCount == 2;
            matrix[i][j + 2] = legsCount == 4;
            matrix[i][j + 3] = legsCount == 5;
            matrix[i][j + 4] = legsCount == 6;
            matrix[i][j + 5] = legsCount == 8;

        }

        return new BooleanGeneMatrix(GENES, species, matrix);
    }

}
