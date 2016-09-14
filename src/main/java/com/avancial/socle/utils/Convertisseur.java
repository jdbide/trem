package com.avancial.socle.utils;

import java.lang.reflect.Array;
import java.math.BigInteger;

public class Convertisseur {
    public static String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);
    }
    
    public static String asciiToHex(String s) {
        char[] chars = s.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }
    
    public static String asciiToBin(String s) {
        return hexToBin(asciiToHex(s));
    }
    
    public static String test2(Object object) {
        return hexToBin(test(object));
    }
    
    public static String test(Object x) {
    	StringBuffer hex = new StringBuffer();
        int length = Array.getLength(x);
        
        for(int i=0;i<length;i++){
            Number n = (Number) Array.get(x, i);
            hex.append(n.toString());
        }
    	
    	return asciiToBin(hex.toString());
    }
}
