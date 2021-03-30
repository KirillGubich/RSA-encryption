package by.bsuir.poit.rsa.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public enum RsaScrambler {
    INSTANCE;

    private static final String NUMBER_REGEXP = "[\\s]+";
    private static final BigInteger TWO = new BigInteger("2");

    public String encrypt(String text, BigInteger key, BigInteger r) {
        String[] inputNumbers = text.split(NUMBER_REGEXP);
        List<BigInteger> resultList = new ArrayList<>();
        for (String inputNumber : inputNumbers) {
            BigInteger number = new BigInteger(inputNumber);
            resultList.add(fastPower(number, key, r));
        }
        StringBuilder sb = new StringBuilder();
        for (BigInteger element : resultList) {
            sb.append(element);
            sb.append(" ");
        }
        return sb.toString();
    }

    public BigInteger fastPower(BigInteger number, BigInteger power, BigInteger n) {
        BigInteger a1 = number;
        BigInteger z1 = power;
        BigInteger x = BigInteger.ONE;
        while (z1.compareTo(BigInteger.ZERO) != 0) {
            while (z1.mod(TWO).compareTo(BigInteger.ZERO) == 0) {
                z1 = z1.divide(TWO);
                a1 = a1.multiply(a1).mod(n);
            }
            z1 = z1.subtract(BigInteger.ONE);
            x = x.multiply(a1).mod(n);
        }
        return x;
    }
}
