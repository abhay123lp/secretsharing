/*
 * Joining message using shares from (k,n) threshold scheme
 */
package secretsharing;

import java.math.BigInteger;
import secretsharingException.JoiningException;

public class Joining {

    public Joining(BigInteger prime) {
        this.prime = prime;
    }

    public BigInteger getMessage(BigInteger[] args, BigInteger[] values, int k) throws JoiningException {
        if (args.length != values.length) {
            throw new JoiningException("Quantity of args and values doesn't match!");
        }
        if (args.length != k) {
            throw new JoiningException("Can't joining!");
        }
        BigInteger message = BigInteger.valueOf(0);
        BigInteger[][] matrix = new BigInteger[k][k];
        fillMatrix(matrix, args);
        message = systemSolve(matrix, values);
        return message;
    }

    protected void fillMatrix(BigInteger[][] matrix, BigInteger[] args) {
        int k = args.length;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k-1; j++) {
                matrix[i][j] = args[i].pow(k - 1 - j);
                matrix[i][j] = matrix[i][j].mod(prime);
            }
            matrix[i][k - 1] = BigInteger.valueOf(1);
        }
    }

    protected void changeRow(BigInteger[][] matrix, int i, int j) {
        BigInteger t;
        for (int k = 0; k < matrix[0].length; k++) {
            t = matrix[i][k];
            matrix[i][k] = matrix[j][k];
            matrix[j][k] = t;
        }
    }

    protected void rowLinComb(BigInteger[][] matrix, BigInteger ki, int i, BigInteger kj, int j) {
        for (int k = 0; k < matrix[0].length; k++) {
            matrix[j][k] = matrix[i][k].multiply(ki).add(matrix[j][k].multiply(kj));
            matrix[j][k] = matrix[j][k].mod(prime);
        }
    }

    protected BigInteger systemSolve(BigInteger[][] matrix, BigInteger[] values) throws JoiningException {
        int i, j;
        BigInteger t = BigInteger.valueOf(0);
        for (j = 0; j < values.length; j++) {
            i = j;
            while ((i < values.length) && (matrix[i][j].equals(BigInteger.ZERO))) {
                i++;
            }
            if (i == values.length) {
                throw new JoiningException("Bad matrix!");
            }
            if (i != j) {
                changeRow(matrix, i, j);
                t = values[i];
                values[i] = values[j];
                values[j] = t;
            }
            t = matrix[i][j];
            for (i = 0; i < values.length; i++) {
                matrix[j][i] = matrix[j][i].multiply(t.modInverse(prime));
                matrix[j][i] = matrix[j][i].mod(prime);
            }
            values[j] = values[j].multiply(t.modInverse(prime));
            values[j] = values[j].mod(prime);
            for (i = j + 1; i < values.length; i++) {
                t = matrix[i][j];
                rowLinComb(matrix, t.multiply(BigInteger.valueOf(-1)), j, BigInteger.ONE, i);
                values[i] = values[i].subtract(values[j].multiply(t));
                values[i] = values[i].mod(prime);
            }
        }
        return values[values.length - 1];
    }
    protected BigInteger prime;
}
