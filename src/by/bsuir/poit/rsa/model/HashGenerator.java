package by.bsuir.poit.rsa.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public enum HashGenerator {
    INSTANCE;

    private static final BigInteger H_ZERO = new BigInteger("100");
    private static final String NUMBER_REGEXP = "[\\s]+";

    public BigInteger hashText(String text, BigInteger n) {
        final char[] chars = text.toCharArray();
        List<BigInteger> bigIntegers = new ArrayList<>();
        for (Character letter : chars) {
            final int numericValue = (int) letter;
            bigIntegers.add(new BigInteger(String.valueOf(numericValue)));
        }
        BigInteger hash = H_ZERO;
        for (BigInteger num : bigIntegers) {
            hash = (hash.add(num)).pow(2).mod(n);
        }
        return hash;
    }

    public BigInteger hashNumeric(String text, BigInteger n) {
        String[] inputNumbers = text.split(NUMBER_REGEXP);
        List<BigInteger> bigIntegers = new ArrayList<>();
        for (String inputNumber : inputNumbers) {
            BigInteger number = new BigInteger(inputNumber);
            bigIntegers.add(number);
        }
        BigInteger hash = H_ZERO;
        for (BigInteger num : bigIntegers) {
            hash = (hash.add(num)).pow(2).mod(n);;
        }
        return hash;
    }
}
