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
    public static<T> boolean compareLists(List<T> l1, List<T> l2) {
        for (T object : l1) {
            if (!l2.contains(object)) {
                return false;
            }
        }
        for (T object : l2) {
            if (!l1.contains(object)) {
                return false;
            }
        }
        return true;
    }
}
