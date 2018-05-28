package fr.isep.algoprogra.biclustering.matrix;

import java.util.stream.Stream;

public abstract class BooleanMatrix implements Comparable<BooleanMatrix> {


    /**
     * @return a string array containing the row labels in their respective
     * original order
     */
    abstract public String[] getRowNames();

    /**
     * @return a string array containing the columns labels in their respective
     * original order
     */
    abstract public String[] getColumnNames();

    /**
     *
     * @param columns the selected columns indexes in the general matrix
     * @param rows the selected rows indexes in the general matrix
     * @return the sub matrix containing the submitted columns and rows
     */
    abstract public BooleanMatrix subMatrix(Integer[] columns, Integer[] rows);

    /**
     *
     * @return null if this is an original matrix or the index of the columns
     *         choosen from its parent matrix if this is a submatrix
     */
    abstract public Integer[] getParentColumns();

    /**
     *
     * @return null if this is an original matrix or the index of the rows
     *         choosen from its parent matrix if this is a submatrix
     */
    abstract public Integer[] getParentRows();

    /**
     * @return the raw boolean matrix
     */
    abstract public boolean[][] getMatrix();

    /**
     * @return the number of rows of this matrix
     */
    abstract public int getRowsCount();

    /**
     * @return the number of columns of this matrix
     */
    abstract public int getColumnsCount();

    /**
     *
     * @param i the index of the column to retrieve
     * @return the i-th column
     */
    abstract public boolean[] getColumn(int i);

    /**
     *
     * @param i the index of the row to retrieve
     * @return the i-th row
     */
    abstract public boolean[] getRow(int i);

    /**
     * Calculate the score of a submatrix given its origin matrix
     * @param originMatrix the origin matrix from which this submatrix
     *                     was extracted
     * @return the score of the matrix freshly computed
     */
    abstract public Float score(BooleanMatrix originMatrix);

    private Float score;

    public Float getScore() {
        return score;
    }

    public void computeScore(BooleanMatrix booleanMatrix) {
        this.score = score(booleanMatrix);
    }


    /**
     * Simple overrided method that nicefully prints any matrix in
     * a proper indented tab to see exactly what it features easily.
     * @return the stringified matrix
     */
    @Override
    public String toString() {
        StringBuilder bldr = new StringBuilder();


        // This way, we will retrieve the max length of species names
        int rowNamesMaxLength = Stream.of(getRowNames())
                .map(String::length)
                .max(Integer::compareTo)
                .get();

        appendSpaces(bldr, rowNamesMaxLength, 0);

        for(String column : getColumnNames()) {
            bldr.append(column);
            appendSpaces(bldr, column.length(), column.length());
        }


        bldr.append("\n");

        for (int i = 0, matrixLength = getMatrix().length; i < matrixLength; i++) {
            boolean[] entryValues = getMatrix()[i];
            String entryName = getRowNames()[i];
            bldr.append(entryName);
            appendSpaces(bldr, rowNamesMaxLength, entryName.length());

            for (int j = 0, columnNameLength = entryValues.length; j < columnNameLength; j++) {
                boolean entryVal = entryValues[j];
                bldr.append(entryVal ? "1" : "0");
                appendSpaces(bldr, getColumnNames()[j].length(), 1);
            }

            bldr.append("\n");
        }

        return bldr.toString();

    }

    private static void appendSpaces(StringBuilder bldr, int columnMaxLength, int originalLength) {

        for (int i = originalLength ; i < columnMaxLength + 1; i++) {
            bldr.append(" ");
        }
        bldr.append("|");
    }

}
