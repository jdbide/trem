package com.avancial.socle.utils;

import java.math.BigInteger;

public class Convertisseur {
    public static String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);
    }
}
