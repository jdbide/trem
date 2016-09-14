package com.avancial.app.utilitaire;

import java.text.ParseException;
/**
 * Interface definissant un traitement sur une donnee (utiliser lors de l'import brut)
 * @author gabriel.gagnier
 *
 */
public interface ITraitementDonnees {
    public String execute(String donnee) throws ParseException;
    public String execute2(Object objects) throws ParseException;
}
