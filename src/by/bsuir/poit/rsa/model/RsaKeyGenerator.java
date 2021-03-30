package by.bsuir.poit.rsa.model;

import java.math.BigInteger;
import java.util.Random;

public enum RsaKeyGenerator {
    INSTANCE;

    public BigInteger generatePublicKey(BigInteger p, BigInteger q) {
        BigInteger eulerFunction = p.subtract(new BigInteger("1"))
                .multiply(q.subtract(new BigInteger("1")));
        BigInteger key = generateRandomValue(eulerFunction.subtract(new BigInteger("1")));
        while (!checkMutuallySimple(key, eulerFunction)) {
            key = generateRandomValue(eulerFunction);
        }
        return key;
    }

    public BigInteger calculatePrivateKey(BigInteger p, BigInteger q, BigInteger publicKey) {
        BigInteger eulerFunction = p.subtract(new BigInteger("1"))
                .multiply(q.subtract(new BigInteger("1")));
        return calculateMultiplicativeModuloInverse(eulerFunction, publicKey);
    }

    private BigInteger generateRandomValue(BigInteger maxLimit) {
        BigInteger minLimit = new BigInteger("1");
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
        return firstValue.equals(new BigInteger("1"));
    }

    private BigInteger calculateMultiplicativeModuloInverse(BigInteger a, BigInteger b) {
        BigInteger d0 = a;
        BigInteger d1 = b;
        BigInteger x0 = new BigInteger("1");
        BigInteger x1 = new BigInteger("0");
        BigInteger y0 = new BigInteger("0");
        BigInteger moduloInverse = new BigInteger("1");

        while (d1.compareTo(new BigInteger("1")) > 0) {
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
