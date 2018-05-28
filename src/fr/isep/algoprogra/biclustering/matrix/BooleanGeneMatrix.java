package fr.isep.algoprogra.biclustering.matrix;

public class BooleanGeneMatrix extends BooleanMatrix {


    private final int speciesCount;

    private final int genesCount;

    private final String[] genes;

    private final String[] species;

    private final Integer[] parentGenesIndexes;

    private final Integer[] parentSpeciesIndexes;

    private final boolean[][] matrix;

    public BooleanGeneMatrix(String[] genes, String[] species, boolean[][] matrix) {

        this(genes, species, null, null, matrix);
    }

    public BooleanGeneMatrix(String[] genes, String[] species, Integer[] parentGenesIndexes, Integer[] parentSpeciesIndexes, boolean[][] matrix) {

        this.genes = genes;
        this.species = species;
        this.speciesCount = species.length;
        this.genesCount = genes.length;
        this.parentGenesIndexes = parentGenesIndexes;
        this.parentSpeciesIndexes = parentSpeciesIndexes;
        this.matrix = matrix;
    }

    @Override
    public String[] getRowNames() {

        return species;
    }

    @Override
    public String[] getColumnNames() {
        return genes;
    }

    /**
     * This implementation of the submatrix process isn't supposed to be used
     * just as is because in fact, instead really picking a sub matrix, it
     * actually pick a sub matrix but fills it with trues whatever might happend
     * next.
     *
     *
     *
     * @param columns the indexes of the columns to keep
     * @param rows the indexes of the rows to keep
     * @return the sub matrix generated
     */
    @Override
    public BooleanMatrix subMatrix(Integer[] columns, Integer[] rows) {

        boolean[][] rawSubMatrix = new boolean[rows.length][columns.length];

        String[] genes = new String[columns.length];
        String[] species = new String[rows.length];

        for (int i = 0; i < columns.length; i++) {
            int colId = columns[i];
            genes[i] = this.genes[colId];
        }

        for (int i = 0; i < rows.length; i++) {
            int rowId = rows[i];
            species[i] = this.species[rowId];
        }

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                rawSubMatrix[i][j] = true;
            }
        }



        return new BooleanGeneMatrix(genes, species, columns, rows, rawSubMatrix);
    }

    @Override
    public Integer[] getParentColumns() {

        return parentGenesIndexes;
    }

    @Override
    public Integer[] getParentRows() {

        return parentSpeciesIndexes;
    }


    @Override
    public boolean[][] getMatrix() {

        return matrix;
    }

    @Override
    public int getRowsCount() {

        return speciesCount;
    }

    @Override
    public int getColumnsCount() {

        return genesCount;
    }

    @Override
    public boolean[] getColumn(int i) {
        boolean[] column = new boolean[species.length];
        for (int j = 0; j < species.length; j++) {
            column[j] = matrix[j][i];
        }
        return column;
    }

    @Override
    public boolean[] getRow(int i) {

        return matrix[i];
    }

    /**
     * The id of scoring the generated sub matrix is really helpful if we want to only
     * keep the best one. The question here is what's a best cluster ?  While the official
     * definition of clustering quality in bi clustering algorithm defines it with a complex
     * distance approach for continuous spaces, we hereby use only booleans and more importantly
     * only use them when there is a true value while we don't consider false value at all.
     *
     * This implementation rely on the following principle : the more a gene is found among
     * our species dataset, and the less it is reliable. This also means that the less a gene
     * is found among our species dataset and the more it is reliable. To this extent, we
     * compute the number of positive in the original matrix and we take the logarithm of the
     * inverse of the ratio of true amongst all species for each given gene.
     *
     * This way, rarest genes will give better cluster but clusters with a more genes in common
     * will always give better results.
     *
     *
     * @param originMatrix the origin matrix from which the actual sub matrix was created
     * @return the score of this submatrix
     */
    @Override
    public Float score(BooleanMatrix originMatrix) {

        float score = 0f;

        /*
         * For each genes in the new submatrix, we will calculate the positive count amongst
         * the species dataset, and then increment our score by Math.log( speciesCount / positiveCount)
         * which will be greater when the positive count is lower and will follow the logarithm rules
         * while always being a positive number.
         */
        for (int i = 0; i < getColumnsCount(); i++) {
            boolean[] genesValues = originMatrix.getColumn(getParentColumns()[i]);

            int positiveCount = 0;

            for (boolean gene : genesValues) {
                if (gene)
                    positiveCount ++;
            }

            score += (float) Math.log((float) originMatrix.getRowsCount() / positiveCount);
        }

        return score;
    }


    @Override
    public int compareTo(BooleanMatrix o) {
        return o.getScore().compareTo(getScore());
    }

}
