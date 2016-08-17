package com.avancial.app.service;

import java.util.StringTokenizer;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Implémentation de {@link IMultipleInsertRequestGenerator}, permettant de
 * définir le nombre maximum d'insertions faites en une requête, et utilisant
 * une seule connexion pour exécuter la ou les requêtes.
 * 
 * @author heloise.guillemaud
 *
 */
public class MultipleInsertRequestGenerator implements IMultipleInsertRequestGenerator {

    /**
     * Session utilisée pour l'exécution des requêtes
     */
    private Session session;
    /**
     * Builder pour générer la requête d'insertion
     */
    private StringBuilder request = new StringBuilder();
    /**
     * Nombre maximum d'insertions dans une requête
     */
    private Integer limitNbValues;
    /**
     * Nombre courant total de valeurs à ajouter
     */
    private Integer curNbValues;
    /**
     * Précise si la requête a été initialisée, avec le nom de la table et de
     * ses champs
     */
    private Boolean reqInit;

    /**
     * Contient le début d'une requête d'insertion:<br>
     * {@code "INSERT INTO table (champ1,champ2,...) VALUES"}
     */
    private String insertReq;

    /**
     * Constructeur
     * 
     * @param session
     *            Connexion pour exécuter la ou les requêtes d'insertion
     * @param limitNbValues
     *            Nombre maximum d'insertions faites dans une seule requête
     */
    public MultipleInsertRequestGenerator(Session session, Integer limitNbValues) {
        this.session = session;
        this.limitNbValues = limitNbValues;

        this.init();
    }

    public void initRequest(String nomTable, String... nomChamps) {
        /* On ré-initialise l'objet */
        this.init();

        this.request.append(nomTable).append(" (");

        /* Ajout des noms de tous les champs */
        for (String nomChamp : nomChamps) {
            this.request.append(nomChamp).append(",");
        }
        /* On enlève la dernière virgule en trop */
        this.request.setLength(this.request.length() - 1);
        this.request.append(") VALUES ");

        this.reqInit = true;
        this.insertReq = this.request.toString();
    }

    public void addValue(Object... colValues) {
        /*
         * Si on a atteint le nombre limite de lignes à ajouter en une requête,
         * on termine la requête en cours et à la suite on en initialise une
         * nouvelle
         */
        if (this.curNbValues > 0 && this.curNbValues % this.limitNbValues == 0) {
            /* Terminaison requête en cours */
            this.request.append("); ");

            /* Début requête suivante */
            this.request.append(this.insertReq);
        }

        /*
         * On ajoute la parenthèse ouvrante pour la suite de valeurs, ainsi
         * qu'une parenthèse fermante et une virgule si ce n'est pas la première
         * valeur
         */
        if (!(this.curNbValues % this.limitNbValues == 0)) {
            this.request.append("), (");
        }
        else {
            this.request.append("(");
        }

        /* On ajoute toutes les valeurs séparées par une virgule */
        for (Object colValue : colValues) {
            /* On récupère la valeur en String */
            String strValue;
            if (colValue == null) {
                strValue = "null";
            }
            else if (colValue.getClass().equals(String.class)) {
                strValue = (String) colValue;
                strValue = strValue.replaceAll("'", "''");
                strValue = "'" + strValue + "'";
            }
            else {
                strValue = colValue.toString().replaceAll("'", "''");
            }

            this.request.append(strValue).append(",");
        }
        /* On enlève la dernière virgule en trop */
        this.request.setLength(this.request.length() - 1);

        this.curNbValues++;
    }

    public String getRequest() {
        String res = null;
        /*
         * On vérifie que la requête a été initialisée et qu'au moins une valeur
         * a été ajoutée
         */
        if (this.reqInit && this.curNbValues > 0) {
            this.request.append(");");
            res = this.request.toString();
        }

        return res;
    }

    public int executeRequest() throws Exception {
        /* On récupère la requête */
        String req = this.getRequest();

        /* On ré-initialise l'objet */
        this.init();

        /* Si elle est terminée */
        if (req != null) {
            StringTokenizer tokenizer = new StringTokenizer(req, ";");
            Query query = null;
            /* Nombre de valeurs insérées */
            int nbInsert = 0;

            /* On exécute à la suite les multiples requêtes d'insert */
            while (tokenizer.hasMoreElements()) {
                query = this.session.createSQLQuery(tokenizer.nextToken());
                nbInsert += query.executeUpdate();
            }

            return nbInsert;
        }
        return 0;
    }

    /**
     * Initialisation des compteurs et des string utilisés en interne pour
     * pouvoir recommencer une requête
     */
    private void init() {
        this.request.setLength(0);
        this.request.append("INSERT INTO ");
        this.reqInit = false;
        this.curNbValues = 0;

        this.insertReq = "";
    }

}
