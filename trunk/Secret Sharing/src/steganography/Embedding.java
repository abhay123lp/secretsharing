/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package steganography;

import java.math.BigInteger;
import steganographyException.*;

public class Embedding {

    public Embedding(BigInteger message, BigInteger prime) {
        this.message = message;
        this.prime = prime;
    }

    void generatePolynom(int power) {
        /* Generate Polynom modulo prime */
        coefficients = new BigInteger[power + 1];
        for (int i = 0; i <= power; i++) {
            coefficients[i] = prime.nextProbablePrime();
            coefficients[i] = coefficients[i].mod(prime);
        }
    }

    BigInteger getPolynomValue(BigInteger arg) {
        BigInteger value = BigInteger.valueOf(0);
        value = value.add(message);
        for (int i = 0; i < coefficients.length; i++) {
            BigInteger k = arg.pow(i + 1);
            k = k.multiply(coefficients[i]);
            value = value.add(k);
        }
        value = value.mod(prime);
        return value;
    }

    public BigInteger[] getShares(BigInteger[] args, int k, int n) throws BadQuantityArgumentsException {
        /* Create (k,n)threshold scheme */
        if (args.length != n) {
            throw new BadQuantityArgumentsException();
        }
        BigInteger[] shares = new BigInteger[n];
        generatePolynom(k - 1);
        for (int i = 0; i < shares.length; i++) {
            shares[i] = getPolynomValue(args[i]);
        }
        return shares;
    }
    private BigInteger message;
    private BigInteger prime;
    private BigInteger[] coefficients;
}
