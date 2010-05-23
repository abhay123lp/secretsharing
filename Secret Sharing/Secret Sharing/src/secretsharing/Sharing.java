/*
 * Secret Sharing by Lagrange's method. 
 */

package secretsharing;

import java.math.BigInteger;
import java.util.Random;
import secretsharingException.*;

public class Sharing {

    public Sharing(BigInteger message, BigInteger prime) {
        this.message = message;
        this.prime = prime;
    }

    void generatePolynom(int power) {
        /* Generate Polynom modulo prime */
        coefficients = new BigInteger[power];
        Random rnd = new Random();
        for (int i = 0; i < power; i++) {
            coefficients[i] = BigInteger.probablePrime(prime.bitLength(), rnd);
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

    public BigInteger[] getShares(BigInteger[] args, int threshold, int n) throws SharingException {
        /* Create (threshold,n)threshold scheme by calculating values of random polynom modulo prime */
        if (args.length != n) {
            throw new SharingException("Args lenth doesn't match with n!");
        }
        BigInteger[] shares = new BigInteger[n];
        generatePolynom(threshold-1);
        for (int i = 0; i < shares.length; i++) {
            shares[i] = getPolynomValue(args[i]);
        }
        return shares;
    }
    protected BigInteger message;
    protected BigInteger prime;
    protected BigInteger[] coefficients;
}