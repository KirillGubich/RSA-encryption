package by.bsuir.poit.rsa.model;

import java.math.BigInteger;
import java.util.Random;

public enum RsaKeyGenerator {
    INSTANCE;

    public BigInteger generatePublicKey(BigInteger p, BigInteger q) {
        BigInteger eulerFunction = p.subtract(BigInteger.ONE)
                .multiply(q.subtract(BigInteger.ONE));
        BigInteger key = generateRandomValue(eulerFunction.subtract(BigInteger.ONE));
        while (!checkMutuallySimple(key, eulerFunction)) {
            key = generateRandomValue(eulerFunction);
        }
        return key;
    }

    public BigInteger calculatePrivateKey(BigInteger p, BigInteger q, BigInteger publicKey) {
        BigInteger eulerFunction = p.subtract(BigInteger.ONE)
                .multiply(q.subtract(BigInteger.ONE));
        return calculateMultiplicativeModuloInverse(eulerFunction, publicKey);
    }

    private BigInteger generateRandomValue(BigInteger maxLimit) {
        BigInteger minLimit = BigInteger.ONE;
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        Random randNum = new Random();
        int len = maxLimit.bitLength();
        BigInteger randomValue = new BigInteger(len, randNum);
        if (randomValue.compareTo(minLimit) < 0) {
            randomValue = randomValue.add(minLimit);
        }
        if (randomValue.compareTo(bigInteger) >= 0) {
            randomValue = randomValue.mod(bigInteger).add(minLimit);
        }
        return randomValue;
    }

    private boolean checkMutuallySimple(BigInteger firstValue, BigInteger secondValue) {
        while (!firstValue.equals(secondValue)) {
            if (firstValue.compareTo(secondValue) > 0) {
                firstValue = firstValue.subtract(secondValue);
            } else {
                secondValue = secondValue.subtract(firstValue);
            }
        }
        return firstValue.equals(BigInteger.ONE);
    }

    private BigInteger calculateMultiplicativeModuloInverse(BigInteger a, BigInteger b) {
        BigInteger d0 = a;
        BigInteger d1 = b;
        BigInteger x0 = BigInteger.ONE;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger moduloInverse = BigInteger.ONE;

        while (d1.compareTo(BigInteger.ONE) > 0) {
            BigInteger q;
            BigInteger d2;
            BigInteger x2;
            BigInteger y2;
            q = d0.divide(d1);
            d2 = d0.mod(d1);
            x2 = x0.subtract(q.multiply(x1));
            y2 = y0.subtract(q.multiply(moduloInverse));
            d0 = d1;
            d1 = d2;
            x0 = x1;
            x1 = x2;
            y0 = moduloInverse;
            moduloInverse = y2;
        }
        return moduloInverse.signum() != -1 ? moduloInverse : moduloInverse.add(a);
    }
}
