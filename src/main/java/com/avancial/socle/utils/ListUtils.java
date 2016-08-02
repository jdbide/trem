package com.avancial.socle.utils;

import java.util.List;

public class ListUtils {

    /**
     * Vérifie si les listes contiennent les mêmes valeurs, en se basant sur la
     * méthode ".equals" des objets qu'elles contiennent, sans se soucier de
     * l'ordre.
     * 
     * @param l1
     * @param l2
     * @return
     */
    public static boolean compareLists(List<Object> l1, List<Object> l2) {
        for (Object object : l1) {
            if (!l2.contains(object)) {
                return false;
            }
        }
        for (Object object : l2) {
            if (!l1.contains(object)) {
                return false;
            }
        }
        return true;
    }
}
